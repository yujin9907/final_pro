package site.metacoding.finals.domain.option_manage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import site.metacoding.finals.domain.option.Option;

public interface OptionManageRepository extends JpaRepository<OptionManage, Long> {

    List<OptionManage> findByShopId(Long shopId);

    // @Query("select m.option from OptionManage m where m.shop = ?1")
    // List<Option> findByShopIdToOptionId(@Param("shopId") Long shopId);

    @Query(value = "select option_id from option_manage where shop_id = ?1", nativeQuery = true)
    List<Long> findByShopIdToOptionId(Long shopId);
}
