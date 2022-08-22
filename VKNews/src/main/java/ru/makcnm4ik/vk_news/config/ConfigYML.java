package ru.makcnm4ik.vk_news.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigYML {
    private String vkAccessToken;
    private String vkDomain;
    private int updateCoolDown;
    private boolean useSQL;
    private String ip, username, password;
    private int port;
    FileConfiguration config;

    private static ConfigYML instance;

    public static ConfigYML getInstance() {
        return instance;
    }

    public ConfigYML(JavaPlugin plugin) {
        instance = this;
        config = plugin.getConfig();

        config.addDefault("vkAccessToken", "");
        config.addDefault("vkDomain", "");
        config.addDefault("updateCoolDown", 10);
        config.addDefault("useSQL", false);

        config.addDefault("ip", "");
        config.addDefault("username", "");
        config.addDefault("password", "");
        config.addDefault("port", 3306);

        config.options().copyDefaults(true);
        plugin.saveConfig();
        init();
    }

    public void init() {
        vkAccessToken = config.getString("vkAccessToken");
        vkDomain = config.getString("vkDomain");
        updateCoolDown = config.getInt("updateCoolDown");
        useSQL = config.getBoolean("useSQL");
        ip = config.getString("ip");
        username = config.getString("username");
        password = config.getString("password");
        port = config.getInt("port");
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
    public boolean isUseSQL() {
        return useSQL;
    }

    public String getIp() {
        return ip;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }
}
