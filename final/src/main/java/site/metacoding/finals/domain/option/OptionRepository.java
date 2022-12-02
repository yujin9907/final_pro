package site.metacoding.finals.domain.option;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {

    // null체크 필요없음(Optional X)
    List<Option> findByShopId(Long shopId);
}
