package config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import rewards.RewardNetwork;
import rewards.internal.RewardNetworkImpl;
import rewards.internal.account.JdbcAccountRepository;
import rewards.internal.restaurant.JdbcRestaurantRepository;
import rewards.internal.reward.JdbcRewardRepository;

@Configuration
public class RewardsConfig {
	
	@Autowired
	DataSource dataSource;
	
	@Bean JdbcAccountRepository accountRepository(){
		JdbcAccountRepository repository = new JdbcAccountRepository();
		repository.setDataSource(dataSource);
		return repository;
	}
	
	@Bean JdbcRestaurantRepository restaurantRepository(){
		JdbcRestaurantRepository repository = new JdbcRestaurantRepository();
		repository.setDataSource(dataSource);
		return repository;
	}
	
	@Bean JdbcRewardRepository rewardRepository(){
		JdbcRewardRepository repository = new JdbcRewardRepository();
		repository.setDataSource(dataSource);
		return repository;
	}
	
	@Bean RewardNetwork rewardNetwork(JdbcAccountRepository accountRepo, JdbcRestaurantRepository restaurantRepo, JdbcRewardRepository rewardRepo){
		return new RewardNetworkImpl(accountRepo, restaurantRepo, rewardRepo);
	}

}
