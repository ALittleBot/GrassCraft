package top.alittlebot.grass_craft;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import top.alittlebot.grass_craft.block.GrassBlocks;
import top.alittlebot.grass_craft.datagen.GrassProvider;
import top.alittlebot.grass_craft.effect.GrassEffects;
import top.alittlebot.grass_craft.entity.GrassEntity;
import top.alittlebot.grass_craft.event.GrassRegisterBrewingRecipesEvent;
import top.alittlebot.grass_craft.item.GrassItems;
import top.alittlebot.grass_craft.item.potion.GrassPotions;
import top.alittlebot.grass_craft.ui.GrassCreativeTab;

@Mod(GrassCraft.MOD_ID)
public class GrassCraft {
    public static final String MOD_ID = "grass_craft";

    public GrassCraft(IEventBus modEventBus) {
        GrassItems.ITEMS.register(modEventBus);
        GrassBlocks.BLOCKS.register(modEventBus);
        GrassEntity.ENTITIES.register(modEventBus);
        GrassEffects.EFFECTS.register(modEventBus);
        GrassPotions.POTIONS.register(modEventBus);
        GrassCreativeTab.TABS.register(modEventBus);

        NeoForge.EVENT_BUS.register(GrassRegisterBrewingRecipesEvent.class);

        modEventBus.addListener(GrassProvider::onGatherData);
    }
}
