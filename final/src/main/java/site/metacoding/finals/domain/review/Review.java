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

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.metacoding.finals.domain.AutoTime;
import site.metacoding.finals.domain.customer.Customer;
import site.metacoding.finals.domain.image_file.ImageFile;
import site.metacoding.finals.domain.shop.Shop;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "image_file_id")
    private List<ImageFile> imageFile;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id")
    private Shop shop;

    @OneToMany(mappedBy = "review")
    private List<ImageFile> imageFiles = new ArrayList<>();

}
