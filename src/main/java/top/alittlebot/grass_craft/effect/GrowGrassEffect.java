package top.alittlebot.grass_craft.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class GrowGrassEffect extends MobEffect {
    public GrowGrassEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyEffectTick(LivingEntity entity, int amplifier) {
        if (!entity.level().isClientSide()) {
            ServerLevel serverLevel = (ServerLevel) entity.level();
            BlockPos pos = entity.blockPosition();
            BlockState blockState = serverLevel.getBlockState(pos.below());
            if (blockState.is(Blocks.GRASS_BLOCK)) {
                serverLevel.setBlock(pos, Blocks.SHORT_GRASS.defaultBlockState(), 3);
            } else if (blockState.is(Blocks.DIRT)) {
                serverLevel.setBlock(pos.below(), Blocks.GRASS_BLOCK.defaultBlockState(), 3);
            }
        }
        return true;
    }
}
