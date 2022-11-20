package site.metacoding.finals.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
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

@Sql("classpath:sql/dml.sql")
@Slf4j
@ActiveProfiles("test")
@AutoConfigureMockMvc
@RequiredArgsConstructor
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class TestControllerTest {

    @Autowired
    private ObjectMapper om;
    @Autowired
    private MockMvc mvc;

    @WithMockUser("USER")
    @Test
    public void 유저정보보기테스트() throws Exception {
        // given

        // when
        ResultActions resultActions = mvc.perform(
                MockMvcRequestBuilders.get("/auth/user/test")
                        .accept("application/json; charset=utf-8"));

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

}
