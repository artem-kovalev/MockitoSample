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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

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
	
	// 
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
		 * atLeast(int) - минимум количество вызовов
		 * atLeastOnce() - вызван 1 раз
		 * times(int) - вызвано ровное количество раз
		 * atMost(int) - не более
		 * never() - не разу не было вызвана
		*/
		verify(product, times(3)).getPrice();
		verify(product, atLeast(3)).getPrice();
		verify(product, atMost(3)).getPrice();
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
	
	@Test
	public void mockAndSpyTest() {
		Product product = mock(Product.class);
		CartItem cartItemMock = mock(CartItem.class);
		CartItem cartItemSpy = spy(new CartItem(product, 1));
		
		assertNull(cartItemMock.getProduct());
		assertNotNull(cartItemSpy.getProduct());
	}
	
	@Test
	public void anyTest() {
		final CartItem cartItem = mock(CartItem.class);
		CartItem cartItem2 = mock(CartItem.class);
		Cart cart = mock(Cart.class);
		
		//-- обобщеный
		when(cart.findProductByName(anyString())).thenReturn(cartItem);
		assertEquals(cartItem, cart.findProductByName("Тут любая строка"));
		verify(cart, atLeastOnce()).findProductByName(anyString());
		
		//--- проверка регуляркой
		when(cart.findProductByName(matches("\\d\\d\\d"))).thenReturn(cartItem2);
		
		assertEquals(cartItem2, cart.findProductByName("123"));
		verify(cart, atLeastOnce()).findProductByName(matches("\\d\\d\\d"));
		//----

		//void методы
		doNothing().when(cart).addProduct(any(Product.class), anyInt());
		cart.addProduct(null, 1);
		verify(cart, atLeastOnce()).addProduct(any(Product.class), anyInt());
		
		//-- создаём спечиальный ответ
		when(cart.findProductByName(anyString())).thenAnswer(new Answer<CartItem>() {

			@Override
			public CartItem answer(InvocationOnMock invocation)
					throws Throwable {
				String arg = (String)invocation.getArguments()[0];
				Product prod = mock(Product.class);
				when(prod.getName()).thenReturn(arg);
				when(cartItem.getProduct()).thenReturn(prod);
				return cartItem;
			}
		});
		final String productName = "product";
		Product prod = cart.findProductByName(productName).getProduct();
		assertEquals(productName, prod.getName());
	}

	
	@Test(expected = RuntimeException.class)
	public void throwExceptionTest() {
		CartItem cartItem = mock(CartItem.class);
		when(cartItem.getProduct()).thenThrow(new RuntimeException());
		cartItem.getProduct();
	}
	
	@Test(expected = RuntimeException.class)
	public void throwExceptionTest2() {
		Cart cart = mock(Cart.class);
		doThrow(new RuntimeException()).when(cart).addProduct(any(Product.class), anyInt());
		cart.addProduct(null, 0);
	}
	
}
