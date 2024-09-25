package com.adp.dto;

import com.adp.model.Candidate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AppliedIdCandidateDTO {
    
    private Candidate candidate;
    
    private int appliedId;
    
    private String status;

}