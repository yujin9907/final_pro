package site.metacoding.finals.domain.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    // 반환타입 메서드명 (매개변수)

    // findBy규칙 : 문법
    public Optional<User> findByUsername(String username);

    @Query(value = "select * from users", nativeQuery = true)
    public List<User> findByIgnoreAll();

}
