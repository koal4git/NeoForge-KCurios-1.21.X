package net.koala.kcurios.datagen;

import net.koala.kcurios.Kcurios;
import net.koala.kcurios.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {


    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Kcurios.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        //STEEL
        basicItem(ModItems.STEEL_INGOT.get());
        basicItem(ModItems.RAW_STEEL.get());

        //EMERALD
        basicItem(ModItems.CRUSHED_EMERALDS.get());
        basicItem(ModItems.EMERALD_INGOT.get());

        //AMETHYST
        basicItem(ModItems.CRUSHED_AMETHYST.get());


        //FOOD
        basicItem(ModItems.BELL_PEPPER.get());

        //FUEL
        basicItem(ModItems.DRY_ICE.get());
        basicItem(ModItems.FLOUR.get());

        //TOOLS
        basicItem(ModItems.CHISEL.get());
    }
}
