package dev.journalsmp.client;

import dev.journalsmp.network.JumpscarePayload;
import dev.journalsmp.network.CooldownSyncPayload;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public final class JournalNetworkingClient {
    private JournalNetworkingClient() {
    }

    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(JumpscarePayload.TYPE, (payload, context) ->
                JumpscareOverlay.activate(payload.durationMillis()));
        ClientPlayNetworking.registerGlobalReceiver(CooldownSyncPayload.TYPE, (payload, context) ->
                CooldownHud.update(payload));
        ClientPlayConnectionEvents.DISCONNECT.register((handler, client) -> JumpscareOverlay.clear());
    }
}
