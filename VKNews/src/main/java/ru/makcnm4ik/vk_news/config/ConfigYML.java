package ru.makcnm4ik.vk_news.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigYML {
    private String vkAccessToken;
    private String vkDomain;
    private int updateCoolDown;

    FileConfiguration config;

    public ConfigYML(JavaPlugin plugin) {
        config = plugin.getConfig();

        config.addDefault("vkAccessToken", "");
        config.addDefault("vkDomain", "");
        config.addDefault("updateCoolDown", 10);

        config.options().copyDefaults(true);
        plugin.saveConfig();
        init();
    }

    public void init() {
        vkAccessToken = config.getString("vkAccessToken");
        vkDomain = config.getString("vkDomain");
        updateCoolDown = config.getInt("updateCoolDown");
    }

    public String getVkAccessToken() {
        return vkAccessToken;
    }
    public String getVkDomain() {
        return vkDomain;
    }
    public int getUpdateCoolDown() {
        return updateCoolDown;
    }
}
