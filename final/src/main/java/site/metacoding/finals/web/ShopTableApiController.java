package site.metacoding.finals.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.config.auth.PrincipalUser;
import site.metacoding.finals.dto.ResponseDto;
import site.metacoding.finals.dto.shop_table.ShopTableReqDto.ShopTableSaveReqDto;
import site.metacoding.finals.dto.shop_table.ShopTableRespDto.ShopTableSaveRespDto;
import site.metacoding.finals.service.ShopTableService;

@RequiredArgsConstructor
@RestController
public class ShopTableApiController {

        private final ShopTableService shopTableService;

        @PostMapping("/shop/table")
        public ResponseEntity<?> reservationSave(@RequestBody ShopTableSaveReqDto shopTableSaveReqDto,
                        @AuthenticationPrincipal PrincipalUser principalUser) {
                ShopTableSaveRespDto shopTableSaveRespDto = shopTableService.save(shopTableSaveReqDto,
                                principalUser.getUser().getId());
                return new ResponseEntity<>(new ResponseDto<>(HttpStatus.CREATED, "테이블 생성 완료", shopTableSaveRespDto),
                                HttpStatus.CREATED);
        }
}
