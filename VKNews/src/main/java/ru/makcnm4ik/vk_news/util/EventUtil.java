package ru.makcnm4ik.vk_news.util;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import ru.makcnm4ik.vk_news.MoonNews;

public class EventUtil {
    public static boolean isCancelled(Event event) {
        if (!(event instanceof Cancellable)) {
            MoonNews.getInstance().printWarn(null, "isCancelled error. Event not instanceof Cancellable");
            return false;
        }

        callEvent(event);

        return ((Cancellable) event).isCancelled();
    }

    public static void callEvent(Event event) {
        Bukkit.getServer().getPluginManager().callEvent(event);
    }
}
