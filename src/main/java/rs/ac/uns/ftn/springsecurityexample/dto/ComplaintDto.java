package rs.ac.uns.ftn.springsecurityexample.dto;

import rs.ac.uns.ftn.springsecurityexample.model.Complaint;

public class ComplaintDto {
	private Long id;
	// company Id or companytAdminId
	private Long companyId;
	private String companyName;
	private Long companyAdminId;
	private String companyAdminFullName;
	private String text;
	private String response;
	private String guestFullName;

	public ComplaintDto(Complaint complaint) {
		id = complaint.getId();
		text = complaint.getText();
		response = complaint.getResponse();
		if (complaint.getCompany() != null) {
			companyId = complaint.getCompany().getId();
			companyName = complaint.getCompany().getName();
		}
		if (complaint.getCompanyAdmin() != null) {
			companyAdminId = complaint.getCompanyAdmin().getId();
			companyAdminFullName = complaint.getCompanyAdmin().getFirstName() + " "
					+ complaint.getCompanyAdmin().getLastName();
		}
		guestFullName = complaint.getGuest().getFirstName() + " " + complaint.getGuest().getLastName();

	}

	public Complaint mapToComplaint() {
		Complaint complaint = new Complaint();
		complaint.setText(text);
		complaint.setResponse(response);

		return complaint;

	}

	public ComplaintDto() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Long getCompanyAdminId() {
		return companyAdminId;
	}

	public void setCompanyAdminId(Long companyAdminId) {
		this.companyAdminId = companyAdminId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getGuestFullName() {
		return guestFullName;
	}

	public void setGuestFullName(String guestFullName) {
		this.guestFullName = guestFullName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyAdminFullName() {
		return companyAdminFullName;
	}

	public void setCompanyAdminFullName(String companyAdminFullName) {
		this.companyAdminFullName = companyAdminFullName;
	}

}
