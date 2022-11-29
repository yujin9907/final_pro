package site.metacoding.finals.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.domain.feature.Feature;
import site.metacoding.finals.domain.feature.FeatureRepository;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.shop.ShopRepository;
import site.metacoding.finals.domain.user.User;
import site.metacoding.finals.domain.user.UserRepository;
import site.metacoding.finals.dto.shop.ShopReqDto.ShopFilterReqDto;
import site.metacoding.finals.dto.shop.ShopReqDto.ShopJoinReqDto;
import site.metacoding.finals.dto.shop.ShopRespDto.ShopDetailRespDto;
import site.metacoding.finals.dto.shop.ShopRespDto.ShopJoinRespDto;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final FeatureRepository featureRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public ShopJoinRespDto join(ShopJoinReqDto shopJoinReqDto) {
        String encPassword = bCryptPasswordEncoder.encode(shopJoinReqDto.getPassword());
        shopJoinReqDto.setPassword(encPassword);

        User userPS = userRepository.save(shopJoinReqDto.toUserEntity());

        // userPS값을 바로 return하면 Entity에 영향이 가나?
        return new ShopJoinRespDto(userPS);
    }

    // @Transactional
    // public ShopInformationRespDto join(ShopInformationReqDto shopJoinReqDto) {
    // String encPassword =
    // bCryptPasswordEncoder.encode(shopJoinReqDto.getPassword());
    // shopJoinReqDto.setPassword(encPassword);

    // User userPS = userRepository.save(shopJoinReqDto.toUserEntity());
    // Shop shopPS = shopRepository.save(shopJoinReqDto.toShopEntity(userPS));

    // return new ShopJoinRespDto(shopPS, userPS);
    // }

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
