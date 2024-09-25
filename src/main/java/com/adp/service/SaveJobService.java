package com.adp.service;

import java.util.List;

import com.adp.model.SaveJob;

public interface SaveJobService {
	List<SaveJob> getallSaveJobs();
	String addSaveJob(SaveJob saveJob);
	String deleteSaveJob(int saveJobId);
	List<SaveJob> getSaveJobsByCandiateId(int candidateId);
}
