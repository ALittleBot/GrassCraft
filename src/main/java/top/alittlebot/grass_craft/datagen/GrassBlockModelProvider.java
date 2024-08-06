package top.alittlebot.grass_craft.datagen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import top.alittlebot.grass_craft.GrassCraft;
import top.alittlebot.grass_craft.block.GrassBlocks;

public class GrassBlockModelProvider {
    public static void onGatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var packOutput = gen.getPackOutput();
        var helper = event.getExistingFileHelper();
        gen.addProvider(event.includeClient(), new ModBlockModelProvider(packOutput, helper));
    }

    public static class ModBlockModelProvider extends BlockStateProvider {
        public ModBlockModelProvider(PackOutput gen, ExistingFileHelper helper) {
            super(gen, GrassCraft.MOD_ID, helper);
        }

        @Override
        protected void registerStatesAndModels() {
            Block block = GrassBlocks.GRASS_TNT_BLOCK.get();
            ResourceLocation bottomTexture = modLoc("block/grass_tnt_bottom");
            ResourceLocation topTexture = modLoc("block/grass_tnt_top");
            ResourceLocation sideTexture = modLoc("block/grass_tnt_side");
            ResourceLocation blockName = BuiltInRegistries.BLOCK.getKey(block);
            simpleBlock(block, models().cubeBottomTop(
                    blockName.getPath(),
                    sideTexture,
                    bottomTexture,
                    topTexture
            ));
        }
    }
}
