package net.koala.kcurios.event;

import com.mojang.blaze3d.platform.InputConstants;
import net.neoforged.neoforge.common.util.Lazy;
import net.koala.kcurios.Kcurios;
import net.koala.kcurios.item.ModItems;
import net.koala.kcurios.networking.MyData;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = Kcurios.MOD_ID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ModClientEvents {

    @SubscribeEvent
    public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
        if(event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() == ModItems.PISTOL.get()) { //change to check fi item is in tag to add more
            float fovModifier = 1f;
            int ticksUsingItem = event.getPlayer().getTicksUsingItem();
            float deltaTicks = (float)ticksUsingItem / 20f;
            if(deltaTicks > 1f) {
                deltaTicks = 1f;
            } else {
                deltaTicks *= deltaTicks;
            }
            fovModifier *= 1f - deltaTicks * 0.15f;
            event.setNewFovModifier(fovModifier);
        }
    }


    private static final KeyMapping keymapping_dash = new KeyMapping(
            "key.kcurios.dash", //uses this translation key
            InputConstants.Type.KEYSYM, // default mapping is on keyboard
            GLFW.GLFW_KEY_C,  //default key is c
            "key.categories.misc");

    public static final Lazy<KeyMapping>  PRESS_DASH = Lazy.of(() -> keymapping_dash);

    @SubscribeEvent // on mod event bus only on the physical client
    public static void registerBindings(RegisterKeyMappingsEvent event) {event.register(PRESS_DASH.get());}

    @SubscribeEvent // on the game event bus pnly on the phsyical client
    public static void onClientTick(ClientTickEvent.Post event){
        while (PRESS_DASH.get().consumeClick()) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("Pressed Dash Button"));
            PacketDistributor.sendToServer( new MyData("Test", 30));
        }

    }

}
