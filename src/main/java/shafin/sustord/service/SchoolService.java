package shafin.sustord.service;

import shafin.sustord.dao.SchoolDao;
import shafin.sustord.entity.School;

public class SchoolService {
	
	protected final School school;
	protected final SchoolDao dao;
	
	public SchoolService(int id) {
		this.dao = new SchoolDao();
		this.school = dao.findOne(id);
	}
}
