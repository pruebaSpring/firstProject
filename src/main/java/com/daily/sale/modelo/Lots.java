package com.daily.sale.modelo;

public class Lots {
	private Double amount;
	private String measure;
	private String location;
	
	public Lots() {
		
	}
	
	public Lots(Double amount, String measure, String location) {
		super();
		this.amount = amount;
		this.measure = measure;
		this.location = location;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
}
