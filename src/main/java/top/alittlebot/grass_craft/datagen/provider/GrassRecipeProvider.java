package top.alittlebot.grass_craft.datagen.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import top.alittlebot.grass_craft.GrassCraft;
import top.alittlebot.grass_craft.item.GrassItems;

import java.util.concurrent.CompletableFuture;

public class GrassRecipeProvider {

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
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GrassItems.STAFF_OF_GRASS_ITEM.get(), 1)
                    .group(GrassCraft.MOD_ID)
                    .define('A', GrassItems.VANILLA_INGOT_ITEM.get())
                    .define('B', GrassItems.GRASS_STICK_ITEM.get())
                    .pattern("A")
                    .pattern("A")
                    .pattern("B")
                    .unlockedBy(getHasName(GrassItems.VANILLA_INGOT_ITEM.get()), has(GrassItems.VANILLA_INGOT_ITEM.get()))
                    .save(recipeOutput);
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GrassItems.GRASS_BALL_ITEM.get(), 4)
                    .group(GrassCraft.MOD_ID)
                    .define('A', GrassItems.VANILLA_ITEM.get())
                    .pattern("A")
                    .unlockedBy(getHasName(GrassItems.VANILLA_ITEM.get()), has(GrassItems.VANILLA_ITEM.get()))
                    .save(recipeOutput);
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, GrassItems.VANILLA_GLOVE_ITEM.get(), 1)
                    .group(GrassCraft.MOD_ID)
                    .define('A', GrassItems.VANILLA_INGOT_ITEM.get())
                    .define('B', Items.SHORT_GRASS)
                    .pattern("AA")
                    .pattern("BB")
                    .unlockedBy(getHasName(GrassItems.VANILLA_INGOT_ITEM.get()), has(GrassItems.VANILLA_INGOT_ITEM.get()))
                    .save(recipeOutput);


            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GrassItems.VANILLA_INGOT_ITEM.get(), 1)
                    .group(GrassCraft.MOD_ID)
                    .requires(Items.COPPER_INGOT, 4)
                    .requires(GrassItems.VANILLA_ITEM.get(), 4)
                    .unlockedBy(getHasName(GrassItems.VANILLA_ITEM.get()), has(GrassItems.VANILLA_ITEM.get()))
                    .save(recipeOutput);
            ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, GrassItems.GRASS_TNT_ITEM.get(), 1)
                    .group(GrassCraft.MOD_ID)
                    .requires(Items.TNT, 1)
                    .requires(GrassItems.VANILLA_ITEM.get(), 1)
                    .unlockedBy(getHasName(GrassItems.VANILLA_ITEM.get()), has(GrassItems.VANILLA_ITEM.get()))
                    .save(recipeOutput);

            SimpleCookingRecipeBuilder.smelting(
                    Ingredient.of(Items.SHORT_GRASS),
                    RecipeCategory.MISC,
                    GrassItems.VANILLA_ITEM.get(),
                    0.1f,
                    200
                )
                    .group(GrassCraft.MOD_ID)
                    .unlockedBy(getHasName(Items.SHORT_GRASS), has(Items.SHORT_GRASS))
                    .save(recipeOutput);
        }
    }
}