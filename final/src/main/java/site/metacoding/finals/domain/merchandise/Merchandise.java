package site.metacoding.finals.domain.merchandise;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.boot.SpringBootConfiguration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.metacoding.finals.domain.shop.Shop;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Merchandise {
    private Long merchandiseId;
    private Integer merchandisePrice;
    private int maxPeople;
    private String merchandiseDay;
    private String merchandiseTime;
    private boolean isSaled; // true -> 1, false -> 0 (db 저장시)
    private Timestamp createAt;
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
