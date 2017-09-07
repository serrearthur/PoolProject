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
	@Column
	private String dateTime;
	@Column
	private String description;
	@Column
	private String name;
	@Column(name="crclass_id")
	private int crclassId;

	public CodeReview() {
	}
	
	public CodeReview(String name, String description, String datetime, int crclassId) {
		this.name=name;
		this.description=description;
		this.dateTime=datetime;
		this.crclassId = crclassId;
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
	
	public int getCrclassId() {
		return this.crclassId;
	}

	public void setCrclassId(int crclassId) {
		this.crclassId = crclassId;
	}
}