package rs.ac.uns.ftn.springsecurityexample.dto;

public class CompanySearchDto {
	private String name;
	private String adress;
	private Float gradeFrom;
	private Float gradeTo;

	public CompanySearchDto() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public Float getGradeFrom() {
		return gradeFrom;
	}

	public void setGradeFrom(Float gradeFrom) {
		this.gradeFrom = gradeFrom;
	}

	public Float getGradeTo() {
		return gradeTo;
	}

	public void setGradeTo(Float gradeTo) {
		this.gradeTo = gradeTo;
	}

}
