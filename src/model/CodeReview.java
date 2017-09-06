package model;

public class CodeReview {
	
	private String name;
	private String description;
	private String datetime;
	private CRClass crclass;
	
	public CodeReview() {
	}
	
	public CodeReview(String name, String description, String datetime, CRClass crclass ) {
		this.name=name;
		this.description=description;
		this.datetime=datetime;
		this.crclass=crclass;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public CRClass getCrclass() {
		return crclass;
	}
	public void setCrclass(CRClass crclass) {
		this.crclass = crclass;
	}
}