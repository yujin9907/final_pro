package site.metacoding.finals.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.domain.reservation.Reservation;
import site.metacoding.finals.domain.reservation.ReservationRepository;
import site.metacoding.finals.domain.shop.Shop;
import site.metacoding.finals.dummy.DummyEntity;

@Slf4j
@DataJpaTest
@ActiveProfiles("test")
public class ReservationRepositoryTest extends DummyEntity {

    @Autowired
    private ReservationRepository reservationRepository;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void 예약커스터머아이디조회() {
        Long CustomerId = 1L;

        List<Reservation> reservation = reservationRepository.findByCustomerId(CustomerId);

        log.debug("디버그 : " + reservation.get(0).getCustomer());

        // then
        assertEquals(reservation.get(0).getId(), 1);
    }

    @Test
    public void 인원수날짜에따른테이블목록테스트() {
        int maxPeople = 4;
        String date = "20221126";

        List<Reservation> reservation = reservationRepository.findByDataMaxPeople(maxPeople, date);

        log.debug("디버그 : " + reservation.size());

        // then
        assertEquals(reservation.get(0).getId(), 1);
    }

}
