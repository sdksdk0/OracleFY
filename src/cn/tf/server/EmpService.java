package cn.tf.server;

import java.util.List;

import cn.tf.dao.impl.EmpDaoImpl;
import cn.tf.dao.inter.EmpDao;
import cn.tf.entity.Emp;
import cn.tf.entity.Page;



public class EmpService implements IEmpService{
	private EmpDao iEmpDao = new EmpDaoImpl();
	public Page show(int currPageNO) throws Exception {
		Page page = new Page();
		
		//封装当前页号
		page.setCurrPageNO(currPageNO);
		
		//封装总记录数
		Integer allRecordNO = iEmpDao.getAllRecoder();
		page.setAllRecordNO(allRecordNO);
		
		//封装总页数
		Integer allPageNO = null;
		if(page.getAllRecordNO() % page.getPerPageSize() == 0){
			allPageNO = page.getAllRecordNO() / page.getPerPageSize();
		}else{
			allPageNO = page.getAllRecordNO() / page.getPerPageSize() + 1;
		}
		page.setAllPageNO(allPageNO);
		
		//封装该本显示的内容
		Integer start = (page.getCurrPageNO()-1) * page.getPerPageSize();
		Integer end = page.getCurrPageNO() * page.getPerPageSize() + 1;
		List<Emp> empList = iEmpDao.findAllRecord(start,end);
		page.setEmpList(empList);
		
		return page;
	}
	
	
	
	
	
	
	
	public static void main(String[] args) throws Exception{
		EmpService service = new EmpService();
		System.out.println("---------------------------------------NO1");
		
		Page page = service.show(1);
		for(Emp e : page.getEmpList()){
			System.out.println(e);
		}
		System.out.println("---------------------------------------NO2");
		page = service.show(2);
		for(Emp e : page.getEmpList()){
			System.out.println(e);
		}
		System.out.println("---------------------------------------NO3");
		page = service.show(3);
		for(Emp e : page.getEmpList()){
			System.out.println(e);
		}
		System.out.println("---------------------------------------NO4");
		page = service.show(4);
		for(Emp e : page.getEmpList()){
			System.out.println(e);
		}
		System.out.println("---------------------------------------NO5");
		page = service.show(5);
		for(Emp e : page.getEmpList()){
			System.out.println(e);
		}
	}
}





