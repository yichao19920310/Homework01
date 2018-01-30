package com.amazon.jim.entity;

import java.io.Serializable;

public class Cart implements Serializable{
private int id;
private int pid;
private int quantity;
private int userid;
private Products product;
public Cart() {
	super();
}
public Cart(int pid, int quantity, int userid, Products product) {
	super();
	this.pid = pid;
	this.quantity = quantity;
	this.userid = userid;
	this.product = product;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getPid() {
	return pid;
}
public void setPid(int pid) {
	this.pid = pid;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public Products getProduct() {
	return product;
}
public void setProduct(Products product) {
	this.product = product;
}
@Override
public String toString() {
	return "Cart [id=" + id + ", pid=" + pid + ", quantity=" + quantity + ", userid=" + userid + ", product=" + product
			+ "]";
}


}
