package top.alittlebot.grass_craft.datagen.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.WritableRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.util.ProblemReporter;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import top.alittlebot.grass_craft.datagen.provider.loot.GrassBlockLootSubProvider;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class GrassLootTableProvider extends LootTableProvider {
    public GrassLootTableProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, Set.of(), List.of(
                new LootTableProvider.SubProviderEntry(GrassBlockLootSubProvider::new, LootContextParamSets.BLOCK)
        ), pRegistries);
    }

    @Override
    protected void validate(WritableRegistry<LootTable> writableregistry, ValidationContext validationcontext, ProblemReporter.Collector problemreporter$collector) {
        // Do not validate against all registered loot tables
    }
}
