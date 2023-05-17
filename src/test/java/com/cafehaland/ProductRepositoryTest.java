package com.cafehaland;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cafehaland.pojo.Ingredient;
import com.cafehaland.pojo.Product;
import com.cafehaland.pojo.ProductSize;
import com.cafehaland.pojo.Syntax;
import com.cafehaland.pojo.User;
import com.cafehaland.pojo.id.SyntaxId;
import com.cafehaland.users.repository.ProductRepository;
import com.cafehaland.users.repository.ProductSizeRepository;
import com.cafehaland.users.repository.SyntaxReopository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ProductRepositoryTest {
	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private ProductSizeRepository productSizeRepo;
	
	@Autowired
	private SyntaxReopository syntaxReopo;
	
	@Test
	public void testCountSyntax() {
		Long count = syntaxReopo.countById(new SyntaxId(28, 2));
		System.out.println(count);
	}
	
	@Test
	public void deleteSyntax() {
//		syntaxReopo.deleteById(new SyntaxId(28, 2));
		syntaxReopo.deleteByProductSizeAndIngredient(new ProductSize(28), new Ingredient(2));
	}
	
	@Test
	public void testCreateProductFirst() {
		Product product = new Product("Cafe1", true, new User(16));
		product.setImage("product-default.png");
		
		ProductSize productSize1 = new ProductSize();
		productSize1.setProduct(product);
		productSize1.setSize("X");
		productSize1.setPrice(10);
		
		
		ProductSize productSize2 = new ProductSize();
		productSize2.setProduct(product);
		productSize2.setSize("L");
		productSize2.setPrice(10);
		
		product.addSize(productSize1);
		product.addSize(productSize2);
		
		productRepo.save(product);
		//productSizeRepo.save(productSize1);
	}
	
	public void testAddSyntax() {
		ProductSize productSize1 = productSizeRepo.findById(10).get();
		Syntax syntax1 = new Syntax();
		syntax1.setQuantity(0.02);
		syntax1.setIngredient(new Ingredient(2));
		syntax1.setProductSize(productSize1);
		
		Syntax syntax2 = new Syntax();
		syntax2.setQuantity(0.02);
		syntax2.setIngredient(new Ingredient(3));
		syntax2.setProductSize(productSize1);
		productSize1.addSyntax(syntax1);
		productSize1.addSyntax(syntax2);
		
		productSizeRepo.save(productSize1);
	}
	
	@Test
	public void testUpdateProductById() {
		productRepo.update("ProductUpdate", "DES UPDATE", "av.jpg", false, new Date(), new User(16), 28);
	}
	
	@Test
	public void testListIngredientByProduct() {
		productRepo.listIngredientByProduct(38).forEach(igredient -> {
			System.out.println(igredient);
		});
	}
}
