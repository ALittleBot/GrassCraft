package top.alittlebot.grass_craft.datagen.provider;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.neoforged.neoforge.client.model.generators.BlockModelProvider;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import top.alittlebot.grass_craft.GrassCraft;
import top.alittlebot.grass_craft.block.GrassBlocks;

public class GrassBlockProvider {

    public static class ModBlockStateProvider extends BlockStateProvider {
        public ModBlockStateProvider(PackOutput gen, ExistingFileHelper helper) {
            super(gen, GrassCraft.MOD_ID, helper);
        }

        @Override
        protected void registerStatesAndModels() {
            ResourceLocation grassTNTBottomTexture = modLoc("block/grass_tnt_bottom");
            ResourceLocation grassTNTTopTexture = modLoc("block/grass_tnt_top");
            ResourceLocation grassTNTSideTexture = modLoc("block/grass_tnt_side");
            ResourceLocation grassTNTBlockName = BuiltInRegistries.BLOCK.getKey(GrassBlocks.GRASS_TNT_BLOCK.get());
            ResourceLocation cookedGrassBlockBottomTexture = modLoc("block/cooked_grass_block_bottom");
            ResourceLocation cookedGrassBlockSideTexture = modLoc("block/cooked_grass_block_side");
            ResourceLocation cookedGrassBlockTopTexture = modLoc("block/cooked_grass_block_top");
            ResourceLocation cookedGrassBlockBlockName = BuiltInRegistries.BLOCK.getKey(GrassBlocks.COOKED_GRASS_BLOCK.get());
            simpleBlock(GrassBlocks.GRASS_TNT_BLOCK.get(), models().cubeBottomTop(
                    grassTNTBlockName.getPath(),
                    grassTNTSideTexture,
                    grassTNTBottomTexture,
                    grassTNTTopTexture
            ));
            simpleBlock(GrassBlocks.COOKED_GRASS_BLOCK.get(), models().cubeBottomTop(
                    cookedGrassBlockBlockName.getPath(),
                    cookedGrassBlockSideTexture,
                    cookedGrassBlockBottomTexture,
                    cookedGrassBlockTopTexture
            ));
            registerCropBlockModels(GrassBlocks.WEEDS_BLOCK.get(), GrassBlocks.WEEDS_ID);
        }

        // 代码来源: https://github.com/HiedaCamellia/WhisperGrove/blob/main/src/main/java/org/hiedacamellia/whispergrove/core/data/provider/StateProvider.java#L43
        private void registerCropBlockModels(Block block, String name) {
            getVariantBuilder(block).forAllStates(state -> {
                int age = state.getValue(CropBlock.AGE);
                return ConfiguredModel.builder()
                        .modelFile(models().crop(getCropBlockModelName(name, age), getCropBlockModelLocation(name, age)))
                        .build();
            });
        }
        private ResourceLocation getCropBlockModelLocation(String name, int age) {
            return ResourceLocation.fromNamespaceAndPath(GrassCraft.MOD_ID, getCropBlockModelName(name, age));
        }

        private String getCropBlockModelName(String name, int age) {
            return "block/" + name + "_stage" + switch (age) {
                case 0, 1 -> 0;
                case 2, 3 -> 1;
                case 4, 5, 6 -> 2;
                case 7 -> 3;
                default -> throw new IllegalArgumentException("Invalid age: " + age);
            };
        }
    }
}
