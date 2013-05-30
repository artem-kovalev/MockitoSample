package ru.sample.mockito.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;

public class CartItemTest {

	/*
	 * Создание стаба
	 */
	@Test
	public void calculatePriceTest() {
		Product product = mock(Product.class);
		when(product.getPrice()).thenReturn(20F);
		
		CartItem item = new CartItem(product, 10);
		
		float expected = 200F;
		float actual = item.getCost();
		
		assertEquals(expected, actual, 0.00001F);	
	}

	/*
	 * Кидаем исключение
	 */
	@Test(expected = RuntimeException.class)
	public void throwExceptionTest() {
		CartItem cartItem = mock(CartItem.class);
		when(cartItem.getProduct()).thenThrow(new RuntimeException());
		cartItem.getProduct();
	}
	
	

}
