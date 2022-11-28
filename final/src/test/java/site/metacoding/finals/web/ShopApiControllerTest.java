package site.metacoding.finals.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.dto.shop.ShopReqDto.ShopSaveReqDto;

@Sql("classpath:sql/dml.sql")
@Slf4j
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class ShopApiControllerTest {

    private static final String APPLICATION_JSON = "application/json; charset=utf-8";

    private MockHttpSession session;

    @Autowired
    private ObjectMapper om;
    @Autowired
    private MockMvc mvc;

    @Test
    public void save() throws Exception {

        // given
        ShopSaveReqDto shopSaveReqDto = new ShopSaveReqDto();
        shopSaveReqDto.setShopName("메타코딩가게");
        shopSaveReqDto.setRegestrationNumber("1234567");
        shopSaveReqDto.setPhoneNumber("0516272658");
        shopSaveReqDto.setCategory("한식");
        shopSaveReqDto.setInformation("부산 서면");
        shopSaveReqDto.setOpentime("9");
        shopSaveReqDto.setClosetime("18");
        shopSaveReqDto.setImage("이미지없음");

        String sdata = om.writeValueAsString(shopSaveReqDto);

        // when
        ResultActions resultActions = mvc
                .perform(MockMvcRequestBuilders
                        .post("/shop/infomation")
                        .content(sdata)
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON));

        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        log.debug("디버그" + responseBody);

        // then
        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());

    }
}
