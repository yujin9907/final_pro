package site.metacoding.finals.dto.customer;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.finals.config.enums.Role;
import site.metacoding.finals.domain.customer.Customer;
import site.metacoding.finals.domain.user.User;
import site.metacoding.finals.dto.user.UserReqDto.JoinReqDto;

public class CustomerReqDto {

    @Getter
    @Setter
    public static class CustomerJoinReqDto {
        private String name;
        private String phoneNumber;
        private String address;
        private String username;
        private String password;

        public Customer toCustomerEntity() {
            return Customer.builder()
                    .name(this.name)
                    .phoneNumber(this.phoneNumber)
                    .address(this.address)
                    .build();
        }

        public User toUserEntity() {
            return User.builder()
                    .username(this.username)
                    .password(this.password)
                    .role(Role.USER)
                    .build();
        }
    }

    @Getter
    @Setter
    public static class test {
        private String name;
        private String phoneNumber;
        private String address;
        private JoinReqDto joinReqDto;
    }

}
