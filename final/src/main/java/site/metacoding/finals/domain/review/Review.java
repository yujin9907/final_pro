package site.metacoding.finals.domain.review;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.metacoding.finals.domain.customer.Customer;
import site.metacoding.finals.domain.shop.Shop;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Review {
    private Long reviewId;
    private int reviewScore;
    private String content;
    private String image;
    private Timestamp createAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;

}
