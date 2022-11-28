package site.metacoding.finals.dto.review;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.finals.domain.customer.Customer;
import site.metacoding.finals.domain.shop.Shop;

public class ReviewReqDto {
    @Getter
    @Setter
    public static class ReviewSaveReqDto {
        private int score;
        private String content;
        private String image;

    }
}
