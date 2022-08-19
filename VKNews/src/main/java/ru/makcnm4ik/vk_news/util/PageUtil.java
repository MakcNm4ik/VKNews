package ru.makcnm4ik.vk_news.util;

import com.vdurmont.emoji.EmojiParser;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.ChatColor;
import xyz.upperlevel.spigot.book.BookUtil;

public class PageUtil {
    public static BaseComponent[] generateNewsPages(String text, String wallUrl) {
        text = EmojiParser.removeAllEmojis(text);

        BookUtil.PageBuilder page = new BookUtil.PageBuilder().add("");

        page = page.add("  ")
                .add(
                        BookUtil.TextBuilder.of("Ссылка на пост")
                                .color(ChatColor.DARK_BLUE)
                                .style(ChatColor.UNDERLINE, ChatColor.BOLD)
                                .onClick(BookUtil.ClickAction.openUrl(wallUrl))
                                .onHover(BookUtil.HoverAction.showText("Просмотреть пост"))
                                .build()
                ).newLine().newLine();

        for (String part : text.split(" ")) {
            if (TextUtil.isUrl(part)) {
                page = page.add(
                        BookUtil.TextBuilder.of(part)
                                .color(ChatColor.DARK_BLUE)
                                .style(ChatColor.UNDERLINE)
                                .onClick(BookUtil.ClickAction.openUrl(TextUtil.isShortUrl(part) ? "http://" + part : part))
                                .onHover(BookUtil.HoverAction.showText("Перейти по ссылке"))
                                .build()
                );
            } else {
                page = page.add(part + " ");
            }
        }

        return page.build();
    }
}
