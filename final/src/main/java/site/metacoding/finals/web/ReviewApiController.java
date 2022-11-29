package site.metacoding.finals.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.config.auth.PrincipalUser;
import site.metacoding.finals.dto.ResponseDto;
import site.metacoding.finals.dto.review.ReviewReqDto.ReviewSaveReqDto;
import site.metacoding.finals.dto.review.ReviewRespDto.ReviewSaveRespDto;
import site.metacoding.finals.handler.ImageFileHandler;
import site.metacoding.finals.service.ReviewService;

@RequiredArgsConstructor
@RestController
public class ReviewApiController {

    private final ReviewService reviewService;
    private final ImageFileHandler imageFileService;

    @PostMapping(value = "/review", consumes = { MediaType.APPLICATION_JSON_VALUE,
            MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> saveReview(@RequestPart("file") List<MultipartFile> file,
            @RequestPart("reqDto") ReviewSaveReqDto reviewSaveReqDto,
            @AuthenticationPrincipal UserDetails principalUser) {
        ReviewSaveRespDto respDto = reviewService.save(file, reviewSaveReqDto, principalUser);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "리뷰 저장", respDto), HttpStatus.CREATED);
    }
}
