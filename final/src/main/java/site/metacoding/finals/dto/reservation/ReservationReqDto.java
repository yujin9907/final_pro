package site.metacoding.finals.dto.reservation;

import lombok.Getter;
import lombok.Setter;

public class ReservationReqDto {
    @Getter
    @Setter
    public static class ReservationDateReqDto {
        private Long shopId;
    }
}
