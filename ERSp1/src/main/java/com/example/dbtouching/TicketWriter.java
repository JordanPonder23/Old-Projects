package com.example.dbtouching;

public class TicketWriter {
	protected String ticketID;
	protected String amount;
	protected String dateSubmitted;
	protected String expenseDescription;
	protected String submitterName;
	protected String submitterID;
	protected String resolverID;
	protected String status;
	protected String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResolverID() {
		return resolverID;
	}

	public void setResolverID(String resolverID) {
		this.resolverID = resolverID;
	}

	public String getSubmitterID() {
		return submitterID;
	}

	public void setSubmitterID(String submitterID) {
		this.submitterID = submitterID;
	}

	public String getTicketID() {
		return ticketID;
	}

	public void setTicketID(String ticketID) {
		this.ticketID = ticketID;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(String dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public String getExpenseDescription() {
		return expenseDescription;
	}

	public void setExpenseDescription(String expenseDescription) {
		this.expenseDescription = expenseDescription;
	}

	public String getSubmitterName() {
		return submitterName;
	}

	public void setSubmitterName(String submitterName) {
		this.submitterName = submitterName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
