package top.alittlebot.grass_craft.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.alittlebot.grass_craft.effect.GrassEffects;
import top.alittlebot.grass_craft.entity.GrassLlamaEntity;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends EntityRenderer<T> implements RenderLayerParent<T, M> {
    /*
    * 十分地好玩, 快去试试吧 \(￣︶￣*\))
    */

    protected LivingEntityRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @Inject(method = "render(Lnet/minecraft/world/entity/LivingEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("HEAD"), cancellable = true)
    public void render(T entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
        ItemStack grassBlock = new ItemStack(Blocks.GRASS_BLOCK);
        if (entity.hasEffect(GrassEffects.TO_GRASS) && entity instanceof Player) {
            // 将玩家的碰撞箱设置为1x1x1方块大小并居中
            double blockCenterX = entity.getX() - 0.5;
            double blockCenterY = entity.getY();
            double blockCenterZ = entity.getZ() - 0.5;
            entity.setBoundingBox(new AABB(blockCenterX, blockCenterY, blockCenterZ,
                    blockCenterX + 0.99D, blockCenterY + 0.99D, blockCenterZ + 0.99D));  // 只能写成0.99D, 不然没办法塞进一格空间里

            // 将玩家位置移动到方块的中心，以调整视角
            entity.setPosRaw(blockCenterX + 0.5, blockCenterY, blockCenterZ + 0.5);

            // 在玩家位置渲染草方块
            poseStack.pushPose();
            poseStack.translate(0.0, -0.25, 0.0); // 根据需要调整平移
            poseStack.scale(4.0F, 4.0F, 4.0F);  // 缩放至覆盖整个碰撞箱

            // 渲染草方块
            Minecraft.getInstance().getItemRenderer().renderStatic(grassBlock, ItemDisplayContext.GROUND, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, null, 0);
            poseStack.popPose();
            ci.cancel();
        } else if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.hasEffect(GrassEffects.GRASS_POISONING)) {
            poseStack.pushPose();
            poseStack.translate(0.0, 0.0, 0.0); // 根据需要调整平移
            poseStack.scale(4.0F, 4.0F, 4.0F);  // 缩放至覆盖整个碰撞箱
            Minecraft.getInstance().getItemRenderer().renderStatic(grassBlock, ItemDisplayContext.GROUND, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, null, 0);
            poseStack.popPose();
            ci.cancel();
        } else {
            super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
        }

        if (entity instanceof GrassLlamaEntity) {
            poseStack.pushPose();
            if (entity.isBaby()) {
                // 如果是幼年羊驼，调整草方块的位置和比例
                poseStack.translate(0, 0.75f, 0); // 调整到幼年羊驼的头部
                poseStack.scale(2f, 2f, 2f); // 草方块更小
            } else {
                // 成年羊驼的草方块渲染
                poseStack.translate(0, 0.5f, 0); // 移动到成年羊驼的头部
                poseStack.scale(4f, 4f, 4f); // 草方块正常大小
            }
            Minecraft.getInstance().getItemRenderer().renderStatic(grassBlock, ItemDisplayContext.GROUND, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, null, 0);
            poseStack.popPose();
        }
    }
}
