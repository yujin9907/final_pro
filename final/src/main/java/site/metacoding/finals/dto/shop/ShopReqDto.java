package site.metacoding.finals.dto.shop;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.finals.config.enums.Role;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.user.User;

@Setter
@Getter
public class ShopReqDto {

    // 회지DTO
    @Setter
    @Getter
    public static class ShopJoinReqDto {
        private String username;
        private String password;

        public User toUserEntity() {
            return User.builder()
                    .username(this.username)
                    .password(this.password)
                    .role(Role.SHOP)
                    .build();
        }
    }

    @Setter
    @Getter
    public static class ShopInformationReqDto {
        private String shopName;
        private String phoneNumber;
        private String address;
        private String category;
        private String information;
        private String opentime;
        private String closetime;
        private String image;
        private int perPrice;
        private int perHour;

        public Shop toInformationEntity(User user) {
            return Shop.builder()
                    .shopName(shopName)
                    .phoneNumber(phoneNumber)
                    .category(category)
                    .address(address)
                    .information(information)
                    .opentime(opentime)
                    .closetime(closetime)
                    .image(image)
                    .perPrice(perPrice)
                    .perHour(perHour)
                    .user(user)
                    .build();
        }
    }

    // 유진 Dto
    @Getter
    @Setter
    public static class ShopFilterReqDto {
        private int date;
        private int person;
        private int time;
    }

}
