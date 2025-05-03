package xyz.cth.trade.common.utils.news;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
@Setter
public class NewsObject {
    private String title;
    private String plainTextContent;
    private List<String> summaryList;
    private List<String> insightList;
    private List<NewsAsset> assetList;
}
