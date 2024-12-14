package top.alittlebot.grass_craft.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import top.alittlebot.grass_craft.entity.GrassMobEntity;

@OnlyIn(Dist.CLIENT)
public class GrassMobRenderer extends EntityRenderer<GrassMobEntity> {

    private static final ResourceLocation GRASS_BLOCK_LOCATION = ResourceLocation.withDefaultNamespace("textures/block/grass_block_side.png");  // 不知道有什么用，随便填一个好了

    public GrassMobRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(@NotNull GrassMobEntity entity, float entityYaw, float partialTick, PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        // 复用草中毒的渲染代码怎么了
        ItemStack grassBlock = new ItemStack(Blocks.GRASS_BLOCK);
        poseStack.pushPose();
        poseStack.translate(0.0, -0.25, 0.0);
        poseStack.scale(4.0F, 4.0F, 4.0F);
        int overlayCoords = LivingEntityRenderer.getOverlayCoords(entity, 0.0F);
        Minecraft.getInstance().getItemRenderer().renderStatic(grassBlock, ItemDisplayContext.GROUND, packedLight, overlayCoords, poseStack, bufferSource, null, 0);
        poseStack.popPose();
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull GrassMobEntity grassTNT) {
        return GRASS_BLOCK_LOCATION;
    }
}
