package net.aerithelion.day_counter;

import net.aerithelion.day_counter.client.DayCounterHud;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

public class DayCounterClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        HudRenderCallback.EVENT.register(new DayCounterHud());

    }
}
