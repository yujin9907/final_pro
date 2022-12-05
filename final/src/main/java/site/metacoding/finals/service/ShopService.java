package site.metacoding.finals.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.domain.image_file.ImageFile;
import site.metacoding.finals.domain.image_file.ImageFileRepository;
import site.metacoding.finals.domain.option.Option;
import site.metacoding.finals.domain.option.OptionRepository;
import site.metacoding.finals.domain.option_manage.OptionManage;
import site.metacoding.finals.domain.option_manage.OptionManageRepository;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.shop.ShopRepository;
import site.metacoding.finals.domain.user.User;
import site.metacoding.finals.domain.user.UserRepository;
import site.metacoding.finals.dto.shop.ShopReqDto.ShopInfoSaveReqDto;
import site.metacoding.finals.dto.shop.ShopReqDto.ShopJoinReqDto;
import site.metacoding.finals.dto.shop.ShopRespDto.ShopDetailRespDto;
import site.metacoding.finals.dto.shop.ShopRespDto.ShopInfoSaveRespDto;
import site.metacoding.finals.dto.shop.ShopRespDto.ShopJoinRespDto;
import site.metacoding.finals.dto.shop.ShopRespDto.ShopListRespDto;
import site.metacoding.finals.handler.ImageFileHandler;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShopService {

    @PersistenceContext
    private EntityManager em;

    private final UserRepository userRepository;
    private final ShopRepository shopRepository;
    private final OptionRepository optionRepository;
    private final OptionManageRepository optionManageRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ImageFileRepository imageFileRepository;
    private final ImageFileHandler imageFileHandler;

    // 회지
    @Transactional
    public ShopJoinRespDto join(ShopJoinReqDto shopJoinReqDto) {
        String encPassword = bCryptPasswordEncoder.encode(shopJoinReqDto.getPassword());
        shopJoinReqDto.setPassword(encPassword);

        User userPS = userRepository.save(shopJoinReqDto.toUserEntity());

        return new ShopJoinRespDto(userPS);
    }

    @Transactional
    public ShopInfoSaveRespDto saveInformation(ShopInfoSaveReqDto shopInfoSaveReqDto, User user) {

        // shop save
        Shop shopPS = shopRepository.save(shopInfoSaveReqDto.toInfoSaveEntity(user));

        // option save
        List<OptionManage> optionManageList = new ArrayList<>();
        for (int option : shopInfoSaveReqDto.getOptionList()) {
            // 왜 getById는 안되는거임??
            Optional<Option> optionPS = optionRepository.findById(Long.valueOf(option));
            OptionManage optionManage = optionManageRepository
                    .save(shopInfoSaveReqDto.toOptionManageEntity(shopPS, optionPS.get()));
            optionManageList.add(optionManage);
        }

        // images save
        List<ImageFile> images = imageFileHandler.storeFile(shopInfoSaveReqDto.getImages());
        for (ImageFile img : images) {
            img.setShop(shopPS);
            imageFileRepository.save(img);
        }

        return new ShopInfoSaveRespDto(shopPS, optionManageList, images);
    }

    public List<ShopListRespDto> List() {
        // em.clear();

        List<Shop> shopPS = shopRepository.findAllList();
        return shopPS.stream().map((shop) -> new ShopListRespDto(shop)).collect(Collectors.toList());
    }

    public List<ShopListRespDto> categoryList(String categoryName) {
        List<Shop> shopList = shopRepository.findByCategory(categoryName);

        return shopList.stream()
                .map((dto) -> new ShopListRespDto(dto)).collect(Collectors.toList());

    }

    @Transactional
    public ShopDetailRespDto detatil(Long shopId) {
        // 가게 정보
        Shop shopPS = shopRepository.findById(shopId)
                .orElseThrow(() -> new RuntimeException("잘못된 가게 요청"));
        // 날짜 + 인원 => 예약 가능 시간 조회

        // 가게 특징
        List<Long> optionList = optionManageRepository.findByShopIdToOptionId(shopId);
        // List<Integer> optionPS = new ArrayList<>();
        // for (Option option : optionList) {
        // optionPS.add(option.getId().intValue());
        // }

        return new ShopDetailRespDto(shopPS, optionList);
    }
}
