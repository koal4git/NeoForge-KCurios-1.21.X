package net.koala.kcurios.block.custom;

import net.koala.kcurios.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class MagicBlock extends Block {

    public MagicBlock(Properties properties) {
        super(properties);
    }


    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hitResult) {
        level.playSound(player, pos, SoundEvents.BREEZE_SHOOT, SoundSource.BLOCKS, 1f, 1f);
        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {

        if (entity instanceof ItemEntity itemEntity){
            if (itemEntity.getItem().getItem() == Items.ROTTEN_FLESH) {
                itemEntity.setItem(new ItemStack(Items.LEATHER, itemEntity.getItem().getCount()));
                level.playSound(entity, pos, SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 1f, 0.5f);
            }else if (itemEntity.getItem().getItem() == Items.WHEAT) {
                itemEntity.setItem(new ItemStack(Items.STRING, itemEntity.getItem().getCount() * 2 ));
                level.playSound(entity, pos, SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 1f, 0.5f);
            }else if (itemEntity.getItem().getItem() == ModItems.CRUSHED_EMERALDS.get()) {
                itemEntity.setItem(new ItemStack(ModItems.EMERALD_INGOT.get(), itemEntity.getItem().getCount()));
                level.playSound(entity, pos, SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 1f, 0.5f);
            }
        }


        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {

        tooltipComponents.add(Component.translatable("tooltip.kcurios.magic_block.tooltip"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
