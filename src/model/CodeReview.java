package model;

import java.io.Serializable;
import javax.persistence.*;

import model.CRClass;

import java.sql.Timestamp;


/**
 * The persistent class for the codeReview database table.
 * 
 */
@Entity
@Table(name="codeReview")
@NamedQuery(name="CodeReview.findAll", query="SELECT c FROM CodeReview c")
public class CodeReview implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String dateTime;

	private String description;

	private String name;
	@PrimaryKeyJoinColumn
	private CRClass crclass;

	public CodeReview() {
	}
	
	public CodeReview(String name, String description, String datetime, CRClass crclass ) {
		this.name=name;
		this.description=description;
		this.dateTime=datetime;
		this.crclass=crclass;
}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public CRClass getCrclass() {
		return this.crclass;
	}

	public void setCrclass(CRClass crclass) {
		this.crclass = crclass;
	}

}