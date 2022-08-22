package ru.makcnm4ik.vk_news.vk;

import com.google.gson.Gson;
import ru.makcnm4ik.vk_news.VKNews;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class VKWallParser {
    private static final String VK_API_VERSION = "5.131";
    private static final String VK_WALL_GET = "https://api.vk.com/method/wall.get";
    private static final String POST_COUNT = "2";
    private final String accessToken, domain;

    public VKWallParser(String accessToken, String domain) {
        this.accessToken = accessToken;
        this.domain = domain;
    }

    public VKResponseResult getLastPost() {
        HashMap<String, String> urlParams = new HashMap<>();

        urlParams.put("v", VK_API_VERSION);
        urlParams.put("access_token", accessToken);
        urlParams.put("domain", domain);
        urlParams.put("count", POST_COUNT);

        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(
                    VK_WALL_GET + formatParams(urlParams)
            ).openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) response.append(inputLine);

            in.close();
            httpURLConnection.disconnect();

            Gson gson = new Gson();
            VKWallEntity vkWallEntity = gson.fromJson(String.valueOf(response), VKWallEntity.class);

            if (vkWallEntity.response.items.size() == 0) return null;
            else return vkWallEntity.response.items.get(0).is_pinned == 0 || vkWallEntity.response.items.size() == 1
                    ? new VKResponseResult(vkWallEntity, 0)
                    : new VKResponseResult(vkWallEntity, 1);
        } catch (IOException e) {
            VKNews.getInstance().printWarn(e, "getLastPost error");
            return null;
        }
    }

    private String formatParams(Map<String, String> params) {
        StringBuilder formattedParams = new StringBuilder();

        for (Map.Entry<String, String> param : params.entrySet()) {
            if (formattedParams.length() == 0) formattedParams.append("?").append(param.getKey()).append("=").append(param.getValue());

            formattedParams.append("&").append(param.getKey()).append("=").append(param.getValue());
        }

        return formattedParams.toString();
    }
}
