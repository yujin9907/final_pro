package site.metacoding.finals.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.dto.customer.CustomerReqDto.CustomerJoinReqDto;

@Sql("classpath:sql/dml.sql")
@Slf4j
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class CustomerApiControllerTest {

    @Autowired
    private ObjectMapper om;
    @Autowired
    private MockMvc mvc;

    @Test
    public void 커스터머회원가입() throws Exception {
        // g
        CustomerJoinReqDto customerJoinReqDto = new CustomerJoinReqDto();
        customerJoinReqDto.setName("테스트");
        customerJoinReqDto.setAddress("주소테스트");
        customerJoinReqDto.setPhoneNumber("테스트입니다");
        customerJoinReqDto.setUsername("테스트아이디");
        customerJoinReqDto.setPassword("테스트비번");

        String ctdata = om.writeValueAsString(customerJoinReqDto);

        // when
        ResultActions resultActions = mvc.perform(post("/customer/join")
                .content(ctdata)
                .contentType("application/json; charset=utf-8")
                .accept("application/json; charset=utf-8"));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        log.debug(responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

}
