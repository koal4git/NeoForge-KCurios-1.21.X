package net.koala.kcurios.item;

import net.koala.kcurios.Kcurios;
import net.koala.kcurios.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {


    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Kcurios.MOD_ID);


    public static final Supplier<CreativeModeTab> KCURIOS_ITEMS_TAB = CREATIVE_MODE_TAB.register("kcurios_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CRUSHED_AMETHYST.get()))
                    .title(Component.translatable("creativetab.kcurios.kcurios_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.CRUSHED_AMETHYST);
                        output.accept(ModItems.AMETHYST_INGOT);
                        output.accept(ModItems.CRUSHED_EMERALDS);
                        output.accept(ModItems.EMERALD_INGOT);
                        output.accept(ModItems.RAW_STEEL);
                        output.accept(ModItems.STEEL_INGOT);
                        output.accept(ModItems.CHISEL);


                        //emerald tools and that
                        output.accept(ModItems.EMERALD_PICKAXE);
                        output.accept(ModItems.EMERALD_SWORD);
                        output.accept(ModItems.EMERALD_AXE);
                        output.accept(ModItems.EMERALD_SHOVEL);
                        output.accept(ModItems.EMERALD_HOE);

                        output.accept(ModItems.EMERALD_HELMET);
                        output.accept(ModItems.EMERALD_CHESTPLATE);
                        output.accept(ModItems.EMERALD_LEGGINGS);
                        output.accept(ModItems.EMERALD_BOOTS);

                        output.accept(ModItems.AMETHYST_HAMMER);


                        output.accept(ModItems.BELL_PEPPER);
                        output.accept(ModItems.DRY_ICE);
                        output.accept(ModItems.FLOUR);

                        output.accept(ModItems.PISTOL);



                        output.accept(ModItems.GOLDEN_LASSO);
                    }).build());


    public static final Supplier<CreativeModeTab> KCURIOS_BLOCKS_TAB = CREATIVE_MODE_TAB.register("kcurios_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.CRUSHED_AMETHYST_BLOCK.get()))
                    .withTabsBefore(ResourceLocation.fromNamespaceAndPath(Kcurios.MOD_ID, "kcurios_items_tab"))
                    .title(Component.translatable("creativetab.kcurios.kcurios_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.CRUSHED_EMERALD_BLOCK);
                        output.accept(ModBlocks.CRUSHED_AMETHYST_BLOCK);
                        output.accept(ModBlocks.STEEL_ORE);
                        output.accept(ModBlocks.DEEPSLATE_STEEL_ORE);
                        output.accept(ModBlocks.STEEL_BLOCK);
                        output.accept(ModBlocks.MAGIC_BLOCK);
                        output.accept(ModBlocks.AMETHYST_LAMP);


                        //steel non blocks
                        output.accept(ModBlocks.STEEL_STAIRS);
                        output.accept(ModBlocks.STEEL_SLAB);

                        output.accept(ModBlocks.STEEL_PRESSURE_PLATE);
                        output.accept(ModBlocks.STEEL_BUTTON);

                        output.accept(ModBlocks.STEEL_FENCE);
                        output.accept(ModBlocks.STEEL_FENCE_GATE);
                        output.accept(ModBlocks.STEEL_WALL);

                        output.accept(ModBlocks.STEEL_DOOR);
                        output.accept(ModBlocks.STEEL_TRAPDOOR);


                    }).build());


    public static void register(IEventBus eventBus) {

        CREATIVE_MODE_TAB.register(eventBus);
    }
}
