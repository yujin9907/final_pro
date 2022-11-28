package site.metacoding.finals.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.domain.feature.Feature;
import site.metacoding.finals.domain.feature.FeatureRepository;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.shop.ShopRepository;
import site.metacoding.finals.dto.shop.ShopReqDto.ShopFilterReqDto;
import site.metacoding.finals.dto.shop.ShopReqDto.ShopSaveReqDto;
import site.metacoding.finals.dto.shop.ShopRespDto.ShopDetailRespDto;
import site.metacoding.finals.dto.shop.ShopRespDto.ShopSaveRespDto;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final FeatureRepository featureRepository;

    @Transactional
    public ShopSaveRespDto save(ShopSaveReqDto shopSaveReqDto) {
        // 핵심 로직
        Shop shopPS = shopRepository.save(shopSaveReqDto.toEntity());

        // DTO 전환
        ShopSaveRespDto shopSaveRespDto = new ShopSaveRespDto(shopPS);

        return shopSaveRespDto;
    } // DB커넥션을 종료

    // ==========================================

    public List<Shop> List() {
        return shopRepository.findAll();
    }

    public List<Shop> categoryList(String categoryName) {
        List<Shop> shopList = shopRepository.findByCategory(categoryName);
        return shopList;
    }

    public List<Shop> filterList(ShopFilterReqDto dto) {
        // 보류
        return null;
    }

    public ShopDetailRespDto detatil(Long shopId) {
        // 가게 정보
        Shop shopPS = shopRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("잘못된 가게 요청"));
        // 날짜 + 인원 => 예약 가능 시간 조회

        // 가게 특징
        Feature featurePS = featureRepository.findByShopId(shopId)
                .orElseThrow(() -> new RuntimeException("잘못된 가게 요청"));

        return new ShopDetailRespDto(shopPS, featurePS);
    }
}
