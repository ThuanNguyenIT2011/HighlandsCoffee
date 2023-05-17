package com.cafehaland;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cafehaland.pojo.Ingredient;
import com.cafehaland.pojo.ProductSize;
import com.cafehaland.pojo.id.SyntaxId;
import com.cafehaland.users.repository.SyntaxReopository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SyntaxRepoTest {
	@Autowired
	private SyntaxReopository syntaxReopo;
	
	@Test
	public void deleteSyntax() {
//		syntaxReopo.deleteById(new SyntaxId(44, 3));
		syntaxReopo.deleteByProductSizeAndIngredient(new ProductSize(28), new Ingredient(2));
	}
	
	@Test
	public void testCountSyntax() {
		Long count = syntaxReopo.countById(new SyntaxId(44, 2));
		System.out.println(count);
	}
}
