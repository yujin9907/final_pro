package site.metacoding.finals.domain;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.domain.customer.Customer;
import site.metacoding.finals.domain.customer.CustomerRepository;
import site.metacoding.finals.domain.reservation.Reservation;
import site.metacoding.finals.domain.reservation.ReservationRepository;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.shop.ShopRepository;
import site.metacoding.finals.domain.shop_table.ShopTable;
import site.metacoding.finals.domain.shop_table.ShopTableRepository;
import site.metacoding.finals.domain.user.User;
import site.metacoding.finals.domain.user.UserRepository;
import site.metacoding.finals.dummy.DummyEntity;

@Slf4j
@DataJpaTest
@ActiveProfiles("test")
public class CustomerRepositoryTest extends DummyEntity {

    @Autowired
    private EntityManager em;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ShopTableRepository shopTableRepository;

    @BeforeEach
    public void setUp() {
        User user = newUser("ssar");
        userRepository.save(user);
        Customer customer = newCustomer(user);
        customerRepository.save(customer);

        Shop shop = newShop("가게", "010", "양식");
        shopRepository.save(shop);
        ShopTable shopTable = newShopTable(shop);
        shopTableRepository.save(shopTable);
        Reservation reservation = newReservation(customer, shopTable);
        reservationRepository.save(reservation);
    }

    @AfterEach
    public void tearDown() {
        em.createNativeQuery("ALTER TABLE customer ALTER COLUMN `id` RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE shop ALTER COLUMN `id` RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE users ALTER COLUMN `id` RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE shop_table ALTER COLUMN `id` RESTART WITH 1").executeUpdate();
        em.createNativeQuery("ALTER TABLE reservation ALTER COLUMN `id` RESTART WITH 1").executeUpdate();

    }

    @Test
    public void 유저삭제_softdelete적용테스트() {
        Optional<Customer> customerPS = customerRepository.findById(1L);

        em.clear();

        System.out.println(customerPS.get().getId());

        customerRepository.delete(customerPS.get());

        em.clear();

        customerRepository.findById(customerPS.get().getId());
    }

    @Test
    public void 유저삭제_softdelete적용테스트2() {
        em.clear();
        customerRepository.findById(1L);

        customerRepository.deleteById(1l);
        em.flush();

        // 이게 null 나오는 게 맞음. @where 적용으로 인해
        // customerRepository.findById(1l).get().getIsDeleted();

    }

}
