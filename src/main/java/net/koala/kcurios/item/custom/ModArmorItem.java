package net.koala.kcurios.item.custom;

import com.google.common.collect.ImmutableMap;
import net.koala.kcurios.item.ModArmorMaterials;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Map;

public class ModArmorItem extends ArmorItem {

    //CREDIT TO KAUPENJOE FOR THIS CLASS, tweaked by koala for kcurios


    private static final Map<Holder<ArmorMaterial>, List<MobEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<Holder<ArmorMaterial>, List<MobEffectInstance>>())
                    .put(ModArmorMaterials.EMERALD_ARMOR_MATERIAL,
                            List.of(new MobEffectInstance(MobEffects.HEALTH_BOOST, Integer.MAX_VALUE, 1),
                                    new MobEffectInstance(MobEffects.LUCK, 999999, 1)
                            ))
                    .build();

    public ModArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    //inventory tick is armor being worn
    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        if (entity instanceof Player player && !level.isClientSide()) {
            evaluateArmorEffects(player);
        }
    }


    private void evaluateArmorEffects(Player player) {
        for (Map.Entry<Holder<ArmorMaterial>, List<MobEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            Holder<ArmorMaterial> material = entry.getKey();
            List<MobEffectInstance> effects = entry.getValue();

            if (hasPlayerCorrectArmorOn(material, player)) {
                // Add effects if not present
                for (MobEffectInstance effect : effects) {
                    if (!player.hasEffect(effect.getEffect())) {
                        player.addEffect(new MobEffectInstance(effect.getEffect(),
                                effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.isVisible()));
                    }
                }
            } else {
                // Remove all effects associated with that material
                for (MobEffectInstance effect : effects) {
                    if (player.hasEffect(effect.getEffect())) {
                        player.removeEffect(effect.getEffect());
                    }
                }
            }
        }
    }


    private void addEffectToPlayer(Player player, List<MobEffectInstance> mapEffect) {
        boolean hasPlayerEffect = mapEffect.stream().allMatch(effect -> player.hasEffect(effect.getEffect()));

        if(!hasPlayerEffect) {
            for (MobEffectInstance effect : mapEffect) {
                player.addEffect(new MobEffectInstance(effect.getEffect(), //if player is missing an effect, add it
                        effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.isVisible()));
            }
        }
    }

    private boolean hasPlayerCorrectArmorOn(Holder<ArmorMaterial> material, Player player) {
        for (ItemStack stack : player.getArmorSlots()) {
            if (!(stack.getItem() instanceof ArmorItem armorItem)) {
                return false;
            }
            if (armorItem.getMaterial() != material) {
                return false;
            }
        }
        return true;
    }


    private boolean hasFullSuitOfArmorOn(Player player) {
        ItemStack boots = player.getInventory().getArmor(0);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack chestplate = player.getInventory().getArmor(2);
        ItemStack helmet = player.getInventory().getArmor(3);

        return !boots.isEmpty() && !leggings.isEmpty() && !chestplate.isEmpty() && !helmet.isEmpty();
    }
}