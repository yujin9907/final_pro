package site.metacoding.finals.dto.shop;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.finals.domain.feature.Feature;
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
}
