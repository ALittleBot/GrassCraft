package top.alittlebot.grass_craft.event;

import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.LlamaRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.horse.Llama;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import top.alittlebot.grass_craft.GrassCraft;
import top.alittlebot.grass_craft.block.GrassBlocks;
import top.alittlebot.grass_craft.entity.GrassEntity;
import top.alittlebot.grass_craft.entity.renderer.GrassMobRenderer;
import top.alittlebot.grass_craft.entity.renderer.GrassTNTRenderer;

@EventBusSubscriber(modid = GrassCraft.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GrassClientEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(GrassEntity.GRASS_TNT_ENTITY.get(), GrassTNTRenderer::new);
        EntityRenderers.register(GrassEntity.GRASS_MOB_ENTITY.get(), GrassMobRenderer::new);
        EntityRenderers.register(GrassEntity.GRASS_BALL_ENTITY.get(), ThrownItemRenderer::new);
        EntityRenderers.register(GrassEntity.GRASS_LLAMA_ENTITY.get(), (context) -> new LlamaRenderer(context, ModelLayers.LLAMA));
        event.enqueueWork(
                () -> {
                    ItemBlockRenderTypes.setRenderLayer(
                            GrassBlocks.WEEDS_BLOCK.get(), RenderType.cutout()
                    ); // 摆了, 又不是不能用 (～￣▽￣)～
                }
        );
    }

    @SubscribeEvent
    public static void onRegisterAttributes(EntityAttributeCreationEvent event) {
        event.put(GrassEntity.GRASS_MOB_ENTITY.get(), Pig.createAttributes().build());
        event.put(GrassEntity.GRASS_LLAMA_ENTITY.get(), Llama.createAttributes().build());
    }
}
