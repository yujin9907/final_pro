package site.metacoding.finals.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import site.metacoding.finals.config.enums.Role;
import site.metacoding.finals.domain.user.User;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class LoginDto {
    private String username;
    private String password;

    public User toEntity() {
        return User.builder()
                .username(this.username)
                .password(this.password)
                .build();
    }
}
