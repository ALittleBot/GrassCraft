package top.alittlebot.grass_craft.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;
import top.alittlebot.grass_craft.effect.GrassEffects;
import top.alittlebot.grass_craft.item.GrassItems;

public class GrassBallEntity extends Snowball {
    public GrassBallEntity(Level level, LivingEntity shooter) {
        super(level, shooter);
    }

    public GrassBallEntity(Level level, double x, double y, double z) {
        super(level, x, y, z);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return GrassItems.GRASS_BALL_ITEM.get();
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        if (entity instanceof LivingEntity livingEntity) {
            livingEntity.addEffect(new MobEffectInstance(GrassEffects.GROW_GRASS, 30 * 20, 1));
        }
    }

    @Override
    protected void onHit(@NotNull HitResult result) {
        super.onHit(result);
        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();

            BlockPos hitPos = new BlockPos((int) result.getLocation().x, (int) result.getLocation().y, (int) result.getLocation().z);
            Level serverLevel = this.level();
            for (int x = -3; x <= 3; x++) {
                for (int z = -3; z <= 3; z++) {
                    BlockPos pos = hitPos.offset(x, -1, z);
                    BlockState blockState = serverLevel.getBlockState(pos);
                    if (blockState.is(Blocks.DIRT)) {
                        serverLevel.setBlock(pos, Blocks.GRASS_BLOCK.defaultBlockState(), 3);
                    }
                }
            }
        }
    }
}
