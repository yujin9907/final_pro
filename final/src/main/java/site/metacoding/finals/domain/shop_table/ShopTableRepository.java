package site.metacoding.finals.domain.shop_table;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShopTableRepository extends JpaRepository<ShopTable, Long> {

    @Query(value = "select distinct(max_people) from shop_table where shop_id= ?1", nativeQuery = true)
    Optional<List<Integer>> findDistinctByShopId(Long id);

}