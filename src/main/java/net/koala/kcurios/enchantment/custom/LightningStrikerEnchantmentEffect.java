package net.koala.kcurios.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public class LightningStrikerEnchantmentEffect implements EnchantmentEntityEffect {
    public static final MapCodec<LightningStrikerEnchantmentEffect> CODEC = MapCodec.unit(LightningStrikerEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel serverLevel, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if(enchantmentLevel ==1) {
            for (int i = 0; i < enchantmentLevel * 2; i++) {
                EntityType.LIGHTNING_BOLT.spawn(serverLevel, entity.getOnPos(), MobSpawnType.TRIGGERED);
            }
        }
        if(enchantmentLevel ==2) {
            for (int i = 0; i < enchantmentLevel * 2; i++) {
                EntityType.LIGHTNING_BOLT.spawn(serverLevel, entity.getOnPos(), MobSpawnType.TRIGGERED);
            }
        }
        if(enchantmentLevel ==3) {
            for (int i = 0; i < enchantmentLevel * 2; i++) {
                EntityType.LIGHTNING_BOLT.spawn(serverLevel, entity.getOnPos(), MobSpawnType.TRIGGERED);
            }
        }
    }


    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
