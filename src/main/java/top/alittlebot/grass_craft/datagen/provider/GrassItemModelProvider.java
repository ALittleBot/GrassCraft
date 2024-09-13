package top.alittlebot.grass_craft.datagen.provider;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import top.alittlebot.grass_craft.GrassCraft;
import top.alittlebot.grass_craft.block.GrassBlocks;
import top.alittlebot.grass_craft.item.GrassItems;

public class GrassItemModelProvider {

    public static class ModItemModelProvider extends ItemModelProvider {
        public ModItemModelProvider(PackOutput gen, ExistingFileHelper helper) {
            super(gen, GrassCraft.MOD_ID, helper);
        }

        @Override
        protected void registerModels() {
            this.singleTexture(GrassItems.GRASS_STICK_ID, ResourceLocation.withDefaultNamespace("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(GrassCraft.MOD_ID, "item/" + GrassItems.GRASS_STICK_ID));
            this.singleTexture(GrassItems.STAFF_OF_GRASS_ID, ResourceLocation.withDefaultNamespace("item/handheld"), "layer0", ResourceLocation.fromNamespaceAndPath(GrassCraft.MOD_ID, "item/" + GrassItems.STAFF_OF_GRASS_ID));
            this.singleTexture(GrassItems.GRASS_BALL_ID, ResourceLocation.withDefaultNamespace("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(GrassCraft.MOD_ID, "item/" + GrassItems.GRASS_BALL_ID));
            this.singleTexture(GrassItems.VANILLA_ID, ResourceLocation.withDefaultNamespace("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(GrassCraft.MOD_ID, "item/" + GrassItems.VANILLA_ID));
            this.singleTexture(GrassItems.VANILLA_INGOT_ID, ResourceLocation.withDefaultNamespace("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(GrassCraft.MOD_ID, "item/" + GrassItems.VANILLA_INGOT_ID));
            this.singleTexture(GrassItems.VANILLA_GLOVE_ID, ResourceLocation.withDefaultNamespace("item/handheld"), "layer0", ResourceLocation.fromNamespaceAndPath(GrassCraft.MOD_ID, "item/" + GrassItems.VANILLA_GLOVE_ID));
            this.singleTexture(GrassItems.VANILLA_PUREE_ID, ResourceLocation.withDefaultNamespace("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(GrassCraft.MOD_ID, "item/" + GrassItems.VANILLA_PUREE_ID));
            this.singleTexture(GrassItems.GRASS_ON_A_STICK_ID, ResourceLocation.withDefaultNamespace("item/handheld_rod"), "layer0", ResourceLocation.fromNamespaceAndPath(GrassCraft.MOD_ID, "item/" + GrassItems.GRASS_ON_A_STICK_ID));
            this.withExistingParent(GrassItems.GRASS_MOB_SPAWN_EGG_ID, this.mcLoc("item/template_spawn_egg"));
            this.singleTexture(GrassBlocks.WEEDS_ID, ResourceLocation.withDefaultNamespace("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(GrassCraft.MOD_ID, "item/" + GrassBlocks.WEEDS_ID));
            this.singleTexture(GrassItems.GRASS_FISH_ID, ResourceLocation.withDefaultNamespace("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(GrassCraft.MOD_ID, "item/" + GrassItems.GRASS_FISH_ID));
            genBlockItemModel(GrassBlocks.GRASS_TNT_BLOCK);
            genBlockItemModel(GrassBlocks.VANILLA_ROD_BLOCK);
        }

        private void genBlockItemModel(DeferredHolder<Block, Block> block) {
            String id = block.getId().getPath();
            withExistingParent(id, ResourceLocation.fromNamespaceAndPath(GrassCraft.MOD_ID, "block/" + id));
        }
    }
}
