package com.chen.base.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseTest1<M extends BaseTest1<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Integer id) {
		set("id", id);
	}

	public java.lang.Integer getId() {
		return get("id");
	}

	public void setFirstname(java.lang.String firstname) {
		set("firstname", firstname);
	}

	public java.lang.String getFirstname() {
		return get("firstname");
	}

	public void setLastname(java.lang.String lastname) {
		set("lastname", lastname);
	}

	public java.lang.String getLastname() {
		return get("lastname");
	}

	public void setPhone(java.lang.String phone) {
		set("phone", phone);
	}

	public java.lang.String getPhone() {
		return get("phone");
	}

	public void setEmail(java.lang.String email) {
		set("email", email);
	}

	public java.lang.String getEmail() {
		return get("email");
	}

}
