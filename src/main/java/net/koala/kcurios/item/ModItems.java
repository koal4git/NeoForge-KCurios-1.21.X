package net.koala.kcurios.item;

import net.koala.kcurios.Kcurios;
import net.koala.kcurios.item.custom.Chiseltem;
import net.koala.kcurios.item.custom.FuelItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Kcurios.MOD_ID);

    public static final DeferredItem<Item> CRUSHED_EMERALDS = ITEMS.register("crushed_emeralds",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> EMERALD_INGOT = ITEMS.register("emerald_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CRUSHED_AMETHYST = ITEMS.register("crushed_amethyst",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_STEEL = ITEMS.register("raw_steel",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new Chiseltem(new Item.Properties().durability(64)));

    public static final DeferredItem<Item> BELL_PEPPER = ITEMS.register("bell_pepper",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BELL_PEPPER)));

    public static final DeferredItem<Item> DRY_ICE = ITEMS.register("dry_ice",
            () -> new FuelItem(new Item.Properties(), 400));


    public static final DeferredItem<Item> FLOUR = ITEMS.register("flour",
            () -> new Item(new Item.Properties()) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {

                    tooltipComponents.add(Component.translatable("tooltip.kcurios.flour.tooltip"));
                    super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
                }
            });




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
