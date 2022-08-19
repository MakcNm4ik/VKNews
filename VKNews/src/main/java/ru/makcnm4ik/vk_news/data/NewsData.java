package ru.makcnm4ik.vk_news.data;

import ru.makcnm4ik.vk_news.vk.VKResponseResult;

import java.util.ArrayList;
import java.util.List;

public class NewsData {
    public static VKResponseResult lastResponseResult = new VKResponseResult(-1, "Not created", "Not created");
    public static List<String> seenPlayers = new ArrayList<>();
}