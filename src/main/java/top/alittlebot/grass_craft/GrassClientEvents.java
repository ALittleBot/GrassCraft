package top.alittlebot.grass_craft;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import top.alittlebot.grass_craft.block.GrassBlocks;
import top.alittlebot.grass_craft.entity.GrassEntity;
import top.alittlebot.grass_craft.entity.GrassMobEntity;
import top.alittlebot.grass_craft.entity.renderer.GrassMobRenderer;
import top.alittlebot.grass_craft.entity.renderer.GrassTNTRenderer;

@EventBusSubscriber(modid = GrassCraft.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GrassClientEvents {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(GrassEntity.GRASS_TNT_ENTITY.get(), GrassTNTRenderer::new);
        EntityRenderers.register(GrassEntity.GRASS_MOB_ENTITY.get(), GrassMobRenderer::new);
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
        event.put(GrassEntity.GRASS_MOB_ENTITY.get(), GrassMobEntity.createAttributes().build());
    }
}
