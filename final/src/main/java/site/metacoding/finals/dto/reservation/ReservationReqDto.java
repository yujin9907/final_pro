package site.metacoding.finals.dto.reservation;

import lombok.Getter;
import lombok.Setter;

public class ReservationReqDto {
    @Getter
    @Setter
    public static class ReservationSelectReqDto {
        private Long shopId;
        private int maxPeople;
    }
}
