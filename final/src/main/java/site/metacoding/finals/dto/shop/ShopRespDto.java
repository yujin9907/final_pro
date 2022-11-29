package site.metacoding.finals.dto.shop;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.finals.domain.feature.Feature;
import site.metacoding.finals.domain.image_file.ImageFile;
import site.metacoding.finals.domain.shop.Shop;

public class ShopRespDto {
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

    @Setter
    @Getter
    public static class ShopCategoryListRespDto {
        private String shopName;
        private String address;
        private String openTime;
        private String closeTime;

        public ShopCategoryListRespDto(Shop shop) {
            this.shopName = shop.getShopName();
            this.address = shop.getAddress();
            this.openTime = shop.getOpenTime();
            this.closeTime = shop.getCloseTime();
        }

    }
}
