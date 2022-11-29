package site.metacoding.finals.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.domain.feature.Feature;
import site.metacoding.finals.domain.feature.FeatureRepository;
import site.metacoding.finals.domain.image_file.ImageFileRepository;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.shop.ShopRepository;
import site.metacoding.finals.dto.shop.ShopReqDto.ShopFilterReqDto;
import site.metacoding.finals.dto.shop.ShopRespDto.ShopCategoryListRespDto;
import site.metacoding.finals.dto.shop.ShopRespDto.ShopDetailRespDto;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRespository;
    private final FeatureRepository featureRepository;
    private final ImageFileRepository imageFileRepository;

    public List<Shop> List() {
        return shopRespository.findAll();
    }

    public List<ShopCategoryListRespDto> categoryList(String categoryName) {
        List<Shop> shopList = shopRespository.findByCategory(categoryName);

        return shopList.stream()
                .map((dto) -> new ShopCategoryListRespDto(dto)).collect(Collectors.toList());

    }

    public List<Shop> filterList(ShopFilterReqDto dto) {
        // 보류
        return null;
    }

    public ShopDetailRespDto detatil(Long shopId) {
        // 가게 정보
        Shop shopPS = shopRespository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("잘못된 가게 요청"));
        // 날짜 + 인원 => 예약 가능 시간 조회

        // 가게 특징
        Feature featurePS = featureRepository.findByShopId(shopId)
                .orElseThrow(() -> new RuntimeException("잘못된 가게 요청"));

        return new ShopDetailRespDto(shopPS, featurePS);
    }
}
