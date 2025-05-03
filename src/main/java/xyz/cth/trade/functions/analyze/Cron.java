package xyz.cth.trade.functions.analyze;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xyz.cth.trade.common.configs.ProvenConfig;
import xyz.cth.trade.common.utils.GPT.Gpt;
import xyz.cth.trade.common.utils.news.News;
import xyz.cth.trade.common.utils.news.NewsAsset;
import xyz.cth.trade.common.utils.news.NewsObject;
import xyz.cth.trade.common.utils.news.Response;
import xyz.cth.trade.entities.PostEntity;

import java.time.LocalDateTime;
import java.util.List;

@EnableScheduling
@Component
@Slf4j
@AllArgsConstructor
public class Cron {
    private final Gpt gpt;
    private final News news;
    private final AnalyzeService analyzeService;
    private ProvenConfig provenConfig;

    @Scheduled(cron = "0 0 */1 * * *")
    private void analyze() {
        Response response = news.getNews();

        if (response == null) {
            return;
        }

        List<NewsObject> contents = response.getData().getContent();

        StringBuilder news_asset = new StringBuilder("아래 뉴스 데이터를 기반으로 분석하여 현재 시장 정보를 하나의 내용으로 정리해서 줘. 또한 분석내용은 제목과 내용으로 나눠서, \"${제목} | ${내용}\"으로 이렇게 하나의 내용으로 정리해서 대답을 해줘.\n");
        int news_id = 1;

        for(NewsObject content : contents) {
            news_asset.append(news_id).append(". \n제목 : ").append(content.getTitle()).append("\n").append("내용 : ");

            if (content.getSummaryList() != null && !content.getSummaryList().isEmpty()) {
                for (String summary : content.getSummaryList()) {
                    news_asset.append(summary).append(", ");
                }
            }

            news_asset.append("\n");

            if (content.getAssetList() != null && !content.getAssetList().isEmpty()) {
                news_asset.append("symbols : ");
                for (NewsAsset asset : content.getAssetList()) {
                    news_asset.append(asset.getAssetTicker()).append(" ");
                }
                news_asset.append("\n");
            }
            news_id += 1;
        }

        xyz.cth.trade.common.utils.GPT.Response analyze = gpt.analyze(news_asset.toString(), provenConfig.getKey());

        if (analyze != null) {
            String[] content = analyze.getOutput().get(0).getContent().get(0).getText().split("\\|");

            System.out.println(content.length);
            System.out.println(content[0]);
            System.out.println(content[1]);

            analyzeService.save(
                    PostEntity.builder()
                            .title(content[0])
                            .content(content[1])
                            .createdAt(LocalDateTime.now())
                    .build()
            );
        }

    }
}
