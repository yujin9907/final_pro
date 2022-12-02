package site.metacoding.finals.domain.customer;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.metacoding.finals.domain.AutoTime;
import site.metacoding.finals.domain.user.User;
import site.metacoding.finals.dto.customer.CustomerReqDto.CustomerUpdateReqDto;

@SQLDelete(sql = "UPDATE customer SET is_deleted = true where id = ?")
@Where(clause = "is_deleted = false") // 디폴트로 동작하는 쿼리
@EnableJpaAuditing
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "customer")
@Getter
public class Customer extends AutoTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 12)
    private String phoneNumber;
    @Column(length = 30)
    private String address;
    @Column(name = "is_deleted")
    private Boolean isDeleted = Boolean.FALSE;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    // @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    // private List<Reservation> reservation = new ArrayList<>();

    public Customer toEntity(CustomerUpdateReqDto dto) {
        return Customer.builder()
                .id(this.id)
                .name(dto.getName())
                .address(dto.getAddress())
                .phoneNumber(dto.getPhoneNumber())
                .user(this.user)
                .build();
    }

}
