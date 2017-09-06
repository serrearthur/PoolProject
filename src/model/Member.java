package model;

public class Member {
	private String name;
	private String email;
	private String birthdate;
	private CRClass crclass; 
	
	public Member() {
	}
	
	public Member(String name, String email, String birthdate) {
		this.name=name;
		this.email=email;
		this.birthdate=birthdate;
	}
	
	public Member(String name, String email, String birthdate, CRClass crclass) {
		this.name=name;
		this.email=email;
		this.birthdate=birthdate;
		this.crclass=crclass;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public CRClass getCrclass() {
		return crclass;
	}

	public void setCrclass(CRClass crclass) {
		this.crclass = crclass;
	}
	
}
