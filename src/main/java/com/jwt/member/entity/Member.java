package com.jwt.member.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(	name = "tb_users", 
        uniqueConstraints = { 
	       @UniqueConstraint(columnNames = "username"),
	       @UniqueConstraint(columnNames = "email") 
        })
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String username;

	@Column(length = 500, nullable = false)
	private String password;

	@Column(length = 255, nullable = false)
	private String email;

	@Column(length = 15, nullable = false)
	private String role;
}
