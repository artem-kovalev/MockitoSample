package ru.sample.mockito.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Iterator;
import java.util.List;

import javax.jws.Oneway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

public class CartTest {
	
	@Test
	public void calculateAmountTest() {
		Product product = mock(Product.class);
		
		Cart cart = new Cart();
		
		cart.addProduct(product, 10);
		cart.addProduct(product, 25);
		
		int expected = 35;
		int actual = cart.getAmountProducts();
		
		assertEquals(expected, actual);		
	}
	
	@Test
	public void costTest() {
		Product product = mock(Product.class);
		when(product.getPrice()).thenReturn(10F, 20F, 30F);
		
		Cart cart = new Cart();
		cart.addProduct(product, 1);
		cart.addProduct(product, 1);
		cart.addProduct(product, 1);	
		
		
		float expected = 60F;
		float actual = cart.getCost();
		
		assertEquals(expected, actual, 0.0001F);
		
		/*
		 * atLeast(int) - минимальное количество вызовов
		 * atLeastOnce() - вызван 1 раз
		 * times(int) - вызвано ровное количество раз
		 * atMost(int) - не более
		 * never() - не разу не было вызвана
		*/
		verify(product, times(3)).getPrice();
	}
	
	@Test
	public void findProducByNameTest() {
		final String productName = "product";
		Product product = mock(Product.class);
		when(product.getName()).thenReturn(productName);
		
		Cart cart = new Cart();
		cart.addProduct(product, 1);
		
		CartItem cartItem = cart.findProductByName(productName);
		assertNotNull(cartItem);
		assertEquals(product, cartItem.getProduct());
		
	}

}
