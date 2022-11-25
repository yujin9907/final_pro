package site.metacoding.finals.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.dto.ResponseDto;
import site.metacoding.finals.dto.shop.ShopReqDto.ShopSaveReqDto;
import site.metacoding.finals.service.ShopService;

@RequiredArgsConstructor
public class ShopApiController {
    private final ShopService shopService;

    @PostMapping("/information")
    public ResponseDto<?> save(@RequestBody ShopSaveReqDto shopSaveReqDto) {
        return new ResponseDto<>(HttpStatus.CREATED, "가게 정보 등록 완료", shopService.save(shopSaveReqDto));
    }
}
