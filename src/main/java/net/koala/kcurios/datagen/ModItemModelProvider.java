package net.koala.kcurios.datagen;

import net.koala.kcurios.Kcurios;
import net.koala.kcurios.block.ModBlocks;
import net.koala.kcurios.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;


public class ModItemModelProvider extends ItemModelProvider {


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Kcurios.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        //STEEL
        basicItem(ModItems.STEEL_INGOT.get());
        basicItem(ModItems.RAW_STEEL.get());
        buttonItem(ModBlocks.STEEL_BUTTON, ModBlocks.STEEL_BLOCK);
        fenceItem(ModBlocks.STEEL_FENCE, ModBlocks.STEEL_BLOCK);
        wallItem(ModBlocks.STEEL_WALL, ModBlocks.STEEL_BLOCK);
        basicItem(ModBlocks.STEEL_DOOR.asItem());



        //EMERALD
        basicItem(ModItems.CRUSHED_EMERALDS.get());
        basicItem(ModItems.EMERALD_INGOT.get());



        //AMETHYST
        basicItem(ModItems.CRUSHED_AMETHYST.get());
        basicItem(ModItems.AMETHYST_INGOT.get());
        handheldItem(ModItems.AMETHYST_HAMMER);


        //FOOD
        basicItem(ModItems.BELL_PEPPER.get());

        //FUEL
        basicItem(ModItems.DRY_ICE.get());
        basicItem(ModItems.FLOUR.get());

        //TOOLS
        basicItem(ModItems.CHISEL.get());

        handheldItem(ModItems.EMERALD_PICKAXE);
        handheldItem(ModItems.EMERALD_SWORD);
        handheldItem(ModItems.EMERALD_AXE);
        handheldItem(ModItems.EMERALD_SHOVEL);
        handheldItem(ModItems.EMERALD_HOE);



        basicItem(ModItems.GOLDEN_LASSO.get());
    }


    private ItemModelBuilder handheldItem(DeferredItem<?> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Kcurios.MOD_ID, "item/" + item.getId().getPath()));

    }


    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(Kcurios.MOD_ID
                        , "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(Kcurios.MOD_ID
                        , "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(Kcurios.MOD_ID
                        , "block/" + baseBlock.getId().getPath()));
    }





}
