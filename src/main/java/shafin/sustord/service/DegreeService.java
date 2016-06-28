package shafin.sustord.service;

import shafin.sustord.dao.DegreeDao;
import shafin.sustord.entity.Degree;

public class DegreeService {
	protected final Degree degree;
	protected final DegreeDao dao;
	
	public DegreeService(int id) {
		this.dao = new DegreeDao();
		this.degree = dao.findOne(id);
	}
}
