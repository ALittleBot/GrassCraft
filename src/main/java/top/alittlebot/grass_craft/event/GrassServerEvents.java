package top.alittlebot.grass_craft.event;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import top.alittlebot.grass_craft.GrassCraft;
import top.alittlebot.grass_craft.entity.GrassEntity;
import top.alittlebot.grass_craft.entity.GrassMobEntity;

@EventBusSubscriber(modid = GrassCraft.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)
public class GrassServerEvents {
    @SubscribeEvent
    public static void onRegisterAttributes(EntityAttributeCreationEvent event) {
        event.put(GrassEntity.GRASS_MOB_ENTITY.get(), GrassMobEntity.createAttributes().build());
    }
}
