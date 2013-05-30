package ru.sample.mockito.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
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
	
	/*
	 * Поверка вызова методов.
	 */
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
		
		verify(product, times(3)).getPrice();
		verify(product, atLeast(3)).getPrice();
		verify(product, atMost(3)).getPrice();
	}
	
	/*
	 * Проверка алгоритма поиска
	 */
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
	
	/*
	 * Слежка за реальным объектом
	 */
	@Test
	public void mockAndSpyTest() {
		Product product = mock(Product.class);
		when(product.getPrice()).thenReturn(2F);
		
		CartItem cartItemMock = mock(CartItem.class);
		CartItem cartItemSpy = spy(new CartItem(product, 1));
		
		assertNull(cartItemMock.getProduct());
		assertNotNull(cartItemSpy.getProduct());
		
		assertEquals(0F, cartItemMock.getCost(), 0.001F);
		assertEquals(2F, cartItemSpy.getCost(), 0.001F);
		
		verify(cartItemSpy).getAmount();
	}
	
	/*
	 * Вызов настоящего метода
	 */
	@Test
	public void callRealMethodTest() {
		Product product = mock(Product.class);
		when(product.getPrice()).thenReturn(2F);
	
		CartItem cartItem = mock(CartItem.class);
		CartItem cartItemRealMethod = mock(CartItem.class);
		when(cartItemRealMethod.getCost()).thenCallRealMethod();
		when(cartItemRealMethod.getAmount()).thenReturn(2);
		when(cartItemRealMethod.getProduct()).thenReturn(product);
		
		assertEquals(0F, cartItem.getCost(), 0.000F);
		assertEquals(4F, cartItemRealMethod.getCost(), 0.000F);
	}
	
	//Комбинируем выводимые резултаты
	@Test
	public void combiningResultsTest() {
		CartItem itemOne = mock(CartItem.class);
		CartItem itemTwo = mock(CartItem.class);
		
		Cart cartMock = mock(Cart.class);
		when(cartMock.findProductByName("first")).thenReturn(itemOne);
		when(cartMock.findProductByName("second")).thenReturn(itemTwo);
		
		assertEquals(itemOne, cartMock.findProductByName("first"));
		assertEquals(itemTwo, cartMock.findProductByName("second"));
		assertNull(cartMock.findProductByName("other"));
	}
	
	/*
	 * матчеры входящих аргументов функции
	 */
	@Test
	public void matchersTest() {
		CartItem itemOne = mock(CartItem.class);
		CartItem itemTwo = mock(CartItem.class);
		CartItem itemOther = mock(CartItem.class);
		
		Cart cartMock = mock(Cart.class);
		when(cartMock.findProductByName(anyString())).thenReturn(itemOther);
		when(cartMock.findProductByName("first")).thenReturn(itemOne);
		when(cartMock.findProductByName("second")).thenReturn(itemTwo);
		
		assertEquals(itemOne, cartMock.findProductByName("first"));
		assertEquals(itemTwo, cartMock.findProductByName("second"));
		assertEquals(itemOther, cartMock.findProductByName("other"));
	}
	
	/*
	 * Кидаем исключения для void метода
	 */
	@Test(expected = RuntimeException.class)
	public void throwExceptionOnVoidMethodTest() {
		Cart cart = mock(Cart.class);
		doThrow(new RuntimeException()).when(cart).addProduct(any(Product.class), anyInt());
		cart.addProduct(null, 0);
	
	}
	
	/*
	 * Создания сложного ответа
	 */
	@Test
	public void createAnswerTest() {
		final CartItem cartItem = mock(CartItem.class);
		Cart cart = mock(Cart.class);
		when(cart.findProductByName(anyString())).thenAnswer(new Answer<CartItem>() {
			@Override
			public CartItem answer(InvocationOnMock invocation) throws Throwable {
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
	
	
}
