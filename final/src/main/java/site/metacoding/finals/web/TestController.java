package site.metacoding.finals.web;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.domain.user.UserRepository;
import site.metacoding.finals.dto.ResponseDto;
import site.metacoding.finals.dto.user.LoginDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RestController
public class TestController {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserRepository userRepository;

    @GetMapping("/")
    public ResponseDto<?> main() {
        return new ResponseDto<>(HttpStatus.OK, "OK", null);
    }

    // @PostMapping(value = "/join")
    // public ResponseDto<?> postMethodName(@RequestBody LoginDto loginDto) {

    // User user = loginDto.toEntity();
    // user.setPassword(bCryptPasswordEncoder.encode(loginDto.getPassword()));

    // return new ResponseDto<>(1, "ok", userRepository.save(user));
    // }

    @GetMapping("/auth/user/test")
    public void test() {
        System.out.println("실행됨");
    }

}
