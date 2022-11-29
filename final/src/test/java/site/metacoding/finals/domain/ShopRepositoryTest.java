package site.metacoding.finals.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.domain.image_file.ImageFile;
import site.metacoding.finals.domain.image_file.ImageFileRepository;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.shop.ShopRepository;
import site.metacoding.finals.dummy.DummyEntity;

@Slf4j
@DataJpaTest
@ActiveProfiles("test")
public class ShopRepositoryTest extends DummyEntity {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ImageFileRepository imageFileRepository;

    @BeforeEach
    public void setUp() {

        Shop shop = newShop("가게1", "1", "한식");
        Shop shop2 = newShop("가게2", "2", "일식");

        shopRepository.save(shop);
        shopRepository.save(shop2);

        ImageFile imageFile = newShopImageFile(shop);
        imageFileRepository.save(imageFile);

    }

    @Test
    public void findByResevationCustomerIdTest() {
        Long customerId = 1L;

        List<Shop> shop = shopRepository.findResevationByCustomerId(customerId);

        assertEquals(shop.get(0).getId(), 1);
    }

    @Test
    public void findSubscribeByCustomerIdTest() {
        Long customerId = 1L;

        List<Shop> shop = shopRepository.findSubscribeByCustomerId(customerId);

        assertEquals(shop.get(0).getId(), 1);
    }

    @Test
    public void findByCategoryTest() {
        log.debug("디버그 이미지 : " + imageFileRepository.findById(1L).get().getId());

        String name = "한식";

        List<Shop> shop = shopRepository.findByCategory(name);

        assertEquals(shop.get(0).getCategory(), name);
    }

}
