package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.AccessType.PROPERTY;


/**
 * The persistent class for the class database table.
 * 
 */
@Entity
@Table(name="class")
@NamedQuery(name="CRClass.findAll", query="SELECT c FROM CRClass c")
public class CRClass implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	@Column
	private String name;
	@Column
	private long count;	

	public CRClass() {
	}
	
	public CRClass(String name) {
		this.name=name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public long getCount() {
		return count;
	}
	
	public void setCount(long count) {
		this.count = count;
	}
}