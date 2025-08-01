package net.koala.kcurios.datagen;

import net.koala.kcurios.Kcurios;
import net.koala.kcurios.block.ModBlocks;
import net.koala.kcurios.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {

        List<ItemLike> STEEL_SMELTABLES = List.of(ModItems.RAW_STEEL,
                ModBlocks.STEEL_ORE, ModBlocks.DEEPSLATE_STEEL_ORE);

        //CRAFTING


        //steel block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STEEL_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.STEEL_INGOT.get())
                .unlockedBy("has_steel_ingot", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        //crushed amethyst block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRUSHED_AMETHYST_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.CRUSHED_AMETHYST.get())
                .unlockedBy("has_crushed_amethyst", has(ModItems.CRUSHED_AMETHYST)).save(recipeOutput);

        //crushed emerald block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRUSHED_EMERALD_BLOCK.get())
                .pattern("BBB")
                .pattern("BBB")
                .pattern("BBB")
                .define('B', ModItems.CRUSHED_EMERALDS.get())
                .unlockedBy("has_crushed_emeralds", has(ModItems.CRUSHED_EMERALDS)).save(recipeOutput);
        //chisel
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.CHISEL.get())
                .pattern("   ")
                .pattern(" S ")
                .pattern(" # ")
                .define('S', ModItems.STEEL_INGOT.get())
                .define('#', Items.STICK)
                .unlockedBy("has_crushed_emeralds", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        //Magic Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.MAGIC_BLOCK.get())
                .pattern("BBB")
                .pattern("BLB")
                .pattern("BBB")
                .define('B', Items.OBSIDIAN)
                .define('L', Items.LAVA_BUCKET)
                .unlockedBy("has_crushed_emeralds", has(ModItems.CRUSHED_EMERALDS)).save(recipeOutput);

        //amethyst lamp
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.AMETHYST_LAMP.get(), 4)
                .pattern("LBL")
                .pattern("BLB")
                .pattern("LBL")
                .define('B', Items.REDSTONE)
                .define('L', ModItems.CRUSHED_AMETHYST.get())
                .unlockedBy("has_crushed_amethyst", has(ModItems.CRUSHED_AMETHYST)).save(recipeOutput);

        //amethyst hammer
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.AMETHYST_HAMMER.get())
                .pattern("AAA")
                .pattern("AIA")
                .pattern(" I ")
                .define('A', ModItems.AMETHYST_INGOT.get())
                .define('I', Items.IRON_INGOT)
                .unlockedBy("has_amethyst_ingot", has(ModItems.AMETHYST_INGOT)).save(recipeOutput);

        //golden lasso
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.GOLDEN_LASSO.get())
                .pattern("GG ")
                .pattern("GA ")
                .pattern("  G")
                .define('G', Items.GOLD_INGOT)
                .define('A', Items.GOLDEN_APPLE)
                .unlockedBy("has_gold_ingot", has(Items.GOLD_INGOT)).save(recipeOutput);

        //emerald ingot
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.EMERALD_INGOT.get(), 1)
                .requires(ModItems.CRUSHED_EMERALDS, 2)
                .unlockedBy("has_crushed_emeralds", has(ModItems.CRUSHED_EMERALDS)).save(recipeOutput);

        //steel ingot
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT.get(), 9)
                .requires(ModBlocks.STEEL_BLOCK)
                .unlockedBy("has_steel_block", has(ModBlocks.STEEL_BLOCK)).save(recipeOutput);

        //crushed emeralds
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CRUSHED_EMERALDS.get(), 9)
                .requires(ModBlocks.CRUSHED_EMERALD_BLOCK)
                .unlockedBy("has_crushed_emerald_block", has(ModBlocks.CRUSHED_EMERALD_BLOCK))
                .save(recipeOutput, "kcurios:crushed_emeralds_haha");

        //crushed amethyst
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.CRUSHED_AMETHYST.get(), 9)
                .requires(ModBlocks.CRUSHED_AMETHYST_BLOCK)
                .unlockedBy("has_steel_block", has(ModBlocks.CRUSHED_AMETHYST_BLOCK)).save(recipeOutput);

        //amethyst ingot
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.AMETHYST_INGOT.get(), 1)
                .requires(ModItems.CRUSHED_AMETHYST, 2)
                .unlockedBy("has_crushed_amethyst", has(ModItems.CRUSHED_AMETHYST)).save(recipeOutput);


        stairBuilder(ModBlocks.STEEL_STAIRS.get(), Ingredient.of(ModItems.STEEL_INGOT)).group("steel")
                .unlockedBy("has_steel", has(ModItems.STEEL_INGOT)).save(recipeOutput);
        slab(recipeOutput, RecipeCategory.BUILDING_BLOCKS, ModBlocks.STEEL_SLAB.get(), ModItems.STEEL_INGOT.get());
        buttonBuilder(ModBlocks.STEEL_BUTTON.get(), Ingredient.of(ModItems.STEEL_INGOT)).group("steel")
                .unlockedBy("has_steel", has(ModItems.STEEL_INGOT)).save(recipeOutput);
        pressurePlate(recipeOutput, ModBlocks.STEEL_PRESSURE_PLATE.get(), ModItems.STEEL_INGOT.get());
        fenceBuilder(ModBlocks.STEEL_FENCE.get(), Ingredient.of(ModItems.STEEL_INGOT)).group("steel")
                .unlockedBy("has_steel", has(ModItems.STEEL_INGOT)).save(recipeOutput);
        fenceBuilder(ModBlocks.STEEL_FENCE_GATE.get(), Ingredient.of(ModItems.STEEL_INGOT)).group("steel")
                .unlockedBy("has_steel", has(ModItems.STEEL_INGOT)).save(recipeOutput);
        wall(recipeOutput, RecipeCategory.BUILDING_BLOCKS,ModBlocks.STEEL_WALL.get(), ModItems.STEEL_INGOT.get());

        doorBuilder(ModBlocks.STEEL_DOOR.get(), Ingredient.of(ModItems.STEEL_INGOT)).group("steel")
                .unlockedBy("has_steel", has(ModItems.STEEL_INGOT)).save(recipeOutput);
        trapdoorBuilder(ModBlocks.STEEL_TRAPDOOR.get(), Ingredient.of(ModItems.STEEL_INGOT)).group("steel")
                .unlockedBy("has_steel", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        //SMELTING BLASTING COOKING

        oreSmelting(recipeOutput, STEEL_SMELTABLES, RecipeCategory.MISC,
                ModItems.STEEL_INGOT.get(), 0.25f, 200, "steel");

        oreBlasting(recipeOutput, STEEL_SMELTABLES, RecipeCategory.MISC,
                ModItems.STEEL_INGOT.get(), 0.25f, 100, "steel");

    }


    //all written by kaupenjoe (i couldnt be bothered writing all that)
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, Kcurios.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }




}
