package xyz.cth.trade.common.utils.GPT;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Response {
    private List<Output> output;
}
