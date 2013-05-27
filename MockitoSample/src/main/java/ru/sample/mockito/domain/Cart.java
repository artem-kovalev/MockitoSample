package ru.sample.mockito.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
 * Чек покупок в магазине
 */
public class Cart {
	private int id;
	private List<CartItem> cartItems;
	
	public Cart() {
		cartItems = new ArrayList<CartItem>();
	}
	
	public int getId() {
		return id;
	}
	
	public void addProduct(Product product, int amount) {
		cartItems.add(new CartItem(product, amount));
	}
	
	public int getAmountProducts() {
		int count = 0;
		for(CartItem cartItem : cartItems) {
			count += cartItem.getAmount();
		}
		return count;
	}
	
	public Collection<CartItem> getSaleItems() {
		return Collections.unmodifiableCollection(cartItems);
	}
	
	public float getCost() {
		float cost = 0F;
		for(CartItem cartItem : cartItems) {
			cost += cartItem.getCost();
		}
		return cost;
	}
	
	public CartItem findProductByName(String name) {
		for(CartItem cartItem : cartItems) {
			if (cartItem.getProduct().getName() == name) {
				return cartItem;
			}
		}
		return null;
	}
}
