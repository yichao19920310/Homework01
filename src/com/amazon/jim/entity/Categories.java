package com.amazon.jim.entity;

import java.io.Serializable;

public class Categories implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = -3529138444024979860L;
private int hpc_id;
private String hpc_name;
private int hpc_parent_id;
public Categories() {
	super();
}
public Categories(int hpc_id, String hpc_name, int hpc_parent_id) {
	super();
	this.hpc_id = hpc_id;
	this.hpc_name = hpc_name;
	this.hpc_parent_id = hpc_parent_id;
}
public int getHpc_id() {
	return hpc_id;
}
public void setHpc_id(int hpc_id) {
	this.hpc_id = hpc_id;
}
public String getHpc_name() {
	return hpc_name;
}
public void setHpc_name(String hpc_name) {
	this.hpc_name = hpc_name;
}
public int getHpc_parent_id() {
	return hpc_parent_id;
}
public void setHpc_parent_id(int hpc_parent_id) {
	this.hpc_parent_id = hpc_parent_id;
}
@Override
public String toString() {
	return "Categories [hpc_id=" + hpc_id + ", hpc_name=" + hpc_name + ", hpc_parent_id=" + hpc_parent_id + "]";
}


}
