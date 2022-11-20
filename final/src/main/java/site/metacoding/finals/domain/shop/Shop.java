package site.metacoding.finals.domain.shop;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.metacoding.finals.domain.user.User;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shopId;
    private String shopName;
    private String shopPhoneNumber;
    private String Category;
    private String address;
    private String opentime;
    private String closetime;
    private boolean isOpened;
    @CreationTimestamp
    private Timestamp createAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
