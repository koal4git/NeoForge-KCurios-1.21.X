package net.koala.kcurios.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.koala.kcurios.networking.MyData;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.util.Lazy;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {


    private static final KeyMapping keymapping_k = new KeyMapping(
            "key.kcurios.dash",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_K,
            "key.kcurios.category"
    );

    // Key Mapping is lazily initialized so it doesn't exist until it is registered
    public static final Lazy<KeyMapping> PRESS_K = Lazy.of(() -> keymapping_k);
    /*
    @SubscribeEvent // on the mod event bus only on the physical client
    public static void registerBindings(RegisterKeyMappingsEvent event) {event.register(PRESS_K.get());}

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        while (PRESS_K.get().consumeClick()) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Dash"));
            PacketDistributor.sendToServer(new MyData("Test", 30));
        }
    } */


    @SubscribeEvent
    public static void registerKeybindings(RegisterKeyMappingsEvent event) {
        event.register(KeyBinding.PRESS_K.get());
    }


}
