package ru.makcnm4ik.vk_news.util;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import ru.makcnm4ik.vk_news.VKNews;
import ru.makcnm4ik.vk_news.data.NewsData;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DataUtil {
    public static void updateData(JavaPlugin javaPlugin) {
        checkStandardPaths(javaPlugin);
        File dataFile = new File(javaPlugin.getDataFolder().getPath() + "/data/data.yml");
        FileConfiguration yaml = YamlConfiguration.loadConfiguration(dataFile);

        if (yaml.contains("data")) NewsData.seenPlayers = (List<String>) yaml.get("data");
    }

    public static void saveData(JavaPlugin javaPlugin) {
        checkStandardPaths(javaPlugin);
        File dataFile = new File(javaPlugin.getDataFolder().getPath() + "/data/data.yml");
        FileConfiguration yaml = YamlConfiguration.loadConfiguration(dataFile);

        if (NewsData.seenPlayers.size() > 0) {
            yaml.set("data", NewsData.seenPlayers);

            try { yaml.save(dataFile); }
            catch (IOException e) { VKNews.getInstance().printWarn(e, "NewsData save error"); }
        }
    }

    private static void checkStandardPaths(JavaPlugin javaPlugin) {
        try {
            javaPlugin.getDataFolder().mkdir();

            File dataPackage = new File(javaPlugin.getDataFolder().getPath() + "/data");
            dataPackage.mkdir();

            File dataFile = new File(javaPlugin.getDataFolder().getPath() + "/data", "data.yml");

            if (!dataFile.exists()) dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
