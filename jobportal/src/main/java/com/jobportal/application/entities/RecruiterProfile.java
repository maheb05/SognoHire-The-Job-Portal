package com.jobportal.application.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recruiter_profile")
@Data @NoArgsConstructor
public class RecruiterProfile {

		@Id
	    private int userAccountId;

	    @OneToOne
	    @JoinColumn(name = "user_account_id")
	    @MapsId
	    private Users userId;

	    private String firstName;
	    private String lastName;
	    private String city;

	    private String state;

	    private String country;

	    private String company;

	    @Column(nullable = true, length = 64)
	    private String profilePhoto;


	    public RecruiterProfile(Users users) {
	        this.userId = users;
	    }
	    
	    @Transient
	    public String getPhotosImagePath() {
	        if (profilePhoto == null) return null;
	        return "/photos/recruiter/" + userAccountId + "/" + profilePhoto;
	    }
}
