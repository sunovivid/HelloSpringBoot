package org.sunovivid.hellospringbootagain.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA 엔티티 클래스가 BaseTimeEntity 클래스를 상속할 경우 필드들(CreatedDate, modifiedDate)도 칼럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate // 엔티티가 생성되어 저장될 때 시간이 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate // 조회한 엔티티 값을 변경할 때 시간이 자동 저장
    private LocalDateTime modifiedDate;
}
