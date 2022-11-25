package site.metacoding.finals.dto.shop;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.finals.domain.shop.Shop;

public class ShopReqDto {

    @Setter
    @Getter
    public static class ShopSaveReqDto {
        private String shopName;
        private String regestrationNumber;
        private String phoneNumber;
        private String Category;
        private String address;
        private String information;
        private String opentime;
        private String closetime;
        private String image;

        public Shop toEntity() {
            return Shop.builder()
                    .shopName(shopName)
                    .regestrationNumber(regestrationNumber)
                    .phoneNumber(phoneNumber).ca
                    .build();
        }
    }
}
