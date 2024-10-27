package rs.ac.uns.ftn.springsecurityexample.dto;

public class MonthIncomeDto {
	private String month;
	private int income;

	public MonthIncomeDto() {
		super();
	}

	public MonthIncomeDto(String month, int income) {
		super();
		this.month = month;
		this.income = income;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

}
