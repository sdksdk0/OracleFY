package cn.tf.util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtil {
	
	
	/**
	 * 加载src目录下的c3p0-config.xml文件
	 */
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
	/**
	 * 获取数据源 
	 * @return 数据源
	 */
	public static ComboPooledDataSource getDataSource() {
		return dataSource;
	}

}
