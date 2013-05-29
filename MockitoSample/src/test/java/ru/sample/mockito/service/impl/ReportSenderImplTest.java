package ru.sample.mockito.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import ru.sample.mockito.domain.Cart;
import ru.sample.mockito.domain.Product;
import ru.sample.mockito.service.EmailSender;
import ru.sample.mockito.service.ReportBuilder;
import ru.sample.mockito.service.ReportSender;

@RunWith(MockitoJUnitRunner.class)
public class ReportSenderImplTest {

	/*
	 * Неверный тест. 
	 * Тест зависит от реализаций других классов и их поведения 
	 */
	@Ignore
	@Test
	public void sendReportTestBad(){
		Product product = new Product();
		product.setName("Сферический продукт в вакууме");
		product.setPrice(10F);
		Cart cart2 = new Cart();
		cart2.addProduct(product, 2);
		
		try {
			ReportBuilder reportBuilder2 = new ReportBuilderImpl();
			EmailSender emailSender2 = new EmailSenderImpl();
			ReportSender reportSender2 = new ReportSenderImpl(reportBuilder2, emailSender2);
			reportSender2.send(cart2);
		} catch (RuntimeException ex) {
			fail();
		}
	}
	
	@Mock
	private ReportBuilder reportBuilder;
	
	@Mock
	private EmailSender emailSender;
	
	@Mock
	private Cart cart;
	
	@InjectMocks
	private ReportSenderImpl reportSender;
	
	@After
	public void clearMock() {
		reset(reportBuilder, emailSender, cart);
	}
	
	@Test
	public void sendReportTestGood() {
		//when(reportBuilder.build(any(Cart.class))).thenReturn(null);
		//when(emailSender.send(anyString())).thenReturn(true);
		
		try {
			reportSender.send(cart);
		} catch (RuntimeException ex) {
			fail();
		}
	}
	
	
	
}
