package site.metacoding.finals.dto.customer;

import lombok.Getter;
import lombok.Setter;
import site.metacoding.finals.config.enums.Role;
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
        private Role role;

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
