package top.alittlebot.grass_craft.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.*;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.portal.DimensionTransition;
import org.jetbrains.annotations.NotNull;
import top.alittlebot.grass_craft.block.GrassBlocks;

import javax.annotation.Nullable;
import java.util.Optional;

public class GrassTNTEntity extends Entity implements TraceableEntity {
    private static final EntityDataAccessor<Integer> DATA_FUSE_ID;
    private static final EntityDataAccessor<BlockState> DATA_BLOCK_STATE_ID;
    private static final ExplosionDamageCalculator USED_PORTAL_DAMAGE_CALCULATOR;
    @javax.annotation.Nullable
    private LivingEntity owner;
    private boolean usedPortal;

    public GrassTNTEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
        this.blocksBuilding = true;
    }

    public GrassTNTEntity(Level level, double x, double y, double z, @javax.annotation.Nullable LivingEntity owner) {
        this(GrassEntity.GRASS_TNT_ENTITY.get(), level);
        this.setPos(x, y, z);
        double d0 = level.random.nextDouble() * 6.2831854820251465;
        this.setDeltaMovement(-Math.sin(d0) * 0.02, 0.20000000298023224, -Math.cos(d0) * 0.02);
        this.setFuse(80);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        this.owner = owner;
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_FUSE_ID, 80);
        builder.define(DATA_BLOCK_STATE_ID, GrassBlocks.GRASS_TNT_BLOCK.get().defaultBlockState());
    }

    protected Entity.@NotNull MovementEmission getMovementEmission() {
        return MovementEmission.NONE;
    }

    public boolean isPickable() {
        return !this.isRemoved();
    }

    protected double getDefaultGravity() {
        return 0.04;
    }

    public void tick() {
        this.handlePortal();
        this.applyGravity();
        this.move(MoverType.SELF, this.getDeltaMovement());
        this.setDeltaMovement(this.getDeltaMovement().scale(0.98));
        if (this.onGround()) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.7, -0.5, 0.7));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.level().isClientSide) {
                this.explode();
            }
        } else {
            this.updateInWaterStateAndDoFluidPushing();
            if (this.level().isClientSide) {
                this.level().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5, this.getZ(), 0.0, 0.0, 0.0);
            }
        }

    }

    protected void explode() {
        Explosion explosion = this.level().explode(this, Explosion.getDefaultDamageSource(this.level(), this), this.usedPortal ? USED_PORTAL_DAMAGE_CALCULATOR : null, this.getX(), this.getY(0.0625), this.getZ(), 4.0F, false, Level.ExplosionInteraction.TNT);
        explosion.explode();
        explosion.finalizeExplosion(true);
        for (BlockPos blockpos : explosion.getToBlow()) {
            BlockState blockstate = this.level().getBlockState(blockpos);
            if (blockstate.canBeReplaced()) {
                this.level().setBlock(blockpos, Blocks.GRASS_BLOCK.defaultBlockState(), 3);
            }
        }
    }

    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putShort("fuse", (short)this.getFuse());
        compound.put("block_state", NbtUtils.writeBlockState(this.getBlockState()));
    }

    protected void readAdditionalSaveData(CompoundTag compound) {
        this.setFuse(compound.getShort("fuse"));
        if (compound.contains("block_state", 10)) {
            this.setBlockState(NbtUtils.readBlockState(this.level().holderLookup(Registries.BLOCK), compound.getCompound("block_state")));
        }

    }

    @javax.annotation.Nullable
    public LivingEntity getOwner() {
        return this.owner;
    }

    public void restoreFrom(@NotNull Entity entity) {
        super.restoreFrom(entity);
        if (entity instanceof GrassTNTEntity grassTNT) {
            this.owner = grassTNT.getOwner();
        }

    }

    public void setFuse(int life) {
        this.entityData.set(DATA_FUSE_ID, life);
    }

    public int getFuse() {
        return this.entityData.get(DATA_FUSE_ID);
    }

    public void setBlockState(BlockState blockState) {
        this.entityData.set(DATA_BLOCK_STATE_ID, blockState);
    }

    public BlockState getBlockState() {
        return this.entityData.get(DATA_BLOCK_STATE_ID);
    }

    @Nullable
    public Entity changeDimension(@NotNull DimensionTransition transition) {
        Entity entity = super.changeDimension(transition);
        if (entity instanceof GrassTNTEntity) {
            this.usedPortal = true;
        }

        return entity;
    }

    static {
        DATA_FUSE_ID = SynchedEntityData.defineId(GrassTNTEntity.class, EntityDataSerializers.INT);
        DATA_BLOCK_STATE_ID = SynchedEntityData.defineId(GrassTNTEntity.class, EntityDataSerializers.BLOCK_STATE);
        USED_PORTAL_DAMAGE_CALCULATOR = new ExplosionDamageCalculator() {
            public boolean shouldBlockExplode(@NotNull Explosion p_353087_, @NotNull BlockGetter p_353096_, @NotNull BlockPos p_353092_, @NotNull BlockState p_353086_, float p_353094_) {
                return !p_353086_.is(Blocks.NETHER_PORTAL) && super.shouldBlockExplode(p_353087_, p_353096_, p_353092_, p_353086_, p_353094_);
            }

            public @NotNull Optional<Float> getBlockExplosionResistance(@NotNull Explosion p_353090_, @NotNull BlockGetter p_353088_, @NotNull BlockPos p_353091_, @NotNull BlockState p_353093_, @NotNull FluidState p_353095_) {
                return p_353093_.is(Blocks.NETHER_PORTAL) ? Optional.empty() : super.getBlockExplosionResistance(p_353090_, p_353088_, p_353091_, p_353093_, p_353095_);
            }
        };
    }
}
