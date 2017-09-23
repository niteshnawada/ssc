package rewards;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import config.RewardsConfig;


@Configuration
@Import(RewardsConfig.class)
public class SystemTestConfig {
	
	/**
	 * Creates an in-memory "rewards" database populated 
	 * with test data for fast testing
	 */
	@Bean
	public DataSource dataSource(){
		return
			(new EmbeddedDatabaseBuilder())
			.addScript("classpath:rewards/testdb/schema.sql")
			.addScript("classpath:rewards/testdb/data.sql")
			.build();
	}	
	
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		
		// We've set these up for you ...
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabase(Database.HSQL);

		Properties props = new Properties();
		props.setProperty("hibernate.format_sql", "true");

		// Your turn ... configure the emf like the example in the slides ...
		emf.setDataSource(dataSource());
		emf.setJpaVendorAdapter(adapter);
		emf.setJpaProperties(props);
		emf.setPackagesToScan("rewards.internal");
		return emf;
	}

	@Bean	
	public JpaTransactionManager transactionManager(EntityManagerFactory emf){
		return new JpaTransactionManager(emf);
	}
}
