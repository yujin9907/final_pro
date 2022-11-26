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
import site.metacoding.finals.domain.shop.ShopRespository;

@Slf4j
@Sql("classpath:sql/dml.sql")
@DataJpaTest
@ActiveProfiles("test")
public class ShopRepositoryTest {

    @Autowired
    private ShopRespository shopRespository;

    @Test
    public void findByResevationCustomerIdTest() {
        Long customerId = 1L;

        List<Shop> shop = shopRespository.findByResevationCustomerId(customerId);

        assertEquals(shop.get(0).getId(), 1);
    }

}
