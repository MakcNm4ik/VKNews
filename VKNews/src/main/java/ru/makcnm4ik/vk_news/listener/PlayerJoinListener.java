package ru.makcnm4ik.vk_news.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import ru.makcnm4ik.vk_news.data.NewsData;
import ru.makcnm4ik.vk_news.event.PreShowBookEvent;
import ru.makcnm4ik.vk_news.util.EventUtil;
import ru.makcnm4ik.vk_news.util.NewsUtil;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String playerUuid = event.getPlayer().getUniqueId().toString();

        if (NewsData.seenPlayers.contains(playerUuid)) return;
        else NewsData.seenPlayers.add(playerUuid);

        if (!EventUtil.isCancelled(new PreShowBookEvent(NewsData.lastResponseResult, player)))
            NewsUtil.showLatestNews(player);
    }
}
