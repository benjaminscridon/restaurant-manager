package restaurant.model;

import java.util.Date;

public class Employee {

	private int employee_no;
	private String password;
	private String job_title;
	private String name;
	private Date birthdate;
	private String address;
	private String email;
	private String mobile;
	private Date hire_date;
	private Date fire_date;

	public int getEmployee_no() {
		return employee_no;
	}

	public void setEmployee_no(int employee_no) {
		this.employee_no = employee_no;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getHire_date() {
		return hire_date;
	}

	public void setHire_date(Date hire_date) {
		this.hire_date = hire_date;
	}

	public Date getFire_date() {
		return fire_date;
	}

	public void setFire_date(Date fire_date) {
		this.fire_date = fire_date;
	}

	@Override
	public String toString() {
		return "Employee [employee_no=" + employee_no + ", password=" + password + ", job_title=" + job_title
				+ ", name=" + name + ", birthdate=" + birthdate + ", address=" + address + ", email=" + email
				+ ", mobile=" + mobile + ", hire_date=" + hire_date + ", fire_date=" + fire_date + "]";
	}

}
