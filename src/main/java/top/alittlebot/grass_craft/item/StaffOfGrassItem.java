package top.alittlebot.grass_craft.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import top.alittlebot.grass_craft.effect.GrassEffects;

import java.util.List;

public class StaffOfGrassItem extends Item {

    public StaffOfGrassItem(Properties properties) {
        super(properties.durability(32));
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        if (context.getLevel() instanceof ServerLevel serverLevel) {
            BlockPos centerPos = context.getClickedPos();

            for (int x = -5; x <= 5; x++) {
                for (int z = -5; z <= 5; z++) {
                    BlockPos pos = centerPos.offset(x, 0, z);
                    BlockState blockState = serverLevel.getBlockState(pos);
                    if (blockState.is(Blocks.GRASS_BLOCK) && serverLevel.getBlockState(pos.above()).canBeReplaced()) {
                        serverLevel.setBlock(pos.above(), Blocks.SHORT_GRASS.defaultBlockState(), 3);
                    } else if (blockState.is(Blocks.DIRT) && serverLevel.getBlockState(pos).canBeReplaced()) {
                        serverLevel.setBlock(pos, Blocks.GRASS_BLOCK.defaultBlockState(), 3);
                    }
                }
            }

            List<LivingEntity> entities = serverLevel.getEntitiesOfClass(LivingEntity.class,
                    new net.minecraft.world.phys.AABB(centerPos).inflate(5),
                    entity -> !(entity instanceof Player));
            for (LivingEntity entity : entities) {
                entity.addEffect(new MobEffectInstance(GrassEffects.GROW_GRASS, 60 * 20, 1));
            }

            ItemStack itemStack = context.getItemInHand();
            Player player = context.getPlayer();
            itemStack.hurtAndBreak(1, player, EquipmentSlot.MAINHAND);

            return InteractionResult.SUCCESS;
        }

        return InteractionResult.PASS;
    }

    @Override
    public boolean hurtEnemy(@NotNull ItemStack stack, LivingEntity target, @NotNull LivingEntity attacker) {
        target.addEffect(new MobEffectInstance(GrassEffects.GROW_GRASS, 60 * 20, 1));
        return true;
    }
}
