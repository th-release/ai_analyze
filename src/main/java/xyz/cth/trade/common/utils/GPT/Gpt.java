package xyz.cth.trade.common.utils.GPT;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import okhttp3.*;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class Gpt {
    private final ObjectMapper objectMapper;

    public xyz.cth.trade.common.utils.GPT.Response analyze(String asset, String key) {
        try {
            Map<String, String> payload = new HashMap<>();
            payload.put("model", "gpt-4.1-mini");
            payload.put("input", asset);
//            payload.put("max_tokens", "16384");

            // Build request
            String jsonBody = objectMapper.writeValueAsString(payload);
            RequestBody body = RequestBody.create(jsonBody, MediaType.get("application/json; charset=utf-8"));
            Request request = new Request.Builder()
                    .url("https://api.openai.com/v1/responses")
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + key)
                    .post(body)
                    .build();

            OkHttpClient httpClient = new OkHttpClient.Builder()
                    .connectTimeout(Duration.ofSeconds(60))
                    .readTimeout(Duration.ofSeconds(60))
                    .writeTimeout(Duration.ofSeconds(60))
                    .build();

            Gson gson = new Gson();
            // Execute request
            try (Response response = httpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response + ": " + response.body().string());
                }

                String jsonResponse = response.body() != null ? response.body().string() : "{}";

                return gson.fromJson(jsonResponse, xyz.cth.trade.common.utils.GPT.Response.class);
            }
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
}
