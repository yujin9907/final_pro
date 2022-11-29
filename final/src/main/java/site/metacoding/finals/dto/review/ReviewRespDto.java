package site.metacoding.finals.dto.review;

import java.util.List;

import javax.sound.sampled.ReverbType;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.finals.domain.customer.Customer;
import site.metacoding.finals.domain.image_file.ImageFile;
import site.metacoding.finals.domain.review.Review;
import site.metacoding.finals.domain.shop.Shop;

public class ReviewRespDto {
    @Getter
    @Setter
    public static class ReviewSaveRespDto {
        private Long id;
        private int score;
        private String content;
        private List<ImageFile> imageFile;
        private Customer customer;
        private Shop shop;

        public ReviewSaveRespDto(Review review, List<ImageFile> images) {
            this.id = review.getId();
            this.score = review.getScore();
            this.content = review.getContent();
            this.customer = review.getCustomer();
            this.shop = review.getShop();
        }
    }

    @Setter
    @Getter
    public static class ShopListRespDto {
        List<ShopDto> shops;
        List<ImageFileDto> images;

        public class ShopDto {
            private String shopName;
            private String information;
            private String category;
            private String address;
        }

        public class ImageFileDto {
            private String storeFilename;
        }
    }
}