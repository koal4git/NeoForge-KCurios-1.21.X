package net.koala.kcurios;

import net.koala.kcurios.block.ModBlocks;
import net.koala.kcurios.component.ModDataComponents;
import net.koala.kcurios.effect.ModEffects;
import net.koala.kcurios.enchantment.ModEnchantmentsEffects;
import net.koala.kcurios.item.ModCreativeModeTabs;
import net.koala.kcurios.item.ModItems;
import net.koala.kcurios.potion.ModPotions;
import net.koala.kcurios.sound.ModSounds;
import net.koala.kcurios.util.ModItemProperties;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;



// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Kcurios.MOD_ID)
public class Kcurios {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "kcurios";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Kcurios(IEventBus modEventBus, ModContainer modContainer) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);



        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModSounds.register(modEventBus);
        ModDataComponents.register(modEventBus);

        ModEffects.register(modEventBus);
        ModPotions.register(modEventBus);

        ModEnchantmentsEffects.register(modEventBus);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.CRUSHED_EMERALDS);
            event.accept(ModItems.CRUSHED_AMETHYST);
        }else if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.CRUSHED_EMERALD_BLOCK);
            event.accept(ModBlocks.CRUSHED_AMETHYST_BLOCK);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = Kcurios.MOD_ID, value = Dist.CLIENT)
    static class ClientModEvents {



        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("Initializing Kcurios client...");

            ModItemProperties.addCustomItemProperties();

            LOGGER.info("PropHunt client initialization complete");
        }



    }
}
