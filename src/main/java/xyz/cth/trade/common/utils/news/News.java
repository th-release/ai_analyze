package xyz.cth.trade.common.utils.news;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class News {
    public xyz.cth.trade.common.utils.news.Response getNews() {
        try {
            OkHttpClient client = new OkHttpClient();

            Request.Builder requestBuilder = new Request.Builder()
                    .url("https://v3-gtw.bloomingbit.io/postbox/v1/news/list?limit=100");

            requestBuilder.method("GET", null);

            Request req = requestBuilder.build();
            Response response = client.newCall(req).execute();

            String jsonResponse = response.body() != null ? response.body().string() : "{}";

            Gson gson = new Gson();

            return gson.fromJson(jsonResponse, xyz.cth.trade.common.utils.news.Response.class);
        } catch (IOException e) {
            return null;
        }
    }
}
