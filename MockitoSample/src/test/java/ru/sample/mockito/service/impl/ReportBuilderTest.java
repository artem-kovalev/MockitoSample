package ru.sample.mockito.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ru.sample.mockito.domain.Cart;
import ru.sample.mockito.domain.CartItem;
import ru.sample.mockito.domain.Product;
/*
 * Инжектирование моков
 */
@RunWith(MockitoJUnitRunner.class)
public class ReportBuilderTest {
	
	private static final String NAME = "product name";
	private static final Float PRICE = 10F;
	
	@Mock
	private Cart cart;
	
	@Mock
	private Product product;
	
	@Mock
	private CartItem item;
	
	@InjectMocks
	private ReportBuilderImpl reportBuilderImpl;
	
	@Before
	public void before() {
		when(product.getName()).thenReturn(NAME);
		when(product.getPrice()).thenReturn(PRICE);
		
		when(item.getProduct()).thenReturn(product);
		
		List<CartItem> items = new ArrayList<CartItem>();
		items.add(item);
		when(cart.getCartItems()).thenReturn(items);
	}
	
	@After
	public void resetMock() {
		reset(cart, product, item);
	}
	
	@Test
	public void buildTest() {
		String result = reportBuilderImpl.build(cart);
		
		assertTrue(result.indexOf(NAME) >= 0);
		assertTrue(result.indexOf(PRICE.toString()) >= 0);
	}
}
