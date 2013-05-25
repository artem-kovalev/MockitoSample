package ru.sample.mockito.domain;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SaleTest {

	
	@Mock
	private List<SaleItem> saleItems;
	
	@Mock
	private Iterator<SaleItem> iterator;
	
	@Mock
	private SaleItem saleItem;
	
	@InjectMocks
	private Sale sale;
	
	@Test
	public void test() {
		when(saleItems.size()).thenReturn(1);
		when(iterator.next()).thenReturn(saleItem);
		when(iterator.hasNext()).thenReturn(true, false);
		when(saleItems.iterator()).thenReturn(iterator);
		sale.getAmountProducts();
	}

}
