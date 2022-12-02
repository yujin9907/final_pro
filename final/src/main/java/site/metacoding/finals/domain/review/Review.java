package site.metacoding.finals.domain.review;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
import site.metacoding.finals.domain.image_file.ImageFile;
import site.metacoding.finals.domain.shop.Shop;

// isdeleted 옵션 구현해야됨
@OnDelete(action = OnDeleteAction.CASCADE)
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Table(name = "review")
@Entity
public class Review extends AutoTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int score;
    private String content;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST) // 이거하면 저장 로직 때 shop, customer를 따로 추가 안해도 됨?
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "shop_id")
    @JsonIgnore
    private Shop shop;

    @OneToMany(mappedBy = "review", fetch = FetchType.LAZY)
    private List<ImageFile> imageFiles = new ArrayList<>();

    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;

}
