package site.metacoding.finals.domain.receipt;

import java.sql.Timestamp;

import javax.persistence.Entity;
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
    private Long receiptId;
    private int receiptPrice;
    private Timestamp createAt;
    @ManyToOne
    private Reservation Reservation;
}
