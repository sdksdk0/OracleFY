package cn.tf.dao.impl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.tf.dao.inter.EmpDao;
import cn.tf.entity.Emp;
import cn.tf.util.JdbcUtil;

public  class EmpDaoImpl implements  EmpDao{

	public Integer getAllRecoder() throws Exception {
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = "SELECT COUNT(EMPNO) FROM EMP";
		BigDecimal bigDecimal = (BigDecimal) runner.query(sql,new ScalarHandler());
		return bigDecimal.intValue();
	}
	public List<Emp> findAllRecord(int start, int end) throws Exception {
		QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
		String sql = " SELECT A.EMPNO,A.ENAME,A.JOB,A.MGR,A.HIREDATE,A.SAL,A.COMM,A.DEPTNO" +
				     " FROM (SELECT ROWNUM ID,EMP.* FROM EMP WHERE ROWNUM<?) A" +
				     " WHERE ID>?";
		Object[] params = {end,start};
		return runner.query(sql,new BeanListHandler<Emp>(Emp.class),params);
	}
	
	public static void main(String[] args) throws Exception {
		EmpDaoImpl  dao=new EmpDaoImpl();
		System.out.println("共有:"+dao.getAllRecoder()+"个员工");
	
		for(Emp e : dao.findAllRecord(0,4)){
			System.out.println(e);
		}
		System.out.println("------------------------------------------------第2页");
		for(Emp e : dao.findAllRecord(3,7)){
			System.out.println(e);
		}
		System.out.println("------------------------------------------------第3页");
		for(Emp e : dao.findAllRecord(6,10)){
			System.out.println(e);
		}
		System.out.println("------------------------------------------------第4页");
		for(Emp e : dao.findAllRecord(9,13)){
			System.out.println(e);
		}
		System.out.println("------------------------------------------------第5页");
		for(Emp e : dao.findAllRecord(12,16)){
			System.out.println(e);
		}
	
	
	}

	

}
