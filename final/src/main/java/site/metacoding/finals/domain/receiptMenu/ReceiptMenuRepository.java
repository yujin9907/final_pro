package site.metacoding.finals.domain.receiptMenu;

import org.springframework.data.jpa.repository.JpaRepository;

import site.metacoding.finals.domain.receipt.Receipt;

public interface ReceiptMenuRepository extends JpaRepository<Receipt, Long> {

}
