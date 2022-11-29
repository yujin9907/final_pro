package site.metacoding.finals.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.shop.ShopRepository;

@Slf4j
@Sql("classpath:sql/dml.sql")
@DataJpaTest
@ActiveProfiles("test")
public class ShopRepositoryTest {

    @Autowired
    private ShopRepository shopRespository;

    @Test
    public void findByResevationCustomerIdTest() {
        Long customerId = 1L;

        List<Shop> shop = shopRespository.findResevationByCustomerId(customerId);

        assertEquals(shop.get(0).getId(), 1);
    }

    @Test
    public void findSubscribeByCustomerIdTest() {
        Long customerId = 1L;

        List<Shop> shop = shopRespository.findSubscribeByCustomerId(customerId);

        assertEquals(shop.get(0).getId(), 1);
    }

    @Test
    public void findByCategoryTest() {
        String name = "한식";

        List<Shop> shop = shopRespository.findByCategory(name);
        log.debug("디버그 : " + shop.get(0));

        assertEquals(shop.get(0).getCategory(), name);
    }

}
