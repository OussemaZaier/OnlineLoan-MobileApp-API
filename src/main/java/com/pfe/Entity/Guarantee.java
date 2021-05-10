package com.pfe.Entity;

public class Guarantee {
	private String type;
	private String desc;
	public Guarantee() {}
	public Guarantee(String type, String desc) {
		super();
		this.type=type;
		this.desc=desc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}

}