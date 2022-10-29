package com.jwt.member.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@MappedSuperclass
@EntityListeners(value = {AuditingEntityListener.class})
public class BaseEntity {

    @CreatedBy
    @Column(updatable = false, nullable = false)
    private String credId;

    @LastModifiedBy
	@Column(nullable = false)
    private String updtId;	

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime credTime;

    @LastModifiedDate
	@Column(nullable = false)
    private LocalDateTime updtTime;

}
