package site.metacoding.finals.dto.shop;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.user.User;

public class ShopReqDto {

    @Setter
    @Getter
    public static class ShopSaveReqDto {
        private String shopName;
        private String regestrationNumber;
        private String phoneNumber;
        private String category;
        private String address;
        private String information;
        private String opentime;
        private String closetime;
        private String image;
        private int perPrice;
        private int perHour;
        private User user;

        public Shop toEntity() {
            return Shop.builder()
                    .shopName(shopName)
                    .regestrationNumber(regestrationNumber)
                    .phoneNumber(phoneNumber)
                    .category(category)
                    .address(address)
                    .information(information)
                    .opentime(opentime)
                    .closetime(closetime)
                    .image(image)
                    .perPrice(perPrice)
                    .perHour(perHour)
                    .build();
        }
    }
}
