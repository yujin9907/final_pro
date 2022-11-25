package site.metacoding.finals.dto.user;

import lombok.Getter;
import lombok.Setter;

public class UserReqDto {

    @Setter
    @Getter
    public static class JoinReqDto {
        private String username;
        private String password;
        private String Role;
    }
}
