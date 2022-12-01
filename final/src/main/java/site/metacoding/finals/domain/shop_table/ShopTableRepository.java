package site.metacoding.finals.domain.shop_table;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShopTableRepository extends JpaRepository<ShopTable, Long> {

    List<ShopTable> findByShopId(Long shopId);

    @Query(value = "select distinct(max_people) from shop_table where shop_id= ?1", nativeQuery = true)
    public List<Integer> findDistinctByShopId(Long id);

}