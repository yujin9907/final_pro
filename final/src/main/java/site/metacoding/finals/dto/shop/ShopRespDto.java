package site.metacoding.finals.dto.shop;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.finals.domain.feature.Feature;
import site.metacoding.finals.domain.shop.Shop;

public class ShopRespDto {

    @Setter
    @Getter
    public static class ShopSaveRespDto {
        private Long id;
        private String shopName;
        private String regestrationNumber;
        private String phoneNumber;
        private String category;
        private String address;
        private String information;
        private String opentime;
        private String closetime;
        private String image;

        public ShopSaveRespDto(Shop shop) {
            this.id = shop.getId();
            this.shopName = shop.getShopName();
            this.phoneNumber = shop.getPhoneNumber();
            this.category = shop.getCategory();
            this.address = shop.getAddress();
            this.information = shop.getInformation();
            this.opentime = shop.getOpentime();
            this.closetime = shop.getClosetime();
            this.image = shop.getImage();
            // image는 차후 패스 경로가 나오도록 해야 할 것 같음
            // 안나오게 하든가
        }
    }

    @Getter
    @Setter
    public static class ShopDetailRespDto {
        private Shop shop;
        private Feature feature;

        // 예약 가능 시간 정보
        public ShopDetailRespDto(Shop shop, Feature feature) {
            this.shop = shop;
            this.feature = feature;
        }

    }
}
