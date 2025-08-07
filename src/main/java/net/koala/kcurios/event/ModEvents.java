package net.koala.kcurios.event;

import net.koala.kcurios.Kcurios;
import net.koala.kcurios.item.ModItems;
import net.koala.kcurios.item.custom.HammerItem;
import net.koala.kcurios.item.custom.LassoItem;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

import java.util.HashSet;
import java.util.Set;


@EventBusSubscriber(modid = Kcurios.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

    //when youre in the actual game actual features yknow
    //cleint is rendering
    //server is anything that cant be done on the client, be healed spawn something would need a hack client yknow

    //gameclient (game bus, forge bus, main bus only on the logical client) ONLY EXTENDS EVENT
    //gameserver

    //for like registering and stuff
    //modlcient IMPLEMENTS IMODBUSEVENT
    //modserver


    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    // Done with the help of https://github.com/CoFH/CoFHCore/blob/1.19.x/src/main/java/cofh/core/event/AreaEffectEvents.java
    // Don't be a jerk License
    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();


        // if have hammer item and player then do this stuff
        if(mainHandItem.getItem() instanceof HammerItem hammer && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if(HARVESTED_BLOCKS.contains(initialBlockPos)) { // is this already in a set?
                return;
            }

            //call hammer event through all positions hammer item gives
            for(BlockPos pos : HammerItem.getBlocksToBeDestroyed(2, initialBlockPos, serverPlayer)) {
                if(pos == initialBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) { // also ensure block is correct tool
                    continue;
                }



                HARVESTED_BLOCKS.add(pos); // add to HARVESTED_BLOCKS
                serverPlayer.gameMode.destroyBlock(pos); // DESTROY blocks
                HARVESTED_BLOCKS.remove(pos); // then remove from HARVEST_BLOCKS
            }

            mainHandItem.setDamageValue(mainHandItem.getDamageValue() - 24);


        }
    }

    // Lasso Capture on Entity Right-Click
    @SubscribeEvent
    public static void onLassoEntityInteract(PlayerInteractEvent.EntityInteract event) {
        if (event.getLevel().isClientSide()) return;
        
        Player player = event.getEntity();
        ItemStack heldItem = player.getItemInHand(event.getHand());
        
        // Only handle lasso items
        if (!(heldItem.getItem() instanceof LassoItem)) return;
        
        // Check item-specific cooldown
        if (heldItem.has(DataComponents.CUSTOM_DATA)) {
            CompoundTag tag = heldItem.get(DataComponents.CUSTOM_DATA).copyTag();
            if (tag != null && tag.contains("CooldownUntil")) {
                long cooldownEnd = tag.getLong("CooldownUntil");
                long currentTime = player.level().getGameTime();
                if (currentTime < cooldownEnd) {
                    // Still on cooldown
                    return;
                }
            }
        }
        
        // Don't allow capturing if already has a trapped entity
        if (LassoItem.hasTrapped(heldItem)) {
            player.displayClientMessage(Component.literal("This lasso already has a mob!").withStyle(ChatFormatting.RED), true);
            event.setCanceled(true);
            return;
        }
        
        // Only handle living entities
        if (!(event.getTarget() instanceof LivingEntity target)) return;
        
        // Prevent capturing players
        if (target instanceof Player) {
            player.displayClientMessage(Component.literal("You can't capture other players!").withStyle(ChatFormatting.RED), true);
            event.setCanceled(true);
            return;
        }
        
        // Prevent capturing special mobs
        if (!target.isEffectiveAi() || target.isInvulnerable() || target.isSpectator()) {
            player.displayClientMessage(Component.literal("This mob can't be captured!").withStyle(ChatFormatting.RED), true);
            event.setCanceled(true);
            return;
        }
        
        // Prevent capturing bosses
        if (target.getType() == net.minecraft.world.entity.EntityType.ENDER_DRAGON ||
            target.getType() == net.minecraft.world.entity.EntityType.WITHER) {
            player.displayClientMessage(Component.literal("The lasso isn't strong enough!").withStyle(ChatFormatting.RED), true);
            event.setCanceled(true);
            return;
        }
        
        // Add cooldown to this specific item to prevent spam
        // Using a random UUID stored in the item's components to track cooldown
        if (!heldItem.has(DataComponents.CUSTOM_DATA)) {
            heldItem.set(DataComponents.CUSTOM_DATA, CustomData.of(new CompoundTag()));
        }
        CompoundTag tag = heldItem.get(DataComponents.CUSTOM_DATA).copyTag();
        if (tag == null) tag = new CompoundTag();
        
        // Store the current game time + cooldown (20 ticks = 1 second)
        tag.putLong("CooldownUntil", player.level().getGameTime() + 20);
        heldItem.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
        
        // Capture the entity
        LassoItem.trap(heldItem, target);
        player.displayClientMessage(
            Component.literal("Captured ").append(target.getDisplayName()).append("!").withStyle(ChatFormatting.GREEN),
            true
        );
        
        // Cancel the original interaction
        event.setCanceled(true);
        
        // Play sound effect
        Level level = player.level();
        level.playSound(
            null,
            player.blockPosition(),
            net.minecraft.sounds.SoundEvents.LEASH_KNOT_PLACE,
            net.minecraft.sounds.SoundSource.PLAYERS,
            1.0f,
            1.0f
        );
    }

    @SubscribeEvent
    public static void livingDamage(LivingDamageEvent.Pre event) {

        if(event.getEntity() instanceof Villager villager && event.getSource().getDirectEntity() instanceof Player player) {
            if(player.getMainHandItem().getItem() == ModItems.CHISEL.get()) {
                player.sendSystemMessage(Component.translatable("easteregg.chisel.villager"));
                player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100, 0));
                villager.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1));
                player.getMainHandItem().shrink(1);
            }

        }
    }





}
