package ru.makcnm4ik.vk_news.vk;

import net.md_5.bungee.api.chat.BaseComponent;
import ru.makcnm4ik.vk_news.util.PageUtil;

public class VKResponseResult {
    private final int id;
    private String text;
    private final String wallUrl;
    private BaseComponent[] pages;

    public VKResponseResult(int id, String text, String wallUrl) {
        this.id = id;
        this.text = text;
        this.wallUrl = wallUrl;
        if (id != -1) this.pages = PageUtil.generateNewsPages(text, wallUrl);
    }

    public VKResponseResult(VKWallEntity vkWallEntity, int itemNum) {
        this.id = vkWallEntity.response.items.get(itemNum).id;
        this.text = vkWallEntity.response.items.get(itemNum).text;
        this.wallUrl = "https://vk.com/wall" + vkWallEntity.response.items.get(itemNum).from_id + "_" + vkWallEntity.response.items.get(itemNum).id;
        if (id != -1) this.pages = PageUtil.generateNewsPages(text, wallUrl);
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BaseComponent[] getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "VKResponseResult{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }
}
