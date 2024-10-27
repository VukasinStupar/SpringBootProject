package rs.ac.uns.ftn.springsecurityexample.dto;

import rs.ac.uns.ftn.springsecurityexample.model.Grade;

public class GradeDto {

	private Long id;

	private int gradeValue;

	private String reason;

	private Long companyId;

	private Long guestId;
	
	private CompanyDto companyDto;

	public GradeDto() {
		super();
	}

	public GradeDto(Grade grade) {
		id = grade.getId();
		gradeValue = grade.getGradeValue();
		reason = grade.getReason();
		companyId = grade.getCompany().getId();
		guestId = grade.getGuest().getId();
		companyDto = new CompanyDto(grade.getCompany());

	}

	public Grade mapToGrade() {
		Grade g = new Grade();
		g.setGradeValue(gradeValue);
		g.setReason(reason);
		

		return g;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getGradeValue() {
		return gradeValue;
	}

	public void setGradeValue(int gradeValue) {
		this.gradeValue = gradeValue;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

	public CompanyDto getCompanyDto() {
		return companyDto;
	}

	public void setCompanyDto(CompanyDto companyDto) {
		this.companyDto = companyDto;
	}

	
	
	

}
