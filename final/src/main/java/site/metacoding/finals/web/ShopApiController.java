package site.metacoding.finals.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.dto.ResponseDto;
import site.metacoding.finals.dto.shop.ShopRespDto;
import site.metacoding.finals.dto.shop.ShopReqDto.ShopSaveReqDto;
import site.metacoding.finals.dto.shop.ShopRespDto.ShopSaveRespDto;
import site.metacoding.finals.service.ShopService;

@RequiredArgsConstructor
@RestController
public class ShopApiController {
    private final ShopService shopService;

    @PostMapping("/shop/information")
    public ResponseDto<?> save(@RequestBody ShopSaveReqDto shopSaveReqDto) {
        ShopSaveRespDto shopSaveRespDto = shopService.save(shopSaveReqDto);
        return new ResponseDto<>(HttpStatus.CREATED, "가게 정보 등록 완료", shopSaveRespDto);
    }
}
