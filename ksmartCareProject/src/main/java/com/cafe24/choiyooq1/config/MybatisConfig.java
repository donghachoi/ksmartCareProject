package com.cafe24.choiyooq1.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.ApplicationContext;

/*	@Configuration
	@MapperScan(value = "kr.or.ksmart.springboot34_mybatis")
*/
public class MybatisConfig {
	
	/* @Bean(name = "mybatisSqlSessionFactory") */
	

	public SqlSessionFactory mybatisSqlSessionFactory(DataSource dataSource,ApplicationContext context) throws Exception {
		//DataSource dataSource == application.properties 에서 설정한 datasource 값을 가지고있는다.
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setMapperLocations(context.getResources("classpath:mapper/**/*.xml"));
		bean.setDataSource(dataSource);
		return bean.getObject();
		
	}
}