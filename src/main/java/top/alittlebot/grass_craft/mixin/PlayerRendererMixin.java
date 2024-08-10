package top.alittlebot.grass_craft.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingEntityRenderer<Player, EntityModel<Player>> {

    public PlayerRendererMixin(EntityRendererProvider.Context context, EntityModel<Player> entityModel, float shadowSize) {
        super(context, entityModel, shadowSize);
    }

    @Redirect(method = "render(Lnet/minecraft/client/player/AbstractClientPlayer;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V"))
    private void redirectRender(LivingEntityRenderer renderer, LivingEntity player, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i) {
        // 设置玩家的碰撞箱为 1x1x1 的方块大小
        player.setBoundingBox(new AABB(player.getX(), player.getY(), player.getZ(),
                player.getX() + 1.0D, player.getY() + 1.0D, player.getZ() + 1.0D));

        // 创建草方块的 ItemStack
        ItemStack grassBlock = new ItemStack(Blocks.GRASS_BLOCK);

        // 开始渲染
        poseStack.pushPose();

        // 将草方块的中心对齐玩家的中心，并放大草方块的渲染大小
        poseStack.translate(0.5, -0.25, 0.5); // 将草方块居中
        poseStack.scale(4.0F, 4.0F, 4.0F);  // 将草方块的渲染缩放到1x1x1的方块大小

        // 渲染草方块
        Minecraft.getInstance().getItemRenderer().renderStatic(grassBlock, ItemDisplayContext.GROUND, i, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, null, 0);
        poseStack.popPose();
    }
}
