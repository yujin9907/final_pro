package site.metacoding.finals.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.finals.dto.ResponseDto;
import site.metacoding.finals.dto.reservation.ReservationReqDto.ReservationDateReqDto;
import site.metacoding.finals.dto.reservation.ReservationReqDto.ReservationSelectReqDto;
import site.metacoding.finals.dto.reservation.ReservationRespDto.ReservationDateRespDto;
import site.metacoding.finals.service.ReservationService;

@RestController
@RequiredArgsConstructor
public class ReservationApiController {

    private final ReservationService reservationService;

    // @GetMapping("/reservation/date/{shopId}/{date}")

    @PostMapping("/reservation/date")
    public ResponseEntity<?> reservationDate(@RequestBody ReservationSelectReqDto dto) {
        ReservationDateRespDto respDto = reservationService.dateList(dto);
        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "예약 가능 날짜 조회", respDto), HttpStatus.OK);
    }

    @PostMapping("/reservation/person")
    public ResponseEntity<?> reservationPerson(@RequestBody ReservationSelectReqDto dto) {

        return new ResponseEntity<>(new ResponseDto<>(HttpStatus.OK, "예약 가능 날짜 조회", respDto), HttpStatus.OK);
    }

}
