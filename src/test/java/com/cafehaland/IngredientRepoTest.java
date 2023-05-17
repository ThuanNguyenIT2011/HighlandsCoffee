package com.cafehaland;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.cafehaland.pojo.Ingredient;
import com.cafehaland.pojo.User;
import com.cafehaland.users.repository.IngredientRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class IngredientRepoTest {
	@Autowired
	private IngredientRepository ingredientRepo;
	
	@Test
	public void testCreatIngredientFirst() {
		User user = new User(16);
		Ingredient ingredient = new Ingredient("Cafe", "Kg", user, 0);
		ingredient.setDate(new Date());
		Ingredient saveIngredient = ingredientRepo.save(ingredient);
		assertThat(saveIngredient.getId()).isGreaterThan(0);
	}
}
