package site.metacoding.finals.domain.feature;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.metacoding.finals.domain.shop.Shop;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Feature {
    private Long featuerId;
    private String featureName;
    private Timestamp createAt;
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
}