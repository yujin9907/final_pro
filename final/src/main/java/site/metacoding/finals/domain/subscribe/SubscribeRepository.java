package site.metacoding.finals.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;

import site.metacoding.finals.domain.customer.Customer;
import site.metacoding.finals.domain.reservation.Reservation;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {

    public Subscribe findByCustomerId(Long customerId);

    public void deleteByCustomerId(Long customerId);

}