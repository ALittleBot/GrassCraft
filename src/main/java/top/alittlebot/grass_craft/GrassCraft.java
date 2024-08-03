package top.alittlebot.grass_craft;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import top.alittlebot.grass_craft.datagen.GrassItemModelProvider;
import top.alittlebot.grass_craft.datagen.GrassLanguageProvider;
import top.alittlebot.grass_craft.datagen.GrassRecipeProvider;
import top.alittlebot.grass_craft.effect.GrassEffects;
import top.alittlebot.grass_craft.item.GrassItems;

@Mod(GrassCraft.MODID)
public class GrassCraft {
    public static final String MODID = "grass_craft";

    public GrassCraft(IEventBus modEventBus) {
        GrassItems.registerItems(modEventBus);
        modEventBus.addListener(GrassLanguageProvider::onGatherData);
        modEventBus.addListener(GrassItemModelProvider::onGatherData);
        modEventBus.addListener(GrassRecipeProvider::onGatherData);
        GrassEffects.registerItems(modEventBus);
    }
}
