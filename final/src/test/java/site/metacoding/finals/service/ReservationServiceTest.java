package site.metacoding.finals.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.exceptions.base.MockitoException;

import site.metacoding.finals.domain.reservation.ReservationRepository;

// @ExtendWith(MockitoException.class)
public class ReservationServiceTest {

    // 단위 테스트는 통합테스트보다 가볍게 진행할 수 있도록 springboottest 보다 mockito를 통해서 가볍게 테스트

    // 1. 빈 리포지토리 만들기
    @Mock
    private ReservationRepository reservationRepository;
    @InjectMocks
    private ReservationService reservationService;

}
