package com.amazon.jim.entity;

import java.io.Serializable;

public class OrderDetails implements Serializable{
	int hod_id;
	int ho_id;
	int hp_id;
	int hod_quantity;
	int hod_cost;
	public OrderDetails() {
		super();
	}
	public OrderDetails(int ho_id, int hp_id, int hod_quantity, int hod_cost) {
		super();
		this.ho_id = ho_id;
		this.hp_id = hp_id;
		this.hod_quantity = hod_quantity;
		this.hod_cost = hod_cost;
	}
	public int getHod_id() {
		return hod_id;
	}
	public void setHod_id(int hod_id) {
		this.hod_id = hod_id;
	}
	public int getHo_id() {
		return ho_id;
	}
	public void setHo_id(int ho_id) {
		this.ho_id = ho_id;
	}
	public int getHp_id() {
		return hp_id;
	}
	public void setHp_id(int hp_id) {
		this.hp_id = hp_id;
	}
	public int getHod_quantity() {
		return hod_quantity;
	}
	public void setHod_quantity(int hod_quantity) {
		this.hod_quantity = hod_quantity;
	}
	public int getHod_cost() {
		return hod_cost;
	}
	public void setHod_cost(int hod_cost) {
		this.hod_cost = hod_cost;
	}
	@Override
	public String toString() {
		return "OrderDetails [hod_id=" + hod_id + ", ho_id=" + ho_id + ", hp_id=" + hp_id + ", hod_quantity="
				+ hod_quantity + ", hod_cost=" + hod_cost + "]";
	}
	
}
