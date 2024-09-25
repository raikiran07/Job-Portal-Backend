package com.adp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "savejobs_details")
public class SaveJob {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="saveJobseq")
	@SequenceGenerator(name="saveJobseq", sequenceName = "saveJobseq", initialValue = 6, allocationSize = 1 )
	private int saveJobId;
	@ManyToOne
	@JoinColumn(name = "candidateId", referencedColumnName = "candidateId")
	private Candidate candidate;
	@ManyToOne 
	@JoinColumn(name="jobId", referencedColumnName = "jobId")
	private JobPost jobPost;

}
