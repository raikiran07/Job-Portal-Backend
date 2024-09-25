package com.adp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

import com.adp.exception.AlreadyAppliedJobPException;
import com.adp.exception.AlreadySaveJobPException;
import com.adp.exception.AppliedJobNotFoundException;
import com.adp.exception.SaveJobNotFoundException;
import com.adp.model.AppliedJob;
import com.adp.model.SaveJob;
import com.adp.repository.SaveJobRepository;

@Service
public class SaveJobServiceImpl implements SaveJobService {
	@Autowired
	private SaveJobRepository saveJobRepository;
	
	@Override
	public List<SaveJob> getallSaveJobs() {
		return saveJobRepository.findAll().stream().sorted(Comparator.comparingInt(SaveJob::getSaveJobId)
				.reversed()).toList();
	}

	@Override
	public String addSaveJob(SaveJob saveJob) {
		Optional<SaveJob> saveOptional = saveJobRepository.findAllByJobPost_JobId(saveJob.getJobPost().getJobId());		
		if(saveOptional.isPresent()) {
			throw new AlreadySaveJobPException("Already saved job for the same job");
		}
		saveJobRepository.save(saveJob);
		return "added successfully";
	}

	@Override
	public String deleteSaveJob(int saveJobId) {
		Optional<SaveJob> saveOptional = saveJobRepository.findById(saveJobId);
		if(saveOptional.isEmpty()) {
			
			throw new SaveJobNotFoundException("save job with Id "+saveJobId+" not found");
		}
		saveJobRepository.deleteById(saveJobId);
		return "deleted successfully";
	}

	@Override
	public List<SaveJob> getSaveJobsByCandiateId(int candidateId) {
		return saveJobRepository.findAllByCandidate_CandidateId(candidateId)
				.stream().sorted(Comparator.comparingInt(SaveJob::getSaveJobId)
				.reversed()).toList();
	}
	

}
