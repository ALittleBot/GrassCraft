package top.alittlebot.grass_craft.entity.renderer;

import net.minecraft.client.model.LlamaModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.LlamaDecorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.horse.Llama;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import top.alittlebot.grass_craft.GrassCraft;

@OnlyIn(Dist.CLIENT)
public class GrassLlamaRenderer extends MobRenderer<Llama, LlamaModel<Llama>> {
    private static final ResourceLocation GREEN = ResourceLocation.fromNamespaceAndPath(GrassCraft.MOD_ID, "textures/entity/grass_llama/green.png");

    public GrassLlamaRenderer(EntityRendererProvider.Context context, ModelLayerLocation layer) {
        super(context, new LlamaModel<>(context.bakeLayer(layer)), 0.7F);
        this.addLayer(new LlamaDecorLayer(this, context.getModelSet()));
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull Llama entity) {
        return GREEN;
    }
}
