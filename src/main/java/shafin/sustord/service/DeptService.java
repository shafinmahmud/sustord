package shafin.sustord.service;

import shafin.sustord.dao.DepartmentDao;
import shafin.sustord.entity.Department;

public class DeptService {
	
	protected final Department department;
	protected final DepartmentDao dao;
	
	public DeptService(int id) {
		this.dao = new DepartmentDao();
		this.department = dao.findOne(id);
	}
}
