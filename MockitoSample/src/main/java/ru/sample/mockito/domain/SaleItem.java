package ru.sample.mockito.domain;

/*
 * Одна позиция в чеке
 */
class SaleItem {
	private Product product;
	private int amount;
	
	public SaleItem(Product product, int amount) {
		if (product == null) {
			throw new NullPointerException("Product not null");
		}
		
		this.product = product;
		this.amount = amount;
	}
	
	public int getAmount() {
		return amount;
	}
	public Product getProduct() {
		return product;
	}
	public float getCost() {
		return getAmount() * getProduct().getPrice();
	}
}
