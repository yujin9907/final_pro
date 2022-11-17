package site.metacoding.finals.domain.menu;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Menu {
    private Long menuId;
    private String menuName;
    private Integer menuPrice;
    private Timestamp createAt;
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
