package site.metacoding.finals.dto.shop;

import lombok.Getter;
import lombok.Setter;

public class ShopReqDto {
    @Getter
    @Setter
    public static class ShopFilterReqDto {
        private int date;
        private int person;
        private int time;
    }

}
