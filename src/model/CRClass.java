package model;
import java.util.Vector;

public class CRClass {

	private String name;
	private Vector<Member> members;
	
	public CRClass() {
		this.members=new Vector<Member>();
	}
	
	public CRClass(String name) {
		this.name=name;
		this.members=new Vector<Member>();
	}
	
	public CRClass(String name, Vector<Member> members) {
		this.name=name;
		this.members=members;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Vector<Member> getMembers() {
		return members;
	}
	
	public boolean addMember(Member member) {
		return members.add(member);
	}
	
	public boolean addMember(Vector<Member> member) {
		return members.addAll(member);
	}
	
	public boolean deleteMember(Member member) {
		return members.remove(member);
	}
	
	public Member deleteMember(int index) {
		return members.remove(index);
	}
	
}
