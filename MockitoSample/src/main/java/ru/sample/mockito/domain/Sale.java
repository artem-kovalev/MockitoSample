package ru.sample.mockito.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
 * Чек покупок в магазине
 */
public class Sale {
	private int id;
	private List<SaleItem> saleItems;
	
	public Sale() {
		saleItems = new ArrayList<SaleItem>();
	}
	
	public int getId() {
		return id;
	}
	
	public void addProduct(Product product, int amount) {
		saleItems.add(new SaleItem(product, amount));
	}
	
	public int getAmountProducts() {
		int count = 0;
		for(SaleItem saleItem : saleItems) {
			count += saleItem.getAmount();
		}
		return count;
	}
	
	public Collection<SaleItem> getSaleItems() {
		return Collections.unmodifiableCollection(saleItems);
	}
	
	public float getCost() {
		float cost = 0F;
		for(SaleItem saleItem : saleItems) {
			cost += saleItem.getCost();
		}
		return cost;
	}
}
