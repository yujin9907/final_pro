package site.metacoding.finals.domain.feature;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, Long> {

    // null체크 필요없음(Optional X)
    List<Feature> findByShopId(Long shopId);
}
