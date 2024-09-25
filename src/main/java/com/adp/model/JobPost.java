package com.adp.model;

import java.sql.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "jobPost_details")
public class JobPost {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jobseq")
	@SequenceGenerator(name= "jobseq", sequenceName = "jobseq", initialValue = 101, allocationSize = 1)
	private int jobId;
	@Column(nullable = false)
	private String jobTitle;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private Date postedDate;
	@Column(nullable = false)
	private int noOfOpening;
	@Column(nullable = false)
	private String location;
	@Column(nullable = false)
	private String salary;
	@Column(nullable = false)
	private String industry;
	@Pattern(regexp = "(Full Time|Internship|Freelancing|Part Time|Other)")
	@Column(nullable = false)
	private String employementType;
	@Column(nullable = false)
	private String requiredSkills;
	private String preferredSkill;
	private String jobQualification;
	private int jobExperience;
	private String rolesResponsibility;
	@Pattern(regexp = "Open|Closed")
	private String status;
	private String companyLogo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hrId", referencedColumnName = "hrId")
	private HRDetails hrDetails;
	
	
	
	
	
}

