package site.metacoding.finals.config.oauth;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.config.auth.PrincipalUser;
import site.metacoding.finals.config.enums.Role;
import site.metacoding.finals.domain.user.User;
import site.metacoding.finals.domain.user.UserRepository;

@RequiredArgsConstructor
@Service
public class Oauth2UserService extends DefaultOAuth2UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    // private final User user;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId(); // google
        String providerId = oAuth2User.getAttribute("sub");
        String username = provider + "_" + providerId; // 사용자가 입력한 적은 없지만 만들어준다

        String uuid = UUID.randomUUID().toString().substring(0, 6);
        String password = bCryptPasswordEncoder.encode("pw" + uuid); // join과 같은 방식으로 구현

        Role role = Role.USER;

        User userPS = userRepository.findByUsername(username);

        System.out.println("여기까지" + userPS);

        // DB에 없는 사용자라면 회원가입처리
        if (userPS == null) {
            userPS = User.builder()
                    .username(username).password(password).role(role)
                    .provider(provider).providerId(providerId)
                    .build();
            userRepository.save(userPS);
        }

        return new PrincipalUser(userPS, oAuth2User.getAttributes());
    }

}
