package site.metacoding.finals.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import lombok.extern.slf4j.Slf4j;
import site.metacoding.finals.domain.reservation.Reservation;
import site.metacoding.finals.domain.reservation.ReservationRepository;

@Slf4j
@Sql("classpath:sql/dml.sql")
@DataJpaTest
@ActiveProfiles("test")
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void 예약커스터머아이디조회() {
        Long CustomerId = 1L;

        List<Reservation> reservation = reservationRepository.findByCustomerId(CustomerId);

        log.debug("디버그 : " + reservation.get(0).getCustomer());

        // then
        assertEquals(reservation.get(0).getId(), 1);
    }

}
