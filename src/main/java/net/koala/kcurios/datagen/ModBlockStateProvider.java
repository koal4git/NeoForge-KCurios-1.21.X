package net.koala.kcurios.datagen;

import net.koala.kcurios.Kcurios;
import net.koala.kcurios.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
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
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

}
