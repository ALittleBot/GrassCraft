package top.alittlebot.grass_craft.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Constructor;
import java.util.List;

public class VanillaGloveItem extends Item {
    public VanillaGloveItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        BlockState blockState = context.getLevel().getBlockState(context.getClickedPos());
        if (blockState == Blocks.SHORT_GRASS.defaultBlockState() || blockState == Blocks.TALL_GRASS.defaultBlockState() || blockState == Blocks.FERN.defaultBlockState() || blockState == Blocks.LARGE_FERN.defaultBlockState()) {
            generateFallingBlock(context.getClickedPos(), blockState, context.getLevel(), 1, context.getPlayer());
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, List<Component> tooltipComponents, @NotNull TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("grass_craft.tooltip.vanilla_glove"));
    }

    private void generateFallingBlock(BlockPos targetPos, BlockState blockState, Level world, int power, Player user) {
        if (!world.isClientSide) {
            FallingBlockEntity fallingBlockEntity = createFallingBlockEntity(world, targetPos.getX() + 0.5, targetPos.getY() + 1.2, targetPos.getZ() + 0.5, blockState);
            fallingBlockEntity.time = 1;
            fallingBlockEntity.setNoGravity(false);
            fallingBlockEntity.setPos(targetPos.getX() + 0.5, targetPos.getY() + 1.2, targetPos.getZ() + 0.5);
            fallingBlockEntity.setDeltaMovement(Vec3.ZERO);
            fallingBlockEntity.xo = targetPos.getX() + 0.5;
            fallingBlockEntity.yo = targetPos.getY() + 1.2;
            fallingBlockEntity.zo = targetPos.getZ() + 0.5;
            fallingBlockEntity.setStartPos(targetPos);

            launchBlock(targetPos, power, user, fallingBlockEntity);

            world.setBlock(targetPos, Blocks.AIR.defaultBlockState(), 3);
            world.addFreshEntity(fallingBlockEntity);
        }
    }

    private void launchBlock(BlockPos targetPos, int power, Player user, FallingBlockEntity fallingBlockEntity) {
        Vec3 userPos = user.position();
        Vec3 targetVec = new Vec3(targetPos.getX() + 0.5, targetPos.getY() + 0.5, targetPos.getZ() + 0.5);
        Vec3 direction = targetVec.subtract(userPos).normalize();
        direction = new Vec3(direction.x, direction.y + 0.2, direction.z).normalize();
        Vec3 velocity = direction.scale(power * 0.5);
        fallingBlockEntity.setDeltaMovement(velocity);
    }

    public FallingBlockEntity createFallingBlockEntity(Level level, double x, double y, double z, BlockState state) {
        try {
            Constructor<FallingBlockEntity> constructor = FallingBlockEntity.class.getDeclaredConstructor(Level.class, double.class, double.class, double.class, BlockState.class);
            constructor.setAccessible(true);
            return constructor.newInstance(level, x, y, z, state);
        } catch (Exception e) {
            e.printStackTrace(); // 就这样吧
        }
        return null;
    }
}
