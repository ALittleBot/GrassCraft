package top.alittlebot.grass_craft.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.level.Level;

public class GrassLlamaEntity extends Llama {
    public GrassLlamaEntity(EntityType<? extends Llama> entityType, Level world) {
        super(entityType, world);
    }
}
