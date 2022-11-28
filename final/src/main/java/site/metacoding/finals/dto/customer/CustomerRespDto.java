package site.metacoding.finals.dto.customer;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.finals.domain.customer.Customer;
import site.metacoding.finals.domain.reservation.Reservation;
import site.metacoding.finals.domain.review.Review;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.user.User;

public class CustomerRespDto {

    @Setter
    @Getter
    public static class CustomerJoinRespDto {
        private Long id;
        private String name;
        private String phoneNumber;
        private String address;
        private User user;

        public CustomerJoinRespDto(Customer customer, User user) {
            this.id = customer.getId();
            this.name = customer.getName();
            this.phoneNumber = customer.getPhoneNumber();
            this.address = customer.getAddress();
            this.user = user;
        }

    }

    @NoArgsConstructor
    @Getter
    @Setter
    public static class CustomerUpdateRespDto {
        private Long id;
        private String name;
        private String phoneNumber;
        private String address;
        @JsonIgnore
        private User user;

        public CustomerUpdateRespDto(Customer customer) {
            this.id = customer.getId();
            this.name = customer.getName();
            this.phoneNumber = customer.getPhoneNumber();
            this.address = customer.getAddress();
            this.user = customer.getUser();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class CustomerMyPageReservationRespDto {
        private List<Shop> shop;
        private List<Reservation> reservation;

    }

    @AllArgsConstructor
    @Setter
    @Getter
    public static class CustomerMyPageSubscribeRespDto {
        private List<Shop> shop;
    }

    @AllArgsConstructor
    @Setter
    @Getter
    public static class CustomerMyPageReviewRespDto {
        private List<Review> review;
    }

}
