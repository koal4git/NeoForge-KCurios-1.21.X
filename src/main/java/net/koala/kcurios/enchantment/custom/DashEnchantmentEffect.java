package net.koala.kcurios.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public class DashEnchantmentEffect implements EnchantmentEntityEffect {
    public static final MapCodec<DashEnchantmentEffect> CODEC = MapCodec.unit(DashEnchantmentEffect::new);

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        /*
        // Only apply to players
        if (!entity.isAlive() || !(entity instanceof net.minecraft.world.entity.player.Player player)) {
            return;
        }

        // Get the player's look direction
        Vec3 look = player.getLookAngle();

        // Scale dash by enchantment level
        double dashStrength = 1.2D + (0.4D * enchantmentLevel);

        // Push player forward
        player.push(look.x * dashStrength, 0.1D, look.z * dashStrength);

        // Optional: reset fall distance so dashing doesn’t cause fall damage
        player.resetFallDistance();

    */






    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
