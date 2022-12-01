package site.metacoding.finals.web;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.dto.review.ReviewReqDto.ReviewSaveReqDto;

@Sql("classpath:sql/dml.sql")
@Slf4j
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class ReviewApiControllerTest {

        @Autowired
        private ObjectMapper om;
        @Autowired
        private MockMvc mvc;

        // 순환 참조
        @Test
        @WithUserDetails("ssar")
        // @WithMockUser(username = "ssar", roles = "USER")
        public void 리뷰저장하기테스트() throws Exception {
                // g
                ReviewSaveReqDto reqDtodata = new ReviewSaveReqDto();
                reqDtodata.setContent("테스트");
                reqDtodata.setScore(5);
                reqDtodata.setShopId(1L);
                String body = om.writeValueAsString(reqDtodata);

                MockMultipartFile reqDto = new MockMultipartFile("reqDto", "reqDto",
                                "application/json", body.getBytes(StandardCharsets.UTF_8));

                String filePath = "c://skdjlsdfjk";
                MockMultipartFile file = new MockMultipartFile("file", "filename.jpg", "form-data",
                                filePath.getBytes(StandardCharsets.UTF_8));

                List<MockMultipartFile> files = new ArrayList<>();
                files.add(file);

                // when
                ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.multipart("/review")
                                .file(file)
                                .file(reqDto));
                String responseBody = resultActions.andReturn().getResponse().getContentAsString();
                log.debug(responseBody);

                resultActions.andExpect(MockMvcResultMatchers.status().isCreated());

        }
}
