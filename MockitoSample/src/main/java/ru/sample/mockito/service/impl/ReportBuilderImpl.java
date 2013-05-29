package ru.sample.mockito.service.impl;

import ru.sample.mockito.domain.Cart;
import ru.sample.mockito.domain.CartItem;
import ru.sample.mockito.service.ReportBuilder;

public class ReportBuilderImpl implements ReportBuilder {

	
	@Override
	public String build(Cart cart) {
		final StringBuilder html = new StringBuilder("<div>Чек покупки</div><br/>");
		html.append("<table><tr><th>Наименование товара</th><th>Количество</th><th>Цена за единицу</th><th>Цена</th></tr>");
		for(CartItem item: cart.getCartItems()) {
			html.append("<tr><td>")
			.append(item.getProduct().getName())
			.append("</td><td>").append(item.getAmount())
			.append("</td><td>").append(item.getProduct().getPrice())
			.append("</td><td>").append(item.getCost()).append("</tr>");
		}
		html.append("</table>");
		return html.toString();
	}

}
