package org.silverstar.category.repository;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public class EntityBase {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regDt;

    @LastModifiedDate
    private LocalDateTime updDt;

    @CreatedBy
    @Column(updatable = false)
    private String regBy;

    @LastModifiedBy
    private String updBy;
}
