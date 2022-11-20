package site.metacoding.finals.domain.user;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.metacoding.finals.config.enums.Role;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter // 나중에 삭제
@Table(name = "users") // 해당 엔티티를 매핑할 때 지정할 테이블 명, h2 사용시 user 예약어 주의
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;
    @CreationTimestamp
    private Timestamp createAt;

    // oauth2
    private String provider;
    private String providerId;

}
