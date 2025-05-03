package xyz.cth.trade;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class OnStart {
    @PostConstruct
    public void init() {
        log.atInfo().log("Analyze Init Right Now! - @cth.release");
    }
}
