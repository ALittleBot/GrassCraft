package top.alittlebot.grass_craft.mixin;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.EatBlockGoal;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(EatBlockGoal.class)
public interface EatBlockGoalAccessor {
    @Accessor("mob")
    Mob getMob();

    @Accessor("level")
    Level getLevel();
}
