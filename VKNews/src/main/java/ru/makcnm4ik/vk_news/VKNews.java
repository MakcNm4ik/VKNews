package ru.makcnm4ik.vk_news;

import com.vdurmont.emoji.EmojiParser;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.makcnm4ik.vk_news.command.NewsCommand;
import ru.makcnm4ik.vk_news.config.ConfigYML;
import ru.makcnm4ik.vk_news.data.NewsData;
import ru.makcnm4ik.vk_news.listener.PlayerJoinListener;
import ru.makcnm4ik.vk_news.util.DataUtil;
import ru.makcnm4ik.vk_news.util.PageUtil;
import ru.makcnm4ik.vk_news.vk.VKResponseResult;
import ru.makcnm4ik.vk_news.vk.VKWallParser;

import java.util.logging.Logger;

public final class VKNews extends JavaPlugin {
    private final Logger logger = getLogger();
    private final PluginManager pluginManager = Bukkit.getPluginManager();
    private static VKNews instance;

    public static VKNews getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        ConfigYML configYML = new ConfigYML(this);

        DataUtil.updateData();

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
                    VKResponseResult lastRRes = NewsData.lastResponseResult;
                    VKResponseResult newVkResponseResult = vkWallParser.getLastPost();

                    if (newVkResponseResult == null) printWarn(null, "newVkResponseResult = null");
                    else if (lastRRes == null) NewsData.lastResponseResult = null;
                    else if (lastRRes.getId() != newVkResponseResult.getId()) {
                        NewsData.seenPlayers.clear();
                        newVkResponseResult.setText(EmojiParser.removeAllEmojis(newVkResponseResult.getText()));
                        NewsData.lastResponseResult = newVkResponseResult;
                    } else if (lastRRes.getId() == newVkResponseResult.getId() && lastRRes.getText().equals("Automatically created")) {
                        NewsData.lastResponseResult.setPages(PageUtil.generateNewsPages(
                                newVkResponseResult.getText(),
                                newVkResponseResult.getWallUrl()
                        ));
                    }
                },
                0, 20L * configYML.getUpdateCoolDown()
        );

        pluginManager.registerEvents(new PlayerJoinListener(), this);
        getCommand("news").setExecutor(new NewsCommand());
    }

    @Override
    public void onDisable() {
        DataUtil.saveData();
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
