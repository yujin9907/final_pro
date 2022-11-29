package site.metacoding.finals.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.config.auth.PrincipalUser;
import site.metacoding.finals.domain.customer.Customer;
import site.metacoding.finals.domain.customer.CustomerRepository;
import site.metacoding.finals.domain.image_file.ImageFile;
import site.metacoding.finals.domain.image_file.ImageFileRepository;
import site.metacoding.finals.domain.review.Review;
import site.metacoding.finals.domain.review.ReviewRepository;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.shop.ShopRepository;
import site.metacoding.finals.dto.review.ReviewReqDto.ReviewSaveReqDto;
import site.metacoding.finals.dto.review.ReviewRespDto.ReviewSaveRespDto;
import site.metacoding.finals.handler.ImageFileHandler;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {

        private final ShopRepository shopRespository;
        private final CustomerRepository customerRepository;
        private final ReviewRepository reviewRepository;
        private final ImageFileRepository imageFileRepository;
        private final ImageFileHandler imageFileHandler;

        @Transactional
        public ReviewSaveRespDto save(List<MultipartFile> multipartFiles, ReviewSaveReqDto dto,
                        PrincipalUser principalUser) {
                // log.debug("디버그 : " + principalUser.getUsername());
                // System.out.println("디버그 : " + principalUser.getUsername());
                // PrincipalUser principalUsers = (PrincipalUser) principalUser;

                Customer customerPS = customerRepository.findByUserId(principalUser.getUser().getId())
                                .orElseThrow(() -> new RuntimeException("잘못된 유저입니다"));
                Shop shopPS = shopRespository.findById(dto.getShopId())
                                .orElseThrow(() -> new RuntimeException("잘못된 가게입니다"));

                List<ImageFile> images = imageFileHandler.storeFile(multipartFiles);
                for (ImageFile img : images) {
                        imageFileRepository.save(img);
                }

                System.out.println("저장완료");
                Review review = reviewRepository.save(dto.toEntity(customerPS,
                                shopPS));

                return new ReviewSaveRespDto(review, images);
        }
}