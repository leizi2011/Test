package com.cmcc.inter.datebase.util;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import com.cmcc.inter.tools.LogUtils;


/**
 * @author iversoncl
 * @Date 2015年7月8日
 * @Project InterfaceFramework
 */
public class MyBatisUtil {

	/**
	 * @Description: 获得MyBatis SqlSessionFactory
	 *               SqlSessionFactory负责创建SqlSession，
	 *               一旦创建成功，就可以用SqlSession实例来执行映射语句，commit，rollback，close等方法。
	 * @return SqlSessionFactory
	 * @author: iversoncl
	 * @time:2015年4月16日 下午2:49:36
	 */
	public static SqlSessionFactory getSessionFactory() {
		SqlSessionFactory sessionFactory = null;
		String resource = "Configuration.xml";
		try {
			sessionFactory = new SqlSessionFactoryBuilder().build(Resources
					.getResourceAsReader(resource));
			LogUtils.info(MyBatisUtil.class, "session build success...");
			return sessionFactory;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
