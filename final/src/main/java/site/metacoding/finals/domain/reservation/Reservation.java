package site.metacoding.finals.domain.reservation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.metacoding.finals.domain.AutoTime;
import site.metacoding.finals.domain.customer.Customer;
import site.metacoding.finals.domain.shop_table.ShopTable;

// isdeleted 옵션 구현해야됨
@OnDelete(action = OnDeleteAction.CASCADE)
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Table(name = "reservation")
@Entity
public class Reservation extends AutoTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 10)
    private String reservationTime;
    @Column(nullable = false, length = 10)
    private String reservationDate;
    // @Converter
    @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "shop_table_id")
    private ShopTable shopTable;

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;
}
