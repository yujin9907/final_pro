package site.metacoding.finals.dto.shop;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.finals.domain.image_file.ImageFile;
import site.metacoding.finals.domain.option.Option;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.user.User;
import site.metacoding.finals.handler.ImageFileHandler;

public class ShopRespDto {

    // 회지
    @Setter
    @Getter
    public static class ShopJoinRespDto {
        private UserDto user;

        public ShopJoinRespDto(User user) {
            this.user = new UserDto(user);
        }

        @Getter
        public class UserDto {
            private Long userId;
            private String username;

            public UserDto(User user) {
                this.userId = user.getId();
                this.username = user.getUsername();
            }
        }
    }

    @Setter
    @Getter
    public static class ShopInfoSaveRespDto {
        private Long id;
        private String shopName;
        private String phoneNumber;
        private String category;
        private String address;
        private String information;
        private String openTime;
        private String closeTime;
        private int perPrice;
        private int perHour;
        private List<FeatureDto> featureList;
        private List<ImageFileDto> imageFiles;
        private UserDto user;

        public ShopInfoSaveRespDto(Shop shop, List<Option> featureList, List<ImageFile> images) {
            this.id = shop.getId();
            this.shopName = shop.getShopName();
            this.phoneNumber = shop.getPhoneNumber();
            this.category = shop.getCategory();
            this.address = shop.getAddress();
            this.information = shop.getInformation();
            this.openTime = shop.getOpenTime();
            this.closeTime = shop.getCloseTime();
            this.perPrice = shop.getPerPrice();
            this.perHour = shop.getPerHour();
            this.featureList = featureList.stream().map((feature) -> new FeatureDto(feature))
                    .collect(Collectors.toList());
            this.imageFiles = images.stream().map((image) -> new ImageFileDto(image)).collect(Collectors.toList());
            this.user = new UserDto(shop.getUser());
        }

        @Getter
        public class UserDto {
            private Long userId;
            private String username;

            public UserDto(User user) {
                this.userId = user.getId();
                this.username = user.getUsername();
            }
        }

        @Getter
        public class FeatureDto {
            private long id;
            private String name;

            public FeatureDto(Option feature) {
                this.id = feature.getId();
                this.name = feature.getName();
            }
        }

        @Getter
        public class ImageFileDto {
            private long id;
            private String storeFilename;

            public ImageFileDto(ImageFile imageFile) {
                this.id = imageFile.getId();
                this.storeFilename = imageFile.getStoreFilename();
            }
        }
    }

    // 유진
    @Setter
    @Getter
    public static class ShopListRespDto {
        private String shopName;
        private String address;
        private String category;
        private String information;
        private String openTime;
        private String closeTime;
        private ImageFileDto imageFileDto;

        public ShopListRespDto(Shop shop) {
            this.shopName = shop.getShopName();
            this.address = shop.getAddress();
            this.category = shop.getCategory();
            this.information = shop.getInformation();
            this.openTime = shop.getOpenTime();
            this.closeTime = shop.getCloseTime();
            this.imageFileDto = new ImageFileDto(shop.getImageFile());
        }

        @Getter
        public class ImageFileDto {
            private long id;
            private String image;

            public ImageFileDto(ImageFile imageFile) {
                this.id = imageFile.getId();
                this.image = ImageFileHandler.encodingFile(imageFile.getStoreFilename());
            }

        }

    }

    @Setter
    @Getter
    public static class ShopReservaitonListRespDto {
        private Shop shop;
        private ImageFile imageFile;
    }

    @Getter
    @Setter
    public static class ShopDetailRespDto {
        private Shop shop;
        private List<Option> feature;

        // 예약 가능 시간 정보
        public ShopDetailRespDto(Shop shop, List<Option> feature) {
            this.shop = shop;
            this.feature = feature;
        }

    }

}