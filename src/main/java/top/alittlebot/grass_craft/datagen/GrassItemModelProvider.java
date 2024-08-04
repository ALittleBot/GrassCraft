package top.alittlebot.grass_craft.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import top.alittlebot.grass_craft.GrassCraft;
import top.alittlebot.grass_craft.item.GrassItems;

public class GrassItemModelProvider {
    public static void onGatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var packOutput = gen.getPackOutput();
        var helper = event.getExistingFileHelper();
        gen.addProvider(event.includeClient(), new ModItemModelProvider(packOutput, helper));
    }

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
        }
    }
}
