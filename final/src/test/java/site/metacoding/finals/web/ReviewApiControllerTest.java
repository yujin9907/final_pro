package site.metacoding.finals.web;

import java.nio.charset.StandardCharsets;

import javax.mail.Multipart;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
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

    @Test
    @WithMockUser
    public void 리뷰저장하기테스트() throws Exception {
        // g
        ReviewSaveReqDto reqDto = new ReviewSaveReqDto();
        reqDto.setContent("테스트");
        reqDto.setScore(5);
        reqDto.setShopId(1L);
        String body = om.writeValueAsString(reqDto);

        MockMultipartFile resumeSaveReqDtoFile = new MockMultipartFile("ResumeSaveReqDto", "ResumeSaveReqDto",
                "application/json", body.getBytes(StandardCharsets.UTF_8));

        String filePath = "c:/skdjlsdfjk";
        MockMultipartFile file = new MockMultipartFile("file", "filename", "form-data",
                filePath.getBytes(StandardCharsets.UTF_8));

        // when
        ResultActions resultActions = mvc.perform(MockMvcRequestBuilders.multipart("/s/resume/save")
                .file(file)
                .file(resumeSaveReqDtoFile));
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        log.debug(responseBody);

        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());

    }
}
