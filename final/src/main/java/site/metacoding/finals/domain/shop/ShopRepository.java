package site.metacoding.finals.domain.shop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import site.metacoding.finals.repositoryDto.customer.ReservationRepositoryRespDto;

public interface ShopRepository extends JpaRepository<Shop, Long> {

        @Query("select s from Shop s join fetch s.imageFile where s.id = ?1")
        Shop findByShopId(@Param("id") Long id);

        @Query("select s from Shop s where s.category = :category")
        List<Shop> findByCategory(@Param("category") String category);

        @Query(value = "select i.store_filename storeFilename, r4.shop_name shopName, r4.category category, r4.address address, "
                        +
                        "r4.reservation_time reservationTime, r4.reservation_date reservationDate from image_file i " +
                        "right join (select shop.id, shop.shop_name, shop.address, shop.category, r3.reservation_date, r3.reservation_time from shop "
                        +
                        "right join (select st.shop_id, r2.* from shop_table st " +
                        "right join (select r.* from reservation r where customer_id= ?1) r2 " +
                        "on st.id = r2.shop_table_id) r3 " +
                        "on shop.id=r3.shop_id) r4 " +
                        "on r4.id=i.shop_id", nativeQuery = true)
        List<ReservationRepositoryRespDto> findResevationByCustomerId(@Param("customerId") Long customerId);

        @Query(value = "select shop.id shopId, shop.shop_name shopName, shop.address address, shop.category category, r3.reservation_date reservationDate, r3.reservation_time reservationTime from shop "
                        +
                        "right join (select st.shop_id, r2.* from shop_table st " +
                        "right join (select r.* from reservation r where customer_id= ?1) r2 " +
                        "on st.id = r2.shop_table_id) r3 " +
                        "on shop.id=r3.shop_id", nativeQuery = true)
        List<ReservationRepositoryRespDto> findResevationByCustomerIdTEST(@Param("customerId") Long customerId);

        @Query(value = "select shop.* from shop"
                        + " right join (select * from subscribe where customer_id =1) s"
                        + " on shop.id=s.shop_id", nativeQuery = true)
        List<Shop> findSubscribeByCustomerId(Long id);

}
