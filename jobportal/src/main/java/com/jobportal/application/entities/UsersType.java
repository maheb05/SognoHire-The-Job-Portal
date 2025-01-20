package com.jobportal.application.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users_type")
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
public class UsersType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userTypeId;
	
	private String userTypeName;
	
	@OneToMany(targetEntity = Users.class, mappedBy = "userTypeId", cascade = CascadeType.ALL)
	private List<Users> users;

	@Override
	public String toString() {
		return "UsersType [userTypeId=" + userTypeId + ", userTypeName=" + userTypeName + "]";
	}
	
}
