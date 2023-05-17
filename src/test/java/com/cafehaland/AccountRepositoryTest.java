package com.cafehaland;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.cafehaland.pojo.Account;
import com.cafehaland.users.repository.AccountRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class AccountRepositoryTest {
	@Autowired
	AccountRepository accoutRepo;
	
	@Test
	public void testGetUserById() {
//		Account account = accoutRepo.getAccountByEmail("thuannit2011@gmail.com");
		Account account = accoutRepo.findById("thuannit2011@gmail.com").get();
		System.out.println(account.getEmail());
	}
	
	@Test
	public void testUpdateEnabled() {
		accoutRepo.updateEnabledStatus("thuan20112002@gmail.com", true);
	}
}
