package site.metacoding.finals.dummy;

import java.net.ProtocolException;

import site.metacoding.finals.config.enums.Role;
import site.metacoding.finals.domain.image_file.ImageFile;
import site.metacoding.finals.domain.review.Review;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.user.User;

public abstract class DummyEntity {

    // 함부로 사용할 수 없도록 protected 설정(상속시에만 사용할 수 있고 new 안됨)
    // abstract 붙이면 new도 안되므로 더 안전

    protected User newUser(String username) {
        return User.builder()
                .username(username)
                .password("123")
                .role(Role.USER)
                .build();
    }

    protected Shop newShop(String name, String phone, String category) {
        return Shop.builder()
                .shopName(name)
                .phoneNumber(phone)
                .category(category)
                .address("부산진구")
                .information("맛있는가게")
                .openTime("10")
                .closeTime("22")
                .perHour(1)
                .perPrice(10000)
                .build();
    }

    protected ImageFile newReviewImageFile(Review review) {
        return ImageFile.builder()
                .originFilename("anan.jpg")
                .storeFilename("store.jpg")
                .review(review)
                .build();
    }

    protected ImageFile newShopImageFile(Shop shop) {
        return ImageFile.builder()
                .originFilename("anan.jpg")
                .storeFilename("store.jpg")
                .shop(shop)
                .build();
    }

}
