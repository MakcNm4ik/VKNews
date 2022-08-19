package ru.makcnm4ik.vk_news.event;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import ru.makcnm4ik.vk_news.vk.VKResponseResult;

public class PreShowBookEvent extends Event implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final VKResponseResult vkResponseResult;
    private final Player player;
    private boolean isCancelled = false;

    public PreShowBookEvent(VKResponseResult vkResponseResult, Player player) {
        this.vkResponseResult = vkResponseResult;
        this.player = player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public VKResponseResult getVkResponseResult() {
        return vkResponseResult;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }
}
