package top.alittlebot.grass_craft.event;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import top.alittlebot.grass_craft.entity.GrassEntity;
import top.alittlebot.grass_craft.entity.GrassLlamaEntity;
import top.alittlebot.grass_craft.item.GrassItems;

public class GrassLlamaEvent {
    @SubscribeEvent
    public static void onPlayerRightClick(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        Entity target = event.getTarget();

        if (target instanceof Llama && !(target instanceof GrassLlamaEntity)) {
            if (player.getMainHandItem().getItem() == GrassItems.VANILLA_ROD_ITEM.get()) {
                GrassLlamaEntity grassLlama = new GrassLlamaEntity(GrassEntity.GRASS_LLAMA_ENTITY.get(), player.level());
                grassLlama.setPos(target.getX(), target.getY(), target.getZ());
                grassLlama.setYRot(target.getYRot());  // 保持朝向
                grassLlama.setXRot(target.getXRot());  // 保持俯仰角度
                target.remove(Entity.RemovalReason.KILLED);
                player.level().addFreshEntity(grassLlama);
                if (!player.isCreative()) {
                    player.getMainHandItem().shrink(1);
                }
            }
        }
    }
}
