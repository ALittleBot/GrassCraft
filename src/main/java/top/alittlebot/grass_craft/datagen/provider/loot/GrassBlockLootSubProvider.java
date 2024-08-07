package top.alittlebot.grass_craft.datagen.provider.loot;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;
import top.alittlebot.grass_craft.block.GrassBlocks;

import java.util.Set;

public class GrassBlockLootSubProvider extends BlockLootSubProvider {
    public GrassBlockLootSubProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        dropSelf(GrassBlocks.GRASS_TNT_BLOCK.get());
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return GrassBlocks.BLOCKS.getEntries()
                .stream()
                .flatMap(d -> d.asOptional().stream())
                .map(Block.class::cast)
                ::iterator;
    }
}
