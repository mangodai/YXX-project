package com.chen.base.model;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseStcwb<M extends BaseStcwb<M>> extends Model<M> implements IBean {

	public void setID(java.lang.Integer ID) {
		set("ID", ID);
	}

	public java.lang.Integer getID() {
		return get("ID");
	}

	public void setTMID(java.lang.Integer TMID) {
		set("TMID", TMID);
	}

	public java.lang.Integer getTMID() {
		return get("TMID");
	}

	public void setTOTAL(java.lang.Integer TOTAL) {
		set("TOTAL", TOTAL);
	}

	public java.lang.Integer getTOTAL() {
		return get("TOTAL");
	}

}
