package site.metacoding.finals.domain.shop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.metacoding.finals.domain.AutoTime;
import site.metacoding.finals.domain.image_file.ImageFile;
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
    @Column(nullable = false, length = 30)
    private String address;
    @Column(length = 50)
    private String category;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String information;
    @Column(nullable = false, length = 10)
    private String openTime;
    @Column(nullable = false, length = 10)
    private String closeTime;

    @Column(nullable = false)
    private int perPrice;
    @Column(nullable = false)
    private int perHour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    // 페치 조인은 들고올 때 조인으로 한번에 들고 옴.
    // 레이지를 사용하면 원래 필요할 때만 데이터를 조회함. => one to one 관계에선 레이지 적용 안 됨.
    // 페치 적용 x => 무조건 셀렉시 연관 테이블 조회함, 한방 쿼리가 아닌 여러번 셀렉으로.
    @OneToOne(mappedBy = "shop", fetch = FetchType.LAZY)
    // @JsonManagedReference
    private ImageFile imageFile = new ImageFile(null, null, null, null, null);
    // null 방지 어케?
}
