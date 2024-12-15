package top.alittlebot.grass_craft.datagen;

import net.minecraft.core.HolderLookup;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import top.alittlebot.grass_craft.datagen.provider.*;

import java.util.concurrent.CompletableFuture;

public class GrassProvider {
    public static void onGatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var packOutput = gen.getPackOutput();
        var registries = event.getLookupProvider();
        var helper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        gen.addProvider(event.includeClient(), new GrassBlockProvider.ModBlockStateProvider(packOutput, helper));
        gen.addProvider(event.includeClient(), new GrassLanguageProvider.EnglishLanguageProvider(packOutput));
        gen.addProvider(event.includeClient(), new GrassLanguageProvider.ChineseLanguageProvider(packOutput));
        gen.addProvider(event.includeClient(), new GrassRecipeProvider.ModRecipeProvider(packOutput, registries));
        gen.addProvider(event.includeClient(), new GrassItemModelProvider.ModItemModelProvider(packOutput, helper));
        gen.addProvider(event.includeServer(), new GrassLootTableProvider(packOutput, lookupProvider));
    }
}
