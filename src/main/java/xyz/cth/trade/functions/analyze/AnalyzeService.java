package xyz.cth.trade.functions.analyze;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import xyz.cth.trade.entities.PostEntity;
import xyz.cth.trade.repositories.PostRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AnalyzeService {
    private final PostRepository postRepository;

    public void save(PostEntity postEntity) {
        postRepository.save(postEntity);
    }

    public void remove(PostEntity postEntity) {
        postRepository.delete(postEntity);
    }

    public Optional<PostEntity> findByUuid(String uuid) {
        return postRepository.findByUuid(uuid);
    }

    public Page<PostEntity> findByPostPaginationDesc(Pageable pageable) {
        return postRepository.findByPostPaginationDesc(pageable);
    }
}
