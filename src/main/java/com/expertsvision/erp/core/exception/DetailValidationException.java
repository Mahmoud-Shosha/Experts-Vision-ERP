package com.expertsvision.erp.core.exception;

public class DetailValidationException  extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String messageCode;
	private String labelCodeFirst;
	private Object valueFirst;
	private String labelCodeSecond;
	private Object valueSecond;

	public DetailValidationException(String messageCode, String labelCodeFirst, Object valueFirst,
			String labelCodeSecond, Object valueSecond) {
		this.messageCode = messageCode;
		this.labelCodeFirst = labelCodeFirst;
		this.valueFirst = valueFirst;
		this.labelCodeSecond = labelCodeSecond;
		this.valueSecond = valueSecond;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getLabelCodeFirst() {
		return labelCodeFirst;
	}

	public void setLabelCodeFirst(String labelCodeFirst) {
		this.labelCodeFirst = labelCodeFirst;
	}

	public Object getValueFirst() {
		return valueFirst;
	}

	public void setValueFirst(Object valueFirst) {
		this.valueFirst = valueFirst;
	}

	public String getLabelCodeSecond() {
		return labelCodeSecond;
	}

	public void setLabelCodeSecond(String labelCodeSecond) {
		this.labelCodeSecond = labelCodeSecond;
	}

	public Object getValueSecond() {
		return valueSecond;
	}

	public void setValueSecond(Object valueSecond) {
		this.valueSecond = valueSecond;
	}

}