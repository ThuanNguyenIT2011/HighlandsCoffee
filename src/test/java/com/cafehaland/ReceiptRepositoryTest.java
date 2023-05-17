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
import com.cafehaland.pojo.Receipt;
import com.cafehaland.pojo.ReceiptDetail;
import com.cafehaland.pojo.Supplier;
import com.cafehaland.pojo.User;
import com.cafehaland.users.repository.ReceiptRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ReceiptRepositoryTest {
	@Autowired
	private ReceiptRepository receiptRepo;
	
	@Test
	public void testCreateReceipt() {
		Receipt receipt = new Receipt(new Date(), new User(20), new Supplier(1));
		
		ReceiptDetail detail1 = new ReceiptDetail(10, new Ingredient(2));
		detail1.setReceipt(receipt);
		ReceiptDetail detail2 = new ReceiptDetail(10, new Ingredient(3));
		detail2.setReceipt(receipt);
		
		receipt.getReceiptDetails().add(detail1);
		receipt.getReceiptDetails().add(detail2);
	
		
		Receipt saveReceipt = receiptRepo.save(receipt);
		assertThat(saveReceipt.getId()).isGreaterThan(0);
	}
	
}
