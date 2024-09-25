package com.adp.model;

import java.sql.Date;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "candidate_details")
public class Candidate {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="candidateseq")
	@SequenceGenerator(name="candidateseq", sequenceName = "candidateseq", initialValue = 16, allocationSize = 1 )
	private int candidateId;
	@Pattern(regexp = "[A-za-z\s]{3,}", message = "Name should contain only alphabet")
	private String firstName;
	@Pattern(regexp = "[A-za-z\s]{3,}", message = "Name should contain only alphabet")
	private String lastName;
	private Date candidateDOB;
	@Pattern(regexp ="(Male|Female)", message = "Invalid Gender")
	private String gender;
	private Long candidateMobileNo;
	@Email
	private String candidateEmail;
	private String candidatePassword;
	private String address;
	private String skills;
	private int experience;
	private String linkedinURL;
	private String githubURL;
	private String qualification;
	private String role;
	private String twitterURL;
	private String candidateBio;
	private String candidateImage;
	private String industry;
	private String profile;
	private String profession;
	
	

}

