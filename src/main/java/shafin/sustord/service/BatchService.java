package shafin.sustord.service;

import shafin.sustord.dao.StudentBatchDao;
import shafin.sustord.entity.StudentBatch;

public class BatchService {
	
	protected final StudentBatch batch;
	protected final StudentBatchDao dao;
	
	public BatchService(int id) {
		this.dao = new StudentBatchDao();
		this.batch = dao.findOne(id);
	}
	
	public BatchService(String batchTag) {
		this.dao = new StudentBatchDao();
		this.batch = dao.findOneByBatchTag(batchTag);
	}
}
