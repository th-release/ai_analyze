package xyz.cth.trade.functions.analyze;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import xyz.cth.trade.common.utils.news.News;
import xyz.cth.trade.common.utils.news.Response;
import xyz.cth.trade.entities.PostEntity;
import xyz.cth.trade.functions.analyze.dto.ListDto;

import java.util.Optional;

@RestController
@RequestMapping("/analyze")
@Slf4j
@Tag(name = "/analyze", description = "분석 AI 관련 API")
@AllArgsConstructor
public class AnalyzeController {
    private final AnalyzeService analyzeService;
    private final News news;

    @GetMapping("/{id}")
    public Optional<PostEntity> findById(@PathVariable("id") String id) {
        return analyzeService.findByUuid(id);
    }

    @GetMapping("/list")
    public Page<PostEntity> list(@ModelAttribute ListDto dto) {
        return analyzeService.findByPostPaginationDesc(PageRequest.of(dto.getPage(), dto.getTake()));
    }
}
