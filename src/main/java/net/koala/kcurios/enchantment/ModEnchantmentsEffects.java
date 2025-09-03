package net.koala.kcurios.enchantment;

import com.mojang.serialization.MapCodec;
import net.koala.kcurios.Kcurios;
import net.koala.kcurios.enchantment.custom.DashEnchantmentEffect;
import net.koala.kcurios.enchantment.custom.LightningStrikerEnchantmentEffect;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEnchantmentsEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENTITY_ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Kcurios.MOD_ID);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> LIGHTNING_STRIKER =
            ENTITY_ENCHANTMENT_EFFECTS.register("lightning_striker", () -> LightningStrikerEnchantmentEffect.CODEC);

    public static final Supplier<MapCodec<? extends EnchantmentEntityEffect>> DASH =
            ENTITY_ENCHANTMENT_EFFECTS.register("dash", () -> DashEnchantmentEffect.CODEC);

    public static void register(IEventBus eventBus) {
        ENTITY_ENCHANTMENT_EFFECTS.register(eventBus);


    }
}
