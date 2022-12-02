package site.metacoding.finals.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.dto.ResponseDto;
import site.metacoding.finals.dto.option.OptionReqDto.OptionSaveReqDto;
import site.metacoding.finals.dto.option.OptionRespDto.OptionSaveRespDto;
import site.metacoding.finals.service.OptionService;

@RestController
@RequiredArgsConstructor
public class OptionApiController {
    private final OptionService optionService;

    @PostMapping("/option")
    public ResponseEntity<?> joinCustomerApi(@RequestBody OptionSaveReqDto optionSaveReqDto) {
        OptionSaveRespDto optionSaveRespDto = optionService.save(optionSaveReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "옵션 등록 완료", optionSaveRespDto),
                HttpStatus.CREATED);
    }
}
