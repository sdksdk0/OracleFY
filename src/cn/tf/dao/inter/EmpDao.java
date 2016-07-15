package cn.tf.dao.inter;

import java.util.List;

import cn.tf.dao.impl.EmpDaoImpl;
import cn.tf.entity.Emp;

//员工管理模块，持久层接口
public interface EmpDao {

	//获取总记录数   @return 总记录数
	public Integer getAllRecoder()  throws Exception;
	
	//分页查询   start表示从第几条记录看似显示     end表示到第几条记录结束
	public List<Emp>  findAllRecord(int start,int end) throws Exception ;
	
}


