package com.enderdevelopment.autokeybind.client;

import com.enderdevelopment.autokeybind.AutokeybindMod;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.UpdateSelectedSlotC2SPacket;
import net.minecraft.util.Hand;

public class AutokeybindClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (AutokeybindMod.keyBinding.wasPressed()) {
                handleKeybindAction(client);
            }
        });
    }

    private void handleKeybindAction(MinecraftClient client) {
        ClientPlayerEntity player = client.player;
        if (player == null) return;

        // Trigger the currently held item's use
        player.interactionManager.interactItem(player, Hand.MAIN_HAND);

        // Scan hotbar for Mace and switch to it
        for (int i = 0; i < 9; i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.getItem() == Items.MACE) {
                player.getInventory().selectedSlot = i;
                // Sync the slot change with the server
                client.getNetworkHandler().sendPacket(new UpdateSelectedSlotC2SPacket(i));
                break;
            }
        }
    }
}