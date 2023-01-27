package net.aerithelion.day_counter;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.WorldSavePath;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class PlayerTimeStat {
    private String UUID,filePath;
    MinecraftClient client;
    Reader r;
    private double timePlayedInTicks;

    public PlayerTimeStat() {
        client = MinecraftClient.getInstance();
        UUID = client.player.getUuidAsString();
        timePlayedInTicks = 0;
    }

    public String getDaysPlayed() throws FileNotFoundException {
        readFile();
        parseJson();
        return convertToDays();
    }

    private void readFile() throws FileNotFoundException {
        String path = client.getServer().getSavePath(WorldSavePath.STATS).toString() + "/" + UUID + ".json";
        //String path = client.getServer().getSavePath(WorldSavePath.STATS).toString() + "/usethis.json";
        path = path.replace("\\","/");
        r = new FileReader(path);
    }

    private void parseJson() {
        Object obj = JsonParser.parseReader(r);
        JsonObject jo = (JsonObject) obj;
        JsonObject stats = jo.getAsJsonObject("stats");
        JsonObject customStats = stats.getAsJsonObject("minecraft:custom");
        String ticks = customStats.get("minecraft:play_time").toString();
        double test = customStats.get("minecraft:play_time").getAsDouble();
        timePlayedInTicks = Double.valueOf(ticks);
    }

    private String convertToDays() {
        //3150352
        double secondsPlayed = timePlayedInTicks/20;
        double hoursPlayed = secondsPlayed/3600;
        double days = hoursPlayed/12;

        double testNum = Math.floor(days);
        String onDay;
        if(testNum == days && days >= 1) {
            onDay = "Day: " + (int) days;
        }
        else {
            days = days + 1;
            onDay = "Day: " + (int) days;
        }

        return onDay;
    }


    public void setFileName(String newName) {
        filePath = newName;
    }

}
