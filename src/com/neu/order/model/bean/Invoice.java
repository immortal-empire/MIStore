package com.neu.order.model.bean;

public class Invoice {
	int id;
	int orderId;
	String invoiceTitle;
	String InvoiceTelephone;
	String invoiceEmail;
	String invoiceContext;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	public String getInvoiceTelephone() {
		return InvoiceTelephone;
	}
	public void setInvoiceTelephone(String invoiceTelephone) {
		InvoiceTelephone = invoiceTelephone;
	}
	public String getInvoiceEmail() {
		return invoiceEmail;
	}
	public void setInvoiceEmail(String invoiceEmail) {
		this.invoiceEmail = invoiceEmail;
	}
	public String getInvoiceContext() {
		return invoiceContext;
	}
	public void setInvoiceContext(String invoiceContext) {
		this.invoiceContext = invoiceContext;
	}
}
