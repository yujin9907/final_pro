package site.metacoding.finals.domain.option_manage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionManageRepository extends JpaRepository<OptionManage, Long> {
    List<OptionManage> findByShopId(Long shopId);
}
