package top.alittlebot.grass_craft;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import top.alittlebot.grass_craft.datagen.GrassItemModelProvider;
import top.alittlebot.grass_craft.datagen.GrassLanguageProvider;
import top.alittlebot.grass_craft.datagen.GrassRecipeProvider;
import top.alittlebot.grass_craft.effect.GrassEffects;
import top.alittlebot.grass_craft.item.GrassCreativeTab;
import top.alittlebot.grass_craft.item.GrassItems;

@Mod(GrassCraft.MOD_ID)
public class GrassCraft {

    public static final String MOD_ID = "grass_craft";

    public GrassCraft(IEventBus modEventBus) {
        GrassItems.ITEMS.register(modEventBus);
        GrassEffects.EFFECTS.register(modEventBus);
        GrassCreativeTab.TABS.register(modEventBus);

        modEventBus.addListener(GrassLanguageProvider::onGatherData);
        modEventBus.addListener(GrassItemModelProvider::onGatherData);
        modEventBus.addListener(GrassRecipeProvider::onGatherData);
    }
}
