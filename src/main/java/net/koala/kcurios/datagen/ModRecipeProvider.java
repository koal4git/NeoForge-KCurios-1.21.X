package net.koala.kcurios.datagen;

import net.koala.kcurios.block.ModBlocks;
import net.koala.kcurios.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
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
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRUSHED_EMERALD_BLOCK.get())
                .pattern("   ")
                .pattern(" S ")
                .pattern(" # ")
                .define('S', ModItems.STEEL_INGOT.get())
                .define('#', Items.STICK)
                .unlockedBy("has_crushed_emeralds", has(ModItems.STEEL_INGOT)).save(recipeOutput);

        //Magic Block
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CRUSHED_EMERALD_BLOCK.get())
                .pattern("BBB")
                .pattern("BLB")
                .pattern("BBB")
                .define('B', Items.OBSIDIAN)
                .define('L', Items.LAVA_BUCKET)
                .unlockedBy("has_crushed_emeralds", has(ModItems.CRUSHED_EMERALDS)).save(recipeOutput);

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



        //SMELTING BLASTING COOKING

        oreSmelting(recipeOutput, STEEL_SMELTABLES, RecipeCategory.MISC,
                ModItems.STEEL_INGOT.get(), 0.25f, 200, "steel");

        oreBlasting(recipeOutput, STEEL_SMELTABLES, RecipeCategory.MISC,
                ModItems.STEEL_INGOT.get(), 0.25f, 100, "steel");
    }
}
