package site.metacoding.finals.dto.shop;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.finals.domain.feature.Feature;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.user.User;

public class ShopRespDto {

    @Setter
    @Getter
    public static class ShopJoinRespDto {
        private User user;

        public ShopJoinRespDto(User user) {
            this.user = user;
        }
    }

    @Setter
    @Getter
    public static class ShopInformationRespDto {
        private Long id;
        private String shopName;
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

        public ShopInformationRespDto(Shop shop) {
            this.id = shop.getId();
            this.shopName = shop.getShopName();
            this.phoneNumber = shop.getPhoneNumber();
            this.category = shop.getCategory();
            this.address = shop.getAddress();
            this.information = shop.getInformation();
            this.opentime = shop.getOpentime();
            this.closetime = shop.getClosetime();
            this.image = shop.getImage();
            this.perPrice = shop.getPerPrice();
            this.perHour = shop.getPerHour();
            this.user = shop.getUser();
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
