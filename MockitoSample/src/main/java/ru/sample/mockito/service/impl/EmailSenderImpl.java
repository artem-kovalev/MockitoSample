package ru.sample.mockito.service.impl;

import ru.sample.mockito.service.EmailSender;

public class EmailSenderImpl implements EmailSender {

	@Override
	public boolean send(String message) {
		//Иммитация работы
		try {
			Thread.sleep(2000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return true;
	}

}
