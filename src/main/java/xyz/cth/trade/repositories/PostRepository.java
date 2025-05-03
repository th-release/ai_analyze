package xyz.cth.trade.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import xyz.cth.trade.entities.PostEntity;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, String> {
    @Query("SELECT u FROM PostEntity u where u.uuid = :uuid")
    Optional<PostEntity> findByUuid(@Param("uuid") String uuid);

    @Query("SELECT u FROM PostEntity u order by u.createdAt desc")
    Page<PostEntity> findByPostPaginationDesc(Pageable pageable);
}
