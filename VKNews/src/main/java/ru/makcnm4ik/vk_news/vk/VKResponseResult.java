package ru.makcnm4ik.vk_news.vk;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.md_5.bungee.api.chat.BaseComponent;
import ru.makcnm4ik.vk_news.util.PageUtil;

@Getter
@ToString
public class VKResponseResult {
    private final int id;
    @Setter
    private String text;
    private final String wallUrl;
    @Setter
    private BaseComponent[] pages;

    public VKResponseResult(int id, String text, String wallUrl) {
        this.id = id;
        this.text = text;
        this.wallUrl = wallUrl;
        if (id != -1 && !text.equals("Automatically created") && !wallUrl.equals("Automatically created"))
            this.pages = PageUtil.generateNewsPages(text, wallUrl);
    }

    public VKResponseResult(VKWallEntity vkWallEntity, int itemNum) {
        this.id = vkWallEntity.response.items.get(itemNum).id;
        this.text = vkWallEntity.response.items.get(itemNum).text;
        this.wallUrl = String.format(
                "https://vk.com/wall%d_%d",
                vkWallEntity.response.items.get(itemNum).from_id,
                vkWallEntity.response.items.get(itemNum).id
        );
        if (id != -1) this.pages = PageUtil.generateNewsPages(text, wallUrl);
    }
}
