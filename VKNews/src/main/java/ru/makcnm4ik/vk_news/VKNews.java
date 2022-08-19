package ru.makcnm4ik.vk_news;

import com.vdurmont.emoji.EmojiParser;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.makcnm4ik.vk_news.command.NewsCommand;
import ru.makcnm4ik.vk_news.config.ConfigYML;
import ru.makcnm4ik.vk_news.data.NewsData;
import ru.makcnm4ik.vk_news.util.DataUtil;
import ru.makcnm4ik.vk_news.listener.PlayerJoinListener;
import ru.makcnm4ik.vk_news.vk.VKResponseResult;
import ru.makcnm4ik.vk_news.vk.VKWallParser;

import java.util.logging.Logger;

public final class VKNews extends JavaPlugin {
    private final Logger logger = getLogger();
    private final PluginManager pluginManager = Bukkit.getPluginManager();
    private static VKNews instance;
    private static boolean isFirstCheck = true;

    public static VKNews getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        DataUtil.updateData(this);
        ConfigYML configYML = new ConfigYML(this);

        if (configYML.getVkAccessToken().length() == 0 || configYML.getVkDomain().length() == 0) {
            disablePlugin(null, "Configs have been created and need tweaking. Plugin disable");
            return;
        }

        VKWallParser vkWallParser = new VKWallParser(
                configYML.getVkAccessToken(),
                configYML.getVkDomain()
        );

        Bukkit.getScheduler().runTaskTimerAsynchronously(
                this,
                () -> {
                    VKResponseResult newVkResponseResult = vkWallParser.getLastPost();

                    if (newVkResponseResult == null) NewsData.lastResponseResult = null;
                    else if (NewsData.lastResponseResult.getId() != newVkResponseResult.getId()) {
                        if (!isFirstCheck) NewsData.seenPlayers.clear();
                        isFirstCheck = false;
                        newVkResponseResult.setText(EmojiParser.removeAllEmojis(newVkResponseResult.getText()));
                        NewsData.lastResponseResult = newVkResponseResult;
                    }
                },
                0, 20L * configYML.getUpdateCoolDown()
        );

        pluginManager.registerEvents(new PlayerJoinListener(), this);
        getCommand("news").setExecutor(new NewsCommand());
    }

    @Override
    public void onDisable() {
        DataUtil.saveData(this);
    }

    public void disablePlugin(Exception reason, String message) {
        printWarn(reason, message);
        pluginManager.disablePlugin(this);
    }

    public void printWarn(Exception reason, String message) {
        logger.warning(message);
        if (reason != null) reason.printStackTrace();
    }
}
