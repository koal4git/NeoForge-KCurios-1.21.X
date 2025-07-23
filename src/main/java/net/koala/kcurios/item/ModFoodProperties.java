package net.koala.kcurios.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {

    public static final FoodProperties BELL_PEPPER = new FoodProperties.Builder().nutrition(3).saturationModifier(0.5f)
            .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0), 0.25f).build();

}
