package top.alittlebot.grass_craft.event;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import top.alittlebot.grass_craft.item.potion.GrassPotions;

public class GrassRegisterBrewingRecipesEvent {

    @SubscribeEvent
    public static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
        PotionBrewing.Builder builder = event.getBuilder();
        builder.addMix(
                Potions.AWKWARD,
                Items.SHORT_GRASS,
                GrassPotions.GRASS_POTION
        );
        builder.addMix(
                Potions.INVISIBILITY,
                Items.SHORT_GRASS,
                GrassPotions.TO_GRASS_POTION
        );
    }
}
