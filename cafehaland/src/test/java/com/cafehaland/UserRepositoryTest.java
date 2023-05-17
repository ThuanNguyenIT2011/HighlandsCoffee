package com.cafehaland;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cafehaland.pojo.Account;
import com.cafehaland.pojo.Role;
import com.cafehaland.pojo.User;
import com.cafehaland.users.repository.AccountRepository;
import com.cafehaland.users.repository.RoleRepository;
import com.cafehaland.users.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AccountRepository accountRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Test
	public void testCreateUserFirst() {
		User user = new User("Thuan", "Nguyen", "0123456789");
		Account account = new Account("thuannit2011@gmail.com", "20112002");
		
		Role roleAdmin = roleRepo.findById(1).get();
		
		account.addRole(roleAdmin);
		
		user.setAccount(account);
		
		System.out.println(roleAdmin.getName());
		
		accountRepo.save(account);
		User saveUser = userRepo.save(user);
		
//		assertThat(saveUser.getId()).isGreaterThan(0);
		
	}
	
	@Test 
	public void getAllUser () {
		List<User> listUsers = userRepo.findAll();
		listUsers.forEach(
					ele -> System.out.println(ele.getFirstName())
				);
	}
	
	
}
