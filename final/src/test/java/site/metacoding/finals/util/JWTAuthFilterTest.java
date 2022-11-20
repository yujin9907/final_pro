package site.metacoding.finals.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.config.enums.Role;
import site.metacoding.finals.domain.user.UserRepository;
import site.metacoding.finals.dto.LoginDto;

// @Sql("classpath:sql/dml.sql")
@Slf4j
@ActiveProfiles("test")
@AutoConfigureMockMvc
@RequiredArgsConstructor
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class JWTAuthFilterTest {

        @Autowired
        private ObjectMapper om;
        @Autowired
        private MockMvc mvc;
        @Autowired
        private BCryptPasswordEncoder bCryptPasswordEncoder;
        @Autowired
        private UserRepository userRepository;

        @BeforeEach
        public void settup() throws Exception {
                // given
                LoginDto loginDto = LoginDto.builder()
                                .username("test")
                                .password(bCryptPasswordEncoder.encode(("123")))
                                .role("USER")
                                .build();

                userRepository.save(loginDto.toEntity());
        }

        @Test
        public void 필터테스트() throws Exception {
                // given
                LoginDto loginDto = LoginDto.builder()
                                .username("test")
                                .password("123")
                                .role("USER")
                                .build();

                String body = om.writeValueAsString(loginDto);

                // when
                ResultActions resultActions = mvc.perform(
                                MockMvcRequestBuilders.post("/login")
                                                .content(body)
                                                .contentType("application/json; charset=utf-8")
                                                .accept("application/json; charset=utf-8"));

                // then
                resultActions.andExpect(MockMvcResultMatchers.status().is3xxRedirection()); // /로 이동하므로 302 가 나옴

        }

        @Test
        public void 메인테스트() throws Exception {
                ResultActions resultActions = mvc.perform(
                                MockMvcRequestBuilders.get("/")
                                                .accept("application/json; charset=utf-8"));

                resultActions.andExpect(MockMvcResultMatchers.status().isOk());
        }
}
