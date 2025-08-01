package net.koala.kcurios.datagen;

import net.koala.kcurios.Kcurios;
import net.koala.kcurios.block.ModBlocks;
import net.koala.kcurios.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Kcurios.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.CRUSHED_AMETHYST_BLOCK.get())
                .add(ModBlocks.CRUSHED_EMERALD_BLOCK.get())
                .add(ModBlocks.STEEL_BLOCK.get())
                .add(ModBlocks.STEEL_ORE.get())
                .add(ModBlocks.DEEPSLATE_STEEL_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.STEEL_BLOCK.get())
                .add(ModBlocks.DEEPSLATE_STEEL_ORE.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.CRUSHED_EMERALD_BLOCK.get())
                .add(ModBlocks.STEEL_ORE.get())
                .add(ModBlocks.CRUSHED_AMETHYST_BLOCK.get());

        tag(BlockTags.FENCES).add(ModBlocks.STEEL_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.STEEL_FENCE_GATE.get());
        tag(BlockTags.WALLS).add(ModBlocks.STEEL_WALL.get());

        tag(ModTags.Blocks.NEEDS_EMERALD_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL); // every block diamond can mine emerald can

        tag(ModTags.Blocks.INCORRECT_FOR_EMERALD_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_DIAMOND_TOOL) // if added to here
                .remove(ModTags.Blocks.NEEDS_EMERALD_TOOL); // it removes it from here? kinda confusing

        tag(ModTags.Blocks.NEEDS_AMETHYST_TOOL)
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_AMETHYST_TOOL)
                .addTag(BlockTags.INCORRECT_FOR_IRON_TOOL)
                .remove(ModTags.Blocks.NEEDS_AMETHYST_TOOL);

        tag(ModTags.Blocks.MINEABLE_WITH_HAMMER)
                .addTag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(BlockTags.MINEABLE_WITH_AXE)
                .addTag(BlockTags.MINEABLE_WITH_SHOVEL);
    }
}
