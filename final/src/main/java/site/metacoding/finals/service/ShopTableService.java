package site.metacoding.finals.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.shop.ShopRepository;
import site.metacoding.finals.domain.shop_table.ShopTableRepository;
import site.metacoding.finals.dto.shop_table.ShopTableReqDto.ShopTableSaveReqDto;
import site.metacoding.finals.dto.shop_table.ShopTableRespDto.AllShopTableRespDto;
import site.metacoding.finals.dto.shop_table.ShopTableRespDto.ShopTableSaveRespDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShopTableService {

    private final ShopTableRepository shopTableRepository;
    private final ShopRepository shopRepository;

    @Transactional
    public ShopTableSaveRespDto save(ShopTableSaveReqDto shopTableSaveReqDto, Long userId) {
        Shop shopPS = shopRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("해당 가게가 없습니다."));
        log.debug("디버그 : 가게ID " + shopPS.getId());

        for (int maxPeople : shopTableSaveReqDto.getMaxPeople()) {
            shopTableRepository.save(shopTableSaveReqDto.toShopTableEntity(maxPeople, shopPS));
        }

        return new ShopTableSaveRespDto(shopTableRepository.findByShopId(shopPS.getId()));
    }

    @Transactional
    public AllShopTableRespDto findAllByShopId(Long userId) {
        Shop shopPS = shopRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("해당 가게가 없습니다."));

        return new AllShopTableRespDto(shopTableRepository.findByShopId(shopPS.getId()));
    }
}
