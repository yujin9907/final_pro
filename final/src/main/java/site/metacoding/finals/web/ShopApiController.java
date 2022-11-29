package site.metacoding.finals.web;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.dto.ResponseDto;
import site.metacoding.finals.dto.shop.ShopRespDto.ShopCategoryListRespDto;
import site.metacoding.finals.dto.shop.ShopRespDto.ShopDetailRespDto;
import site.metacoding.finals.service.ShopService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ShopApiController {

    private final ShopService shopService;

    @GetMapping("/shop/list")
    public ResponseEntity<?> shopList() {
        List<Shop> shopList = shopService.List();
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "전체 가게 리스트 조회", shopList), HttpStatus.OK);
    }

    // 리스폰스 dto 방식 얘만 다름
    @GetMapping("/shop/list/{categoryName}")
    public ResponseEntity<?> shopCategoryList(@PathVariable String categoryName) {
        List<ShopCategoryListRespDto> shopList = shopService.categoryList(categoryName);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "카테고리별 가게 리스트 조회", shopList), HttpStatus.OK);
    }

    @GetMapping("/shop/detail/{id}")
    public ResponseEntity<?> shopDetail(@PathVariable Long id) {
        ShopDetailRespDto dto = shopService.detatil(id);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "가게 상세보기 조회", dto), HttpStatus.OK);
    }
}
