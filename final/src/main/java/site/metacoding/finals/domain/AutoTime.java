package site.metacoding.finals.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AutoTime {

    @LastModifiedDate
    @Column(nullable = false)
    protected LocalDateTime updateAt;

    @LastModifiedDate
    @Column(nullable = false)
    protected LocalDateTime createdAt;

}
