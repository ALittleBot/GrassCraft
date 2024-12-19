package top.alittlebot.grass_craft.entity;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.Llama;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.alittlebot.grass_craft.effect.GrassEffects;
import top.alittlebot.grass_craft.item.GrassItems;

import javax.annotation.Nullable;

public class GrassLlamaEntity extends Llama {
    public GrassLlamaEntity(EntityType<? extends Llama> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    public @NotNull InteractionResult mobInteract(@NotNull Player player, @NotNull InteractionHand hand) {
        if (!player.hasEffect(GrassEffects.TO_GRASS)) {
            return super.mobInteract(player, hand);
        }
        this.doPlayerRide(player);
        return super.mobInteract(player, hand);
    }

    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        Entity passenger = this.getFirstPassenger();
        if (passenger instanceof Player player && player.isHolding(GrassItems.VANILLA_ROD_ITEM.get())) {
            return player;
        }
        return super.getControllingPassenger();
    }
}
