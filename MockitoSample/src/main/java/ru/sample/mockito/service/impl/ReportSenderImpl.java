package ru.sample.mockito.service.impl;

import ru.sample.mockito.domain.Cart;
import ru.sample.mockito.service.EmailSender;
import ru.sample.mockito.service.ReportBuilder;
import ru.sample.mockito.service.ReportSender;

public class ReportSenderImpl implements ReportSender {

	private EmailSender emailSender;
	private ReportBuilder reportBuilder;

	public ReportSenderImpl(ReportBuilder reportBuilder, EmailSender emailSender) {
		this.reportBuilder = reportBuilder;
		this.emailSender = emailSender;
	}
	

	
	@Override
	public void send(Cart cart) {
		
	}

}
