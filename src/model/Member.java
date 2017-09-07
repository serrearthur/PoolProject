package model;

import java.io.Serializable;
import javax.persistence.*;

import model.CRClass;

import java.util.Date;


/**
 * The persistent class for the member database table.
 * 
 */
@Entity
@Table(name="member")
@NamedQuery(name="Member.findAll", query="SELECT m FROM Member m")
public class Member implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	@Column(name="birthdate")
	private String birthdate;
	@Column(name="crclass_id")
	private Integer crclassId;
	@Column(name="email")
	private String email;
	@Column (name="name")
	private String name;

	public Member() {
	}
	
	public Member(String name, String email, String birthdate, Integer classeId) {
		this.name=name;
		this.email=email;
		this.birthdate=birthdate;
		this.crclassId=classeId;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBirthdate() {
		return this.birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public Integer getCrclassId() {
		return this.crclassId;
	}

	public void setCrclassId(Integer crclassId) {
		this.crclassId = crclassId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}