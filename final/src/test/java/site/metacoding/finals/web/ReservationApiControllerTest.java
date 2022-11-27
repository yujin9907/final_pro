package site.metacoding.finals.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.dto.reservation.ReservationReqDto.ReservationDateReqDto;

@Sql("classpath:sql/dml.sql")
@Slf4j
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class ReservationApiControllerTest {

    @Autowired
    private ObjectMapper om;
    @Autowired
    private MockMvc mvc;

    @Test
    public void 커스터머회원가입() throws Exception {
        // g
        ReservationDateReqDto reqDto = new ReservationDateReqDto();
        reqDto.setShopId(1L);
        String body = om.writeValueAsString(reqDto);

        // when
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.post("/reservation/date")
                .content(body)
                .contentType("application/json; charset=utf-8")
                .accept("application/json; charset=utf-8"));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        log.debug(responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }
}
