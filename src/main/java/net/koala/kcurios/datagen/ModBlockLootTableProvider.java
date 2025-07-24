package net.koala.kcurios.datagen;

import net.koala.kcurios.block.ModBlocks;
import net.koala.kcurios.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {


    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(),registries);
    }

    @Override
    protected void generate() {

        dropSelf(ModBlocks.CRUSHED_EMERALD_BLOCK.get());
        dropSelf(ModBlocks.CRUSHED_AMETHYST_BLOCK.get());
        dropSelf(ModBlocks.MAGIC_BLOCK.get());
        dropSelf(ModBlocks.STEEL_BLOCK.get());
        dropSelf(ModBlocks.AMETHYST_LAMP.get());

        dropSelf(ModBlocks.STEEL_STAIRS.get());

        add(ModBlocks.STEEL_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.STEEL_SLAB.get()));

        dropSelf(ModBlocks.STEEL_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.STEEL_BUTTON.get());
        dropSelf(ModBlocks.STEEL_FENCE.get());
        dropSelf(ModBlocks.STEEL_FENCE_GATE.get());
        dropSelf(ModBlocks.STEEL_WALL.get());
        dropSelf(ModBlocks.STEEL_TRAPDOOR.get());

        add(ModBlocks.STEEL_DOOR.get(),
                block -> createDoorTable(ModBlocks.STEEL_DOOR.get()));



        add(ModBlocks.STEEL_ORE.get(),
                block -> createOreDrop(ModBlocks.STEEL_ORE.get(), ModItems.RAW_STEEL.get()));
        add(ModBlocks.DEEPSLATE_STEEL_ORE.get(),
                block -> createMultipleOreDrops(ModBlocks.DEEPSLATE_STEEL_ORE.get(), ModItems.RAW_STEEL.get(), 2f, 6f));
    }

    //this is the copper ore drop method but changed
    protected LootTable.Builder createMultipleOreDrops(Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable( block,
                this.applyExplosionDecay( block, LootItem.lootTableItem(item) // changed from items.raw_copper to item to make it multiuse
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))  //from 2f anf 5f to min and max drops
                        .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))));
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
