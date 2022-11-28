package site.metacoding.finals.domain;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.domain.shop_table.ShopTable;
import site.metacoding.finals.domain.shop_table.ShopTableRepository;

@Sql("classpath:sql/dml.sql")
@Slf4j
@DataJpaTest
public class ShopTableRepositoryTest {

    @Autowired
    private ShopTableRepository shopTableRepository;

    @Test
    public void findDistinctByShopIdTest() {
        // g
        Long id = 1L;

        //
        List<Integer> shopPS = shopTableRepository.findDistinctByShopId(id);
        log.debug("디버그 중복제거 : " + shopPS.size());

    }

}
