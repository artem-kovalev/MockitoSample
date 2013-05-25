package ru.sample.mockito.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;

public class SaleItemTest {

	@Test
	public void calculatePriceTest() {
		Product product = mock(Product.class);
		when(product.getPrice()).thenReturn(20F);
		
		SaleItem item = new SaleItem(product, 10);
		
		float expected = 200F;
		float actual = item.getCost();
		
		assertEquals(expected, actual, 0.00001F);	
	}

}
