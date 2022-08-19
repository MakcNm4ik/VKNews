package ru.makcnm4ik.vk_news.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ru.makcnm4ik.vk_news.MoonNews;
import ru.makcnm4ik.vk_news.data.NewsData;
import ru.makcnm4ik.vk_news.util.NewsUtil;

public class NewsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (NewsData.lastResponseResult.getId() == -1 || NewsData.lastResponseResult == null) {
            sender.sendMessage(ChatColor.RED + "Новость отсутствует или произошла ошибка");
            MoonNews.getInstance().printWarn(null, "OnCommand -> " + NewsData.lastResponseResult.toString());
            return false;
        }

        NewsUtil.showLatestNews((Player) sender);

        return true;
    }
}
