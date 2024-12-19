package top.alittlebot.grass_craft.datagen.provider.loot;

import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import org.jetbrains.annotations.NotNull;
import top.alittlebot.grass_craft.block.GrassBlocks;
import top.alittlebot.grass_craft.block.WeedsBlock;
import top.alittlebot.grass_craft.item.GrassItems;

import java.util.Set;

public class GrassBlockLootSubProvider extends BlockLootSubProvider {
    public GrassBlockLootSubProvider(HolderLookup.Provider provider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), provider);
    }

    @Override
    protected void generate() {
        dropSelf(GrassBlocks.GRASS_TNT_BLOCK.get());
        add(GrassBlocks.WEEDS_BLOCK.get(),
                createCropDrops(
                GrassBlocks.WEEDS_BLOCK.get(), // 作物的方块
                GrassItems.WEEDS_ITEM.get(), // 成熟的作物
                GrassItems.WEEDS_ITEM.get(), // 种子
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(GrassBlocks.WEEDS_BLOCK.get())
                        .setProperties(StatePropertiesPredicate.Builder.properties()
                                .hasProperty(WeedsBlock.AGE, WeedsBlock.MAX_AGE)) // 当作物达到最大年龄时
        ));
        dropSelf(GrassBlocks.VANILLA_ROD_BLOCK.get());
        dropSelf(GrassBlocks.COOKED_GRASS_BLOCK.get());
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
