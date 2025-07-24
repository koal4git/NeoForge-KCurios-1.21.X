package net.koala.kcurios.datagen;

import net.koala.kcurios.Kcurios;
import net.koala.kcurios.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {


    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Kcurios.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        //steel blocks
        blockWithItem(ModBlocks.STEEL_BLOCK);
        blockWithItem(ModBlocks.STEEL_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_STEEL_ORE);

        //crushed blocks
        blockWithItem(ModBlocks.CRUSHED_AMETHYST_BLOCK);
        blockWithItem(ModBlocks.CRUSHED_EMERALD_BLOCK);

        //advanced blocks
        blockWithItem(ModBlocks.MAGIC_BLOCK);

        //steel non blocks
        stairsBlock(ModBlocks.STEEL_STAIRS.get(), blockTexture(ModBlocks.STEEL_BLOCK.get()));
        slabBlock(ModBlocks.STEEL_SLAB.get(), blockTexture(ModBlocks.STEEL_BLOCK.get()), blockTexture(ModBlocks.STEEL_BLOCK.get()));

        buttonBlock(ModBlocks.STEEL_BUTTON.get(), blockTexture(ModBlocks.STEEL_BLOCK.get()));
        pressurePlateBlock(ModBlocks.STEEL_PRESSURE_PLATE.get(), blockTexture(ModBlocks.STEEL_BLOCK.get()));
        fenceBlock(ModBlocks.STEEL_FENCE.get(), blockTexture(ModBlocks.STEEL_BLOCK.get()));
        fenceGateBlock(ModBlocks.STEEL_FENCE_GATE.get(), blockTexture(ModBlocks.STEEL_BLOCK.get()));

        trapdoorBlockWithRenderType(ModBlocks.STEEL_TRAPDOOR.get(),modLoc("block/steel_trapdoor"),true, "cutout" );
        wallBlock(ModBlocks.STEEL_WALL.get(), blockTexture(ModBlocks.STEEL_BLOCK.get()));

        doorBlockWithRenderType(ModBlocks.STEEL_DOOR.get(), modLoc("block/steel_door_bottom"), modLoc("block/steel_door_top"), "cutout"  );

        blockItem(ModBlocks.STEEL_STAIRS);
        blockItem(ModBlocks.STEEL_SLAB);
        blockItem(ModBlocks.STEEL_PRESSURE_PLATE);
        blockItem(ModBlocks.STEEL_FENCE_GATE);
        blockItem(ModBlocks.STEEL_TRAPDOOR, "_bottom");



    }





    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock){
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("kcurios:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix){
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("kcurios:block/" + deferredBlock.getId().getPath() + appendix));
    }

}
