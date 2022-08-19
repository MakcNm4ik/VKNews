package ru.makcnm4ik.vk_news.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import ru.makcnm4ik.vk_news.data.NewsData;
import xyz.upperlevel.spigot.book.BookUtil;

public class NewsUtil {
    public static void showLatestNews(Player player) {
        ItemStack book = BookUtil.writtenBook()
                .author("Moon News")
                .title("Latest news")
                .pages(NewsData.lastResponseResult.getPages()).build();



        BookUtil.openPlayer(player, book);
    }
}
