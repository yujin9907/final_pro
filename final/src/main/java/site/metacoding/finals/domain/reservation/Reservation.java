package site.metacoding.finals.domain.reservation;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.metacoding.finals.domain.customer.Customer;
import site.metacoding.finals.domain.merchandise.Merchandise;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    private Long reservationId;
    private Timestamp createAt;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name = "merchandise_id")
    private Merchandise merchandise;
}
