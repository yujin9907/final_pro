package site.metacoding.finals.dto.reservation;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import site.metacoding.finals.domain.shop.Shop;

public class ReservationRespDto {

    @AllArgsConstructor
    @Getter
    @Setter
    public static class ReservationDateRespDto {
        private List<Integer> date;

    }

}
