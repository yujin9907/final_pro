package site.metacoding.finals.domain.shop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShopRepository extends JpaRepository<Shop, Long> {

        @Query("select s from Shop s where s.category = :category")
        List<Shop> findByCategory(@Param("category") String category);

        @Query(value = "select shop.* from shop"
                        + " right join (select st.shop_id, r2.* from shop_table st"
                        + " right join (select r.* from reservation r where customer_id= ?1) r2"
                        + " on st.id = r2.shop_table_id) r3"
                        + " on shop.id=r3.shop_id", nativeQuery = true)
        List<Shop> findResevationByCustomerId(Long customerId);

        @Query(value = "select shop.* from shop"
                        + " right join (select * from subscribe where customer_id =1) s"
                        + " on shop.id=s.shop_id", nativeQuery = true)
        List<Shop> findSubscribeByCustomerId(Long id);

}
