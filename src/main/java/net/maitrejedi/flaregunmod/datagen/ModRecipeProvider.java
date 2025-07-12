package net.maitrejedi.flaregunmod.datagen;

import net.maitrejedi.flaregunmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        final Map<DeferredItem<Item>, Item> COLORED_FLARE_RECIPES = Map.ofEntries(
                Map.entry(ModItems.FLARE_BLACK, Items.BLACK_DYE),
                Map.entry(ModItems.FLARE_BLUE, Items.BLUE_DYE),
                Map.entry(ModItems.FLARE_BROWN, Items.BROWN_DYE),
                Map.entry(ModItems.FLARE_CYAN, Items.CYAN_DYE),
                Map.entry(ModItems.FLARE_GRAY, Items.GRAY_DYE),
                Map.entry(ModItems.FLARE_GREEN, Items.GREEN_DYE),
                Map.entry(ModItems.FLARE_LIGHT_BLUE, Items.LIGHT_BLUE_DYE),
                Map.entry(ModItems.FLARE_LIGHT_GRAY, Items.LIGHT_GRAY_DYE),
                Map.entry(ModItems.FLARE_LIME, Items.LIME_DYE),
                Map.entry(ModItems.FLARE_MAGENTA, Items.MAGENTA_DYE),
                Map.entry(ModItems.FLARE_ORANGE, Items.ORANGE_DYE),
                Map.entry(ModItems.FLARE_PINK, Items.PINK_DYE),
                Map.entry(ModItems.FLARE_PURPLE, Items.PURPLE_DYE),
                Map.entry(ModItems.FLARE_RED, Items.RED_DYE),
                Map.entry(ModItems.FLARE_WHITE, Items.WHITE_DYE),
                Map.entry(ModItems.FLARE_YELLOW, Items.YELLOW_DYE)
        );

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FLARE.get(), 4)
                .requires(Items.COPPER_INGOT)
                .requires(Items.COAL)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(recipeOutput, "flaregunmod:flare_from_coal");

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FLARE.get(), 4)
                .requires(Items.COPPER_INGOT)
                .requires(Items.CHARCOAL)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(recipeOutput, "flaregunmod:flare_from_charcoal");

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.FLARE_GUN.get())
                .pattern("   ")
                .pattern("BBB")
                .pattern(" CA")
                .define('B', Items.COPPER_INGOT)
                .define('A', Items.STICK)
                .define('C', Items.LEVER)
                .unlockedBy("has_copper_ingot", has(Items.COPPER_INGOT))
                .save(recipeOutput);

        coloredFlares(recipeOutput, COLORED_FLARE_RECIPES);
    }

    protected static void coloredFlares(RecipeOutput recipeOutput, Map<DeferredItem<Item>, Item> coloredFlareRecipes) {
        for (Map.Entry<DeferredItem<Item>, Item> entry : coloredFlareRecipes.entrySet()) {
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, entry.getKey(), 8)
                    .pattern("BBB")
                    .pattern("BAB")
                    .pattern("BBB")
                    .define('B', ModItems.FLARE)
                    .define('A', entry.getValue())
                    .unlockedBy("has_flare", has(ModItems.FLARE))
                    .save(recipeOutput);
        }
    }
}
