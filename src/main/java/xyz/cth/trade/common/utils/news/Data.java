package xyz.cth.trade.common.utils.news;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Data {
    private List<NewsObject> content;
}
