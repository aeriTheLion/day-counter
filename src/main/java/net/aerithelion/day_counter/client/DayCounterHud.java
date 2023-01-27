package net.aerithelion.day_counter.client;

import net.aerithelion.day_counter.PlayerTimeStat;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.math.MatrixStack;

import java.io.FileNotFoundException;

public class DayCounterHud implements HudRenderCallback {

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {

        MinecraftClient client = MinecraftClient.getInstance();
        PlayerTimeStat timeStat = new PlayerTimeStat();
        String onDay;
        if(client != null) {
            try {
                onDay = timeStat.getDaysPlayed();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                onDay = "ERROR - File Not Found";
            }

            //test = client.getServer().getSavePath(WorldSavePath.STATS).toString();
            //String test = client.getServer().getSavePath(WorldSavePath.ROOT).getParent().getFileName().toString(); - gets World name
            //String test = client.getServer().getSavePath(WorldSavePath.ROOT).getParent().toString();

            TextRenderer renderer = MinecraftClient.getInstance().textRenderer;
            renderer.draw(matrixStack,onDay, 5,2, 0xffffff);

        }

    }
}
