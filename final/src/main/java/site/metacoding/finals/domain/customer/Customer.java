package site.metacoding.finals.domain.customer;

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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String customerName;
    private String customerPhoneNumber;
    @CreationTimestamp
    private Timestamp createAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
