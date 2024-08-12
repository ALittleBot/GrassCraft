package top.alittlebot.grass_craft.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.alittlebot.grass_craft.effect.GrassEffects;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererMixin<T extends LivingEntity> {

    @Inject(method = "render*", at = @At("HEAD"))
    public void render(T entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
        /*
        可能对于部分实体没有办法很好的适配，就这样吧 \(￣︶￣*\))
        */
        if (Minecraft.getInstance().player != null && Minecraft.getInstance().player.hasEffect(GrassEffects.GRASS_POISONING)) {
            ItemStack grassBlock = new ItemStack(Blocks.GRASS_BLOCK);
            poseStack.pushPose();
            poseStack.translate(0.0, 0.0, 0.0); // 根据需要调整平移
            poseStack.scale(4.0F, 4.0F, 4.0F);  // 缩放至覆盖整个碰撞箱
            Minecraft.getInstance().getItemRenderer().renderStatic(grassBlock, ItemDisplayContext.GROUND, packedLight, OverlayTexture.NO_OVERLAY, poseStack, buffer, null, 0);
            poseStack.popPose();
        }
    }
}
