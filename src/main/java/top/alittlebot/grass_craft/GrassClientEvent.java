package top.alittlebot.grass_craft;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import top.alittlebot.grass_craft.entity.GrassEntity;
import top.alittlebot.grass_craft.entity.GrassMobEntity;
import top.alittlebot.grass_craft.entity.renderer.GrassMobRenderer;
import top.alittlebot.grass_craft.entity.renderer.GrassTNTRenderer;

@EventBusSubscriber(modid = GrassCraft.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class GrassClientEvent {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(GrassEntity.GRASS_TNT_ENTITY.get(), GrassTNTRenderer::new);
        EntityRenderers.register(GrassEntity.GRASS_MOB_ENTITY.get(), GrassMobRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterAttributes(EntityAttributeCreationEvent event) {
        event.put(GrassEntity.GRASS_MOB_ENTITY.get(), GrassMobEntity.createAttributes().build());
    }
}
