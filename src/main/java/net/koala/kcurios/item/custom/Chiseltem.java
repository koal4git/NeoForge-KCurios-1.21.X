package net.koala.kcurios.item.custom;

import net.koala.kcurios.block.ModBlocks;
import net.koala.kcurios.component.ModDataComponents;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Map;

public class Chiseltem extends Item {

    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.DEEPSLATE, Blocks.DEEPSLATE_BRICKS,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    Blocks.EMERALD_BLOCK, ModBlocks.CRUSHED_EMERALD_BLOCK.get(),
                    Blocks.AMETHYST_BLOCK, ModBlocks.CRUSHED_AMETHYST_BLOCK.get()
            );

    public Chiseltem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if (CHISEL_MAP.containsKey(clickedBlock)) {
            if(!level.isClientSide()) {
                //ONLY ON SERVER !!!!
                level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());

                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), context.getPlayer(),
                    item -> context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);


                context.getItemInHand().set(ModDataComponents.COORDINATES, context.getClickedPos());

            }
        }

        return InteractionResult.SUCCESS;
    }


    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {

        if(!Screen.hasShiftDown()) {
            tooltipComponents.add(Component.translatable("tooltip.kcurios.shift_down"));
        } else {
            tooltipComponents.add(Component.translatable("tooltip.kcurios.chisel.tooltip"));

        }

        if (stack.get(ModDataComponents.COORDINATES) != null) {
           tooltipComponents.add(Component.literal("Last Block Changed at " + stack.get(ModDataComponents.COORDINATES)));
        }


        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);


    }
}
