package site.metacoding.finals.dto.review;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.finals.domain.customer.Customer;
import site.metacoding.finals.domain.image_file.ImageFile;
import site.metacoding.finals.domain.review.Review;
import site.metacoding.finals.domain.shop.Shop;

public class ReviewReqDto {
    @Getter
    @Setter
    public static class ReviewSaveReqDto {
        private int score;
        private String content;
        private String image;
        private Long shopId;

        public Review toEntity(List<ImageFile> imageFile, Customer customer, Shop shop) {
            return Review.builder()
                    .content(this.content)
                    .score(this.score)
                    .imageFile(imageFile)
                    .customer(customer)
                    .shop(shop)
                    .build();
        }
    }
}
