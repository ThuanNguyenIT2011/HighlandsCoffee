package com.cafehaland;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cafehaland.pojo.Ingredient;
import com.cafehaland.pojo.Supplier;
import com.cafehaland.pojo.User;
import com.cafehaland.users.repository.SupplierRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SupplierRepositoryTest {
	@Autowired
	private SupplierRepository supplierRepo;
	
	
	@Test
	public void testCreatSupplierFirst() {
		User user = new User(16);
		Supplier supplier = new Supplier("NguyenThuan", "ntkt2k3@gmail.com", "0123456789", user);
		supplier.addIngredient(new Ingredient(1));
		supplier.addIngredient(new Ingredient(2));
		
		Supplier saveSupplier = supplierRepo.save(supplier);
		
		assertThat(saveSupplier.getId()).isGreaterThan(0);
	}
}
