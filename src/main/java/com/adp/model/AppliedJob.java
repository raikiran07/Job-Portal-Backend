package com.adp.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
@Table(name = "applied_details")
public class AppliedJob {
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="applyJobseq")
		@SequenceGenerator(name="applyJobseq", sequenceName = "applyJobseq", initialValue = 6, allocationSize = 1 )
		private int appliedId;
		@ManyToOne
		@JoinColumn(name = "candidateId", referencedColumnName = "candidateId")
		private Candidate candidate;
		
		@ManyToOne 
		@JoinColumn(name="jobId", referencedColumnName = "jobId")
		private JobPost jobPost;
		
		@Pattern(regexp = ("Accepted|Rejected|Applied"))
		private String status;
		
		@Lob
		private byte[] pdf;
}
