package site.metacoding.finals.service;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.shop.ShopRespository;

@RequiredArgsConstructor
public class ShopService {
    private final ShopRespository shopRepository;

    @Transactional
    public ShopSaveRespDto save(ShopSaveReqDto shopSaveReqDto) {
        // 핵심 로직
        Shop shopPS = shopRepository.save(shopSaveReqDto.toEntity());

        // DTO 전환
        ShopSaveRespDto shopSaveRespDto = new ShopSaveRespDto(shopPS);

        return shopSaveRespDto;
    } // DB커넥션을 종료
}
