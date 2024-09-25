package com.adp.model;

import java.sql.Date;

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
public class HRDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="hrseq")
	@SequenceGenerator(name= "hrseq", sequenceName = "hrseq", initialValue = 201, allocationSize = 1)
	private int hrId;
	@Pattern(regexp = "[A-za-z\s]{3,}", message = "Name should contain only alphabet")
	private String hrName;
	@Email
	private String hrEmail;
	private String hrPassword;
	private String industry;
	private long hrMobileNo;
	@Pattern(regexp ="(Male|Female)", message = "Invalid Gender")
	private String gender;
	private int age;
	private Date hrDOB;
	private String role;
	

}



