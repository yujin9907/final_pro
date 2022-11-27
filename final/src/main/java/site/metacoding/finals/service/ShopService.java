package site.metacoding.finals.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.shop.ShopRespository;
import site.metacoding.finals.dto.shop.ShopReqDto.ShopFilterReqDto;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRespository shopRespository;

    public List<Shop> List() {
        return shopRespository.findAll();
    }

    public List<Shop> categoryList(String categoryName) {
        List<Shop> shopList = shopRespository.findByCategory(categoryName);
        return shopList;
    }

    public List<Shop> filterList(ShopFilterReqDto dto) {
        // 보류
        return null;
    }
}
