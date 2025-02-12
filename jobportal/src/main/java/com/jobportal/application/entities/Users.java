package com.jobportal.application.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name = "users")
@Data @NoArgsConstructor
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	
	@Column(unique = true)
	private String email;
	
	@NotEmpty
	private String password;
	
	private boolean isActive;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date registrationDate;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userTypeId", referencedColumnName = "userTypeId")
	private UsersType userTypeId;
}
