package site.metacoding.finals.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.config.auth.PrincipalUser;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.dto.ResponseDto;
import site.metacoding.finals.dto.shop.ShopReqDto.ShopInformationReqDto;
import site.metacoding.finals.dto.shop.ShopReqDto.ShopJoinReqDto;
import site.metacoding.finals.dto.shop.ShopRespDto.ShopDetailRespDto;
import site.metacoding.finals.dto.shop.ShopRespDto.ShopInformationRespDto;
import site.metacoding.finals.dto.shop.ShopRespDto.ShopJoinRespDto;
import site.metacoding.finals.service.ShopService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ShopApiController {

    private final ShopService shopService;

    // shop입장에서 이용하는 가게 기능

    @PostMapping("/shop/join")
    public ResponseEntity<?> joinShopApi(@RequestBody ShopJoinReqDto shopJoinReqDto) {
        ShopJoinRespDto shopJoinRespDto = shopService.join(shopJoinReqDto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "가게 회원가입 완료", shopJoinRespDto),
                HttpStatus.CREATED);
    }

    @PostMapping("/shop/information")
    public ResponseEntity<?> save(@RequestBody ShopInformationReqDto shopinformInformationReqDto,
            @AuthenticationPrincipal PrincipalUser principalUser) {
        log.debug("디버그 : 컨트롤러단로그" + principalUser.getUser().getId());
        log.debug("디버그 : 컨트롤러단로그" + principalUser.getUser().getUsername());
        log.debug("디버그 : 컨트롤러단로그" + principalUser.getUser().getPassword());
        ShopInformationRespDto shopInformationRespDto = shopService.information(shopinformInformationReqDto,
                principalUser.getUser());
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "가게 정보등록 완료", shopInformationRespDto),
                HttpStatus.CREATED);
    }

    // customer입장에서 보는 가게 기능

    @GetMapping("/shop/list")
    public ResponseEntity<?> shopList() {
        List<Shop> shopList = shopService.List();
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "전체 가게 리스트 조횐", shopList), HttpStatus.OK);
    }

    // 리스폰스 dto 방식 얘만 다름
    @GetMapping("/shop/list/{categoryName}")
    public ResponseEntity<?> shopCategoryList(@PathVariable String categoryName) {
        List<Shop> shopList = shopService.categoryList(categoryName);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "카테고리별 가게 리스트 조회", shopList), HttpStatus.OK);
    }

    @GetMapping("/shop/detail/{id}")
    public ResponseEntity<?> shopDetail(@PathVariable Long id) {
        ShopDetailRespDto dto = shopService.detatil(id);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "가게 상세보기 조회", dto), HttpStatus.OK);
    }
}
