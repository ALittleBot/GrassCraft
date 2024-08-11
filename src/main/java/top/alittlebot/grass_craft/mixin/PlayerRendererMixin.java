package top.alittlebot.grass_craft.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import top.alittlebot.grass_craft.effect.GrassEffects;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingEntityRenderer<Player, EntityModel<Player>> {

    public PlayerRendererMixin(EntityRendererProvider.Context context, EntityModel<Player> entityModel, float shadowSize) {
        super(context, entityModel, shadowSize);
    }

    @Redirect(method = "render(Lnet/minecraft/client/player/AbstractClientPlayer;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V"))
    private void redirectRender(LivingEntityRenderer renderer, LivingEntity player, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        /*
        参数瞎调的，只能适用于碰撞箱完整的方块
        有些bug，但勉强能用 (●'◡'●)
        */
        if (player.hasEffect(GrassEffects.TO_GRASS)) {
            // 将玩家的碰撞箱设置为1x1x1方块大小并居中
            double blockCenterX = player.getX() - 0.5;
            double blockCenterY = player.getY();
            double blockCenterZ = player.getZ() - 0.5;
            player.setBoundingBox(new AABB(blockCenterX, blockCenterY, blockCenterZ,
                    blockCenterX + 0.99D, blockCenterY + 0.99D, blockCenterZ + 0.99D));  // 只能写成0.99D，不然没办法塞进一格空间里

            // 将玩家位置移动到方块的中心，以调整视角
            player.setPosRaw(blockCenterX + 0.5, blockCenterY, blockCenterZ + 0.5);

            // 在玩家位置渲染草方块
            ItemStack grassBlock = new ItemStack(Blocks.GRASS_BLOCK);

            poseStack.pushPose();
            poseStack.translate(0.0, -0.25, 0.0); // 根据需要调整平移
            poseStack.scale(4.0F, 4.0F, 4.0F);  // 缩放至覆盖整个碰撞箱

            // 渲染草方块
            Minecraft.getInstance().getItemRenderer().renderStatic(grassBlock, ItemDisplayContext.GROUND, i, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, null, 0);
            poseStack.popPose();
        } else {
            super.render((Player) player, f, g, poseStack, multiBufferSource, i);
        }
    }
}