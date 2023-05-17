package com.cafehaland;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cafehaland.pojo.Product;
import com.cafehaland.users.repository.ProductSizeRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductSizeReopositoryTest {
	@Autowired
	private ProductSizeRepository productSizeRepo;
	
	@Test
	public void testUpdatePrice() {
		productSizeRepo.updatePrice(new Product(28), "L", 9);
	}
}
