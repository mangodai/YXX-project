package com.chen.base.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseStlbb<M extends BaseStlbb<M>> extends Model<M> implements IBean {

	public void setID(java.lang.Integer ID) {
		set("ID", ID);
	}

	public java.lang.Integer getID() {
		return get("ID");
	}

	public void setNAME(java.lang.String NAME) {
		set("NAME", NAME);
	}

	public java.lang.String getNAME() {
		return get("NAME");
	}

	public void setNOTE(java.lang.String NOTE) {
		set("NOTE", NOTE);
	}

	public java.lang.String getNOTE() {
		return get("NOTE");
	}

}