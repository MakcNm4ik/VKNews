package ru.makcnm4ik.vk_news.db;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.makcnm4ik.vk_news.VKNews;
import ru.makcnm4ik.vk_news.data.NewsData;
import ru.makcnm4ik.vk_news.vk.VKResponseResult;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class YMLDB {
    private final String dataFolderPath = "plugins/VKNews/data/";
    private final String dataFilePath = dataFolderPath + "data.yml";

    public void updateData() {
        checkStandardPaths();
        File dataFile = new File(dataFilePath);
        FileConfiguration yaml = YamlConfiguration.loadConfiguration(dataFile);

        if (yaml.contains("data")) NewsData.seenPlayers = (List<String>) yaml.get("data");
        if (yaml.contains("lastPostId")) NewsData.lastResponseResult = new VKResponseResult(
                (int) yaml.get("lastPostId"),
                "Automatically created",
                "Automatically created"
        );
    }

    public void saveData() {
        checkStandardPaths();
        File dataFile = new File(dataFilePath);
        FileConfiguration yaml = YamlConfiguration.loadConfiguration(dataFile);

        if (NewsData.seenPlayers.size() > 0)
            yaml.set("data", NewsData.seenPlayers);

        if (NewsData.lastResponseResult != null && NewsData.lastResponseResult.getId() != -1)
            yaml.set("lastPostId", NewsData.lastResponseResult.getId());


        try { yaml.save(dataFile); }
        catch (IOException e) { VKNews.getInstance().printWarn(e, "saveData error"); }
    }

    private void checkStandardPaths() {
        try {
            new File(dataFolderPath).mkdir();

            File dataPackage = new File(dataFolderPath);
            dataPackage.mkdir();

            File dataFile = new File(dataFilePath);

            if (!dataFile.exists()) dataFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
