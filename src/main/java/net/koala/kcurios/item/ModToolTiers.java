package net.koala.kcurios.item;

import net.koala.kcurios.util.ModTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.common.SimpleTier;

public class ModToolTiers {

    public static final Tier EMERALD = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_EMERALD_TOOL ,
            1400, 7F, 2.5F, 18, () -> Ingredient.of(ModItems.EMERALD_INGOT.get()));

    public static final Tier AMETHYST = new SimpleTier(ModTags.Blocks.INCORRECT_FOR_AMETHYST_TOOL ,
            512, 6F, 3F, 16, () -> Ingredient.of(ModItems.AMETHYST_INGOT.get()));

}
