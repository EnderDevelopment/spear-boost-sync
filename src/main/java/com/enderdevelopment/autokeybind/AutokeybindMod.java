package com.enderdevelopment.autokeybind;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class AutokeybindMod implements ClientModInitializer {
    private static KeyBinding keyBinding;
    
    @Override
    public void onInitializeClient() {
        // Register keybind
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.autokeybind.action",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_X,
                "category.autokeybind.main"
        ));

        // Register client tick event
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                // Keybind action will be implemented here
            }
        });
    }
}