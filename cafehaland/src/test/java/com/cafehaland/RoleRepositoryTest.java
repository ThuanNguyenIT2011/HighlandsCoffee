package com.cafehaland;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cafehaland.pojo.Role;
import com.cafehaland.users.repository.RoleRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {
	@Autowired
	RoleRepository roleRepo;
	
	@Test
	public void testCreateRole() {
		Role roleAdmin = new Role("Admin", "mange everything");
		Role roleSave = roleRepo.save(roleAdmin);
		assertThat(roleSave.getId()).isGreaterThan(0);
	}
	
	@Test
	public void testCreateRestRole() {
		Role roleSalespersson = new Role("Saleperson", "Orders");
		Role roleEditor = new Role("Editor","Manage products, syntax product");
		Role roleInventory = new Role("Inventory", "Import and order materials");
		Role roleBartender = new Role("Bartender", "Making drink and complete orders");
		
		roleRepo.saveAll(List.of(roleSalespersson, roleEditor, roleInventory, roleBartender));
	}
}
