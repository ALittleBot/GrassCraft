package top.alittlebot.grass_craft.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.EatBlockGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import net.neoforged.neoforge.event.EventHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.EnumSet;
import java.util.function.Predicate;

@Mixin(EatBlockGoal.class)
public abstract class EatBlockGoalMixin extends Goal {
    private static final int EAT_ANIMATION_TICKS = 40;
    private static final Predicate<BlockState> IS_TALL_GRASS;
    private final Mob mob;
    private final Level level;
    private int eatAnimationTick;

    public EatBlockGoalMixin(Mob mob) {
        this.mob = mob;
        this.level = mob.level();
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        BlockPos blockpos = this.mob.blockPosition();
        return IS_TALL_GRASS.test(this.level.getBlockState(blockpos)) ? true : this.level.getBlockState(blockpos.below()).is(Blocks.GRASS_BLOCK);
    }

    @Override
    public void start() {
        this.eatAnimationTick = this.adjustedTickDelay(40);
        this.level.broadcastEntityEvent(this.mob, (byte) 10);
        this.mob.getNavigation().stop();
    }

    @Override
    public void stop() {
        this.eatAnimationTick = 0;
    }

    @Override
    public boolean canContinueToUse() {
        return this.eatAnimationTick > 0;
    }

    @Override
    public void tick() {
        this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
        if (this.eatAnimationTick == this.adjustedTickDelay(4)) {
            BlockPos blockpos = this.mob.blockPosition();
            if (IS_TALL_GRASS.test(this.level.getBlockState(blockpos))) {
                if (EventHooks.canEntityGrief(this.level, this.mob)) {
                    this.level.destroyBlock(blockpos, false);
                }

                this.mob.ate();
            } else {
                BlockPos blockpos1 = blockpos.below();
                if (this.level.getBlockState(blockpos1).is(Blocks.GRASS_BLOCK)) {
                    if (EventHooks.canEntityGrief(this.level, this.mob)) {
                        this.level.levelEvent(2001, blockpos1, Block.getId(Blocks.GRASS_BLOCK.defaultBlockState()));
                        this.level.setBlock(blockpos1, Blocks.DIRT.defaultBlockState(), 2);
                    }

                    this.mob.ate();
                }
            }
        }

    }

    static {
        IS_TALL_GRASS = BlockStatePredicate.forBlock(Blocks.SHORT_GRASS);
    }
}
