package rs.ac.uns.ftn.springsecurityexample.dto;

import java.util.ArrayList;
import java.util.List;

public class MonthIncomeForEachProduct {
	private String month;
	private List<ProductIncome> incomes;

	public MonthIncomeForEachProduct() {
		super();
		incomes = new ArrayList<ProductIncome>();
	}

	public MonthIncomeForEachProduct(String month, List<ProductIncome> incomes) {
		super();
		this.month = month;
		this.incomes = incomes;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public List<ProductIncome> getIncomes() {
		return incomes;
	}

	public void setIncomes(List<ProductIncome> incomes) {
		this.incomes = incomes;
	}

}
