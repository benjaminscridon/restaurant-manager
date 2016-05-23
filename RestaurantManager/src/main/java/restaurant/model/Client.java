package restaurant.model;

public class Client {
	private int client_no;
	private String name;
	private String mobile;
	private String email;
	private String loyality;

	public Client(int client_no, String name, String mobile, String email, String loyality) {
		this.client_no = client_no;
		this.name = name;
		this.mobile = mobile;
		this.email = email;
		this.loyality = loyality;
	}

	public int getClient_no() {
		return client_no;
	}

	public void setClient_no(int client_no) {
		this.client_no = client_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoyality() {
		return loyality;
	}

	public void setLoyality(String loyality) {
		this.loyality = loyality;
	}

	@Override
	public String toString() {
		return "Client [client_no=" + client_no + ", name=" + name + ", mobile=" + mobile + ", email=" + email
				+ ", loyality=" + loyality + "]";
	}

}
