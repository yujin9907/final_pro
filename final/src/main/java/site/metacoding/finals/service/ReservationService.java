package site.metacoding.finals.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.domain.shop.ShopRespository;
import site.metacoding.finals.dto.reservation.ReservationReqDto.ReservationSelectReqDto;
import site.metacoding.finals.dto.reservation.ReservationRespDto.ReservationDateRespDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ShopRespository shopRespository;

    public ReservationDateRespDto dateList(ReservationSelectReqDto dto) {
        log.debug("디버그 : " + " 서비스 진입");

        Shop shopPS = shopRespository.findById(dto.getShopId())
                .orElseThrow(() -> new RuntimeException("잘못된 가게 요청입니다"));

        log.debug("디버그 : " + shopPS.getOpentime());

        List<Integer> ableDate = new ArrayList<>();
        String todays = DateTimeFormatter.ofPattern("dd").format(LocalDate.now());
        int today = Integer.parseInt(todays);
        int initHour = shopPS.getPerHour();
        int endHour = shopPS.getSetReservationDate();

        for (int i = today; i < endHour; i += initHour) {
            log.debug("디버그 : " + i);
            ableDate.add(i);
        }

        log.debug("디버그 : " + today);
        log.debug("디버그 : " + initHour);
        log.debug("디버그 : " + endHour);

        return new ReservationDateRespDto(ableDate);

    }
}
