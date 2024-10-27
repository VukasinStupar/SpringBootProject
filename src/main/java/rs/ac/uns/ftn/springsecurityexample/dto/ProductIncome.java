package rs.ac.uns.ftn.springsecurityexample.dto;

public class ProductIncome {
	private String name;
	private long income;

	public ProductIncome(String name, long income) {
		super();
		this.name = name;
		this.income = income;
	}

	public ProductIncome() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getIncome() {
		return income;
	}

	public void setIncome(long income) {
		this.income = income;
	}
}
