package site.metacoding.finals.domain.merchandise;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.jsf.FacesContextUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.metacoding.finals.config.enums.Saled;
import site.metacoding.finals.domain.AutoTime;
import site.metacoding.finals.domain.shop.Shop;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Table(name = "merchandise")
@Entity
public class Merchandise extends AutoTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer price;
    @Column(nullable = false)
    private int maxPeople;
    @Column(nullable = false, length = 10)
    private String merchandiseDate;
    @Column(nullable = false, length = 10)
    private String startTime;
    @Column(nullable = false, length = 10)
    private String endTime;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Saled saled; // sale, none
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
