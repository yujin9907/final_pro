package site.metacoding.finals.domain.receiptMenu;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.metacoding.finals.domain.receipt.Receipt;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ReceiptMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptMenuId;
    private String receiptMenuName;
    private int receiptMenuPrice;
    private int receiptMenuQty;
    private Timestamp createAt;
    @OneToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;
}
