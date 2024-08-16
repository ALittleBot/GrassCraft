package top.alittlebot.grass_craft.entity;

import javax.annotation.Nullable;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.DismountHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import top.alittlebot.grass_craft.item.GrassItems;

public class GrassMobEntity extends Animal implements ItemSteerable, Saddleable {
    /*
     * 抄猪的代码 （￣︶￣）↗
     * 很好玩
     */
    private static final EntityDataAccessor<Integer> DATA_BOOST_TIME = SynchedEntityData.defineId(GrassMobEntity.class, EntityDataSerializers.INT);

    public GrassMobEntity(EntityType<? extends GrassMobEntity> entityType, Level level) {
        super(entityType, level);
    }

    @org.jetbrains.annotations.Nullable
    @Override
    public AgeableMob getBreedOffspring(@NotNull ServerLevel serverLevel, @NotNull AgeableMob ageableMob) {
        return GrassEntity.GRASS_MOB_ENTITY.get().create(serverLevel);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.@NotNull Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_BOOST_TIME, 0);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.0));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.2, stack -> stack.is(GrassItems.GRASS_ON_A_STICK_ITEM), false));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.2, stack -> stack.is(Items.SHORT_GRASS), false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        Entity passenger = this.getFirstPassenger();
        if (passenger instanceof Player player && player.isHolding(GrassItems.GRASS_ON_A_STICK_ITEM.get())) {
            return player;
        }
        return super.getControllingPassenger();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.GRASS_STEP;
    }

    @Override
    protected SoundEvent getHurtSound(@NotNull DamageSource damageSource) {
        return SoundEvents.GRASS_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.GRASS_BREAK;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(GrassItems.WEEDS_ITEM.get());
    }


    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        super.mobInteract(player, hand);
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.is(Items.SHORT_GRASS)) {
            float f = this.getHealth();
            this.heal(10.0F);
            if (this.getHealth() == f) {
                return InteractionResult.PASS;
            } else {
                float f1 = 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F;
                this.playSound(SoundEvents.GRASS_PLACE, 1.0F, f1);
                itemStack.consume(1, player);
                return InteractionResult.sidedSuccess(this.level().isClientSide);
            }
        }
        if (this.isSaddleable() && !this.isVehicle() && !player.isSecondaryUseActive() && !this.isInLove()) {
            if (!this.level().isClientSide) {
                player.startRiding(this);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        return InteractionResult.PASS;
    }


    @Override
    public boolean isSaddleable() {
        return this.isAlive() && !this.isBaby();
    }

    @Override
    public void equipSaddle(@NotNull ItemStack itemStack, @org.jetbrains.annotations.Nullable SoundSource soundSource) {}

    @Override
    public boolean isSaddled() {
        return true;
    }

    @Override
    public @NotNull Vec3 getDismountLocationForPassenger(@NotNull LivingEntity livingEntity) {
        Direction direction = this.getMotionDirection();
        if (direction.getAxis() != Axis.Y) {
            int[][] offsets = DismountHelper.offsetsForDirection(direction);
            BlockPos blockPos = this.blockPosition();
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
            for (Pose pose : livingEntity.getDismountPoses()) {
                AABB aabb = livingEntity.getLocalBoundsForPose(pose);
                for (int[] offset : offsets) {
                    mutableBlockPos.set(blockPos.getX() + offset[0], blockPos.getY(), blockPos.getZ() + offset[1]);
                    double blockHeight = this.level().getBlockFloorHeight(mutableBlockPos);
                    if (DismountHelper.isBlockFloorValid(blockHeight)) {
                        Vec3 vec3 = Vec3.upFromBottomCenterOf(mutableBlockPos, blockHeight);
                        if (DismountHelper.canDismountTo(this.level(), livingEntity, aabb.move(vec3))) {
                            livingEntity.setPose(pose);
                            return vec3;
                        }
                    }
                }
            }
        }
        return super.getDismountLocationForPassenger(livingEntity);
    }

    @Override
    protected @NotNull Vec3 getRiddenInput(@NotNull Player player, @NotNull Vec3 travelVector) {
        return new Vec3(0.0, 0.0, 1.0);
    }

    @Override
    public @NotNull Vec3 getLeashOffset() {
        return new Vec3(0.0, 0.6 * this.getEyeHeight(), this.getBbWidth() * 0.4);
    }

    @Override
    public boolean boost() {
        return false;
    }

    @Override
    protected void tickRidden(@NotNull Player player, @NotNull Vec3 travelVector) {
        super.tickRidden(player, travelVector);
        this.setRot(player.getYRot(), player.getXRot() * 0.5F);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
    }

    @Override
    protected float getRiddenSpeed(@NotNull Player player) {
        return (float) (this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.25);
    }

    /*
    @Override
    public void tick() {
        super.tick();
        if (this.isInLove()) {
            List<Player> nearbyPlayers = this.level().getEntitiesOfClass(Player.class,
                    new AABB(this.blockPosition()).inflate(15.0));
            for (Player ignored : nearbyPlayers) {
                double d0 = this.random.nextGaussian() * 0.02;
                double d1 = this.random.nextGaussian() * 0.02;
                double d2 = this.random.nextGaussian() * 0.02;
                this.level().addParticle(ParticleTypes.HEART, this.getRandomX(1.0), this.getRandomY() + 0.5, this.getRandomZ(1.0), d0, d1, d2);
            }
        }
    }
     */
}