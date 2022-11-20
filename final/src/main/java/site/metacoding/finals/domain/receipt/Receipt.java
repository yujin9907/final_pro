package site.metacoding.finals.domain.receipt;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.metacoding.finals.domain.reservation.Reservation;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptId;
    private int receiptPrice;
    private Timestamp createAt;
    @ManyToOne
    private Reservation Reservation;
}
