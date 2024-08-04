package top.alittlebot.grass_craft.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.jetbrains.annotations.NotNull;
import top.alittlebot.grass_craft.GrassCraft;
import top.alittlebot.grass_craft.item.GrassItems;

import java.util.concurrent.CompletableFuture;

public class GrassRecipeProvider {

    public static void onGatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var packOutput = gen.getPackOutput();
        var registries = event.getLookupProvider();
        gen.addProvider(event.includeClient(), new ModRecipeProvider(packOutput, registries));
    }

    public static class ModRecipeProvider extends RecipeProvider {

        public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
            super(output, registries);
        }

        @Override
        protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GrassItems.GRASS_STICK_ITEM.get(), 4)
                    .group(GrassCraft.MOD_ID)
                    .define('#', Items.SHORT_GRASS)
                    .pattern("#")
                    .pattern("#")
                    .unlockedBy(getHasName(Items.SHORT_GRASS), has(Items.SHORT_GRASS))
                    .save(recipeOutput);
            SimpleCookingRecipeBuilder.smelting(
                            Ingredient.of(Items.SHORT_GRASS),
                            RecipeCategory.MISC,
                            GrassItems.VANILLA_ITEM.get(),
                            0.1f,
                            200
                    ).unlockedBy(getHasName(Items.SHORT_GRASS), has(Items.SHORT_GRASS))
                    .save(recipeOutput);
        }
    }
}
