package net.koala.kcurios.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LassoItem extends Item {

    //I AM BEING VERY CLEAR WITH THIS ALOT OF THIS CODE WAS WRITTEN **WITH THE HELP** OF AI SOME OF IT WAS FULLY
    // AI BUT IT WAS MOSTLY LOGIC AND THINGS I DIDNT GET (cough cough component handling)
    //BEING VERY TRANSPAERTEN HERE


    private static final String ENTITY_TYPE_KEY = "EntityType";
    private static final String ENTITY_DATA_KEY = "EntityData";

    public LassoItem(Properties props) {
        super(props);
    }

    // Checks if the lasso has a trapped entity
    // Returns true if both entity type and data are present in the item's NBT
    public static boolean hasTrapped(ItemStack stack) {
        return stack.has(DataComponents.CUSTOM_DATA) &&
               stack.get(DataComponents.CUSTOM_DATA) != null &&
               stack.get(DataComponents.CUSTOM_DATA).copyTag() != null &&
               stack.get(DataComponents.CUSTOM_DATA).copyTag().contains(ENTITY_TYPE_KEY) &&
               stack.get(DataComponents.CUSTOM_DATA).copyTag().contains(ENTITY_DATA_KEY);
    }

    // Captures a living entity into the lasso item
    // Creates a new lasso item with the entity data and removes the original entity
    public static void trap(ItemStack stack, Entity entity) {
        if (entity instanceof LivingEntity) {
            CompoundTag tag = new CompoundTag();
            String entityType = EntityType.getKey(entity.getType()).toString();
            tag.putString(ENTITY_TYPE_KEY, entityType);
            
            CompoundTag entityData = new CompoundTag();
            entity.saveWithoutId(entityData);
            
            entityData.remove("UUID");
            entityData.remove("Pos");
            entityData.remove("Motion");
            entityData.remove("Rotation");
            entityData.remove("OnGround");
            
            tag.put(ENTITY_DATA_KEY, entityData);
            
            ItemStack newStack = stack.copy();
            newStack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag));
            
            if (!stack.isEmpty()) {
                stack.shrink(1);
                Player player = ((LivingEntity)entity).level().getNearestPlayer(entity, 10);
                if (player != null && !player.getInventory().add(newStack)) {
                    player.drop(newStack, false);
                }
            }
            
            entity.discard();
        }
    }

    // Handles right-click interaction with living entities
    // Prevents capturing invalid targets and manages cooldowns
    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity entity, InteractionHand hand) {
        if (player.level().isClientSide) {
            return InteractionResult.SUCCESS;
        }
        
        if (entity instanceof Player || entity.isPassenger() || entity.isVehicle()) {
            return InteractionResult.PASS;
        }
        
        if (LassoItem.hasTrapped(stack)) {
            player.displayClientMessage(Component.literal("This lasso already has a mob!").withStyle(ChatFormatting.RED), true);
            return InteractionResult.FAIL;
        }
        
        LassoItem.trap(stack, entity);
        player.getCooldowns().addCooldown(this, 20);
        
        return InteractionResult.SUCCESS;
    }

    // Releases the trapped entity when right-clicking with the lasso
    // Spawns the entity in front of the player and clears the lasso
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        
        if (level.isClientSide || !hasTrapped(stack)) {
            return InteractionResultHolder.pass(stack);
        }
        
        CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
        if (customData == null || customData.copyTag() == null) {
            return InteractionResultHolder.pass(stack);
        }
        
        CompoundTag tag = customData.copyTag();
        String entityTypeId = tag.getString(ENTITY_TYPE_KEY);
        Optional<EntityType<?>> entityType = EntityType.byString(entityTypeId);
        
        if (entityType.isPresent() && level instanceof ServerLevel serverLevel) {
            Entity entity = entityType.get().create(serverLevel);
            if (entity != null) {
                CompoundTag entityData = tag.getCompound(ENTITY_DATA_KEY);
                UUID oldUuid = entity.getUUID();
                entity.load(entityData);
                entity.setUUID(oldUuid);

                
                double x = player.getX() + player.getLookAngle().x * 2;
                double y = player.getY() + player.getEyeHeight() - 1;
                double z = player.getZ() + player.getLookAngle().z * 2;
                entity.setPos(x, y, z);
                entity.setYRot(player.getYRot());
                
                serverLevel.addFreshEntity(entity);
                stack.remove(DataComponents.CUSTOM_DATA);
                
                level.playSound(null, player.blockPosition(), 
                    SoundEvents.LEASH_KNOT_BREAK, SoundSource.PLAYERS, 1.0f, 1.0f);
                
                return InteractionResultHolder.success(stack);
            }
        }
        
        return InteractionResultHolder.pass(stack);
    }


    @Override
    @OnlyIn(Dist.CLIENT)
    public boolean isFoil(ItemStack stack) {
        return hasTrapped(stack);
    }

    // Adds tooltip text showing the trapped entity type
    // Displays capture/release instructions based on the lasso's state
    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        if (hasTrapped(stack)) {
            CustomData customData = stack.get(DataComponents.CUSTOM_DATA);
            if (customData != null && customData.copyTag() != null) {
                CompoundTag tag = customData.copyTag();
                if (tag.contains(ENTITY_TYPE_KEY)) {
                    String entityTypeId = tag.getString(ENTITY_TYPE_KEY);
                    EntityType.byString(entityTypeId).ifPresent(entityType -> {
                        MutableComponent text = Component.literal("Contains: ")
                            .append(entityType.getDescription())
                            .withStyle(ChatFormatting.GREEN);
                        tooltip.add(text);
                    });
                    tooltip.add(Component.literal("Right-click to release").withStyle(ChatFormatting.GRAY));
                    return;
                }
            }
        }
        tooltip.add(Component.literal("Right-click on a mob to capture it").withStyle(ChatFormatting.GRAY));
    }
}