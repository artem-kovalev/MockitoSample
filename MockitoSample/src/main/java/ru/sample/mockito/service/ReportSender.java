package ru.sample.mockito.service;

import ru.sample.mockito.domain.Cart;

public interface ReportSender {
	void send(Cart cart);
}
