package site.metacoding.finals.domain.shop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.metacoding.finals.domain.AutoTime;
import site.metacoding.finals.domain.user.User;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "shop")
@Entity
public class Shop extends AutoTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20, unique = true)
    private String shopName;
    @Column(nullable = false, length = 12, unique = true)
    private String phoneNumber;
    @Column(length = 50)
    private String Category;
    @Column(nullable = false, length = 30)
    private String address;
    private String information;
    @Column(nullable = false, length = 10)
    private String opentime;
    @Column(nullable = false, length = 10)
    private String closetime;
    @Column(length = 100)
    private String image;

    @Column(nullable = false)
    private int perPrice;
    @Column(nullable = false)
    private int perHour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
