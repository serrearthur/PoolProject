package controller;

import java.util.Vector;

import model.CRClass;
import model.CodeReview;
import model.Member;

public class MainController {
	private Vector<CodeReview> codeReviews=new Vector<CodeReview>();
	private Vector<Member> members=new Vector<Member>();
	private Vector<CRClass> classes=new Vector<CRClass>();

	//implémentation du singleton
	/** Holder */
	private static class SingletonHolder
	{		
		/** Instance unique non préinitialisée */
			private final static MainController instance=new MainController();
	}
 
	/** Point d'accès pour l'instance unique du singleton */
	public static MainController getInstance()
	{
		return SingletonHolder.instance;
	}
	
	private MainController() {
		/**
		 * TODO:
		 * Charger la database dans les attributs du controlleur
		 */
		
		/**
		 * TEMPORAIRE:
		 * liste fixe de valeurs pour démo JSP
		 */

		
		//classes
		classes.add(new CRClass("pool 2017"));
		classes.add(new CRClass("piscine 2017"));
		
		//membres
		members.add(new Member("John Snow", "jsnow@winterfell.ws", "02.07.1352"));
		members.add(new Member("Santa Klaus", "pernoel@polenord.pn", "01.01.0000"));
		members.add(new Member("Donald Trump", "therealdonald@twitter.com", "14.06.1946"));
		
		members.addElement(new Member("Tahiti Bob", "tbob@springfield.com", "08.10.1984", classes.get(0)));
		classes.get(0).addMember(members.get(3));
		members.addElement(new Member("Neo", "neo@ebiz.fr", "25.02.1973", classes.get(1)));
		classes.get(1).addMember(members.get(4));
		
//		members.add(new Member("John Snow", "jsnow@winterfell.ws", "02.07.1352"));
//		members.add(new Member("Santa Klaus", "pernoel@polenord.pn", "01.01.0000"));
//		members.add(new Member("Donald Trump", "therealdonald@twitter.com", "14.06.1946"));
//			
//		members.addElement(new Member("Tahiti Bob", "tbob@springfield.com", "08.10.1984", classes.get(0)));
//		classes.get(0).addMember(members.get(3));
//		members.addElement(new Member("Neo", "neo@ebiz.fr", "25.02.1973", classes.get(1)));
//		classes.get(1).addMember(members.get(4));
		
		//codeReviews
		codeReviews.addElement(new CodeReview("pomme", "ceci est une pomme", "14/09/2017", classes.get(0)));
	}
	
	public Vector<CodeReview> getCodeReviews() {
		return codeReviews;
	}

	public void setCodeReviews(Vector<CodeReview> codeReviews) {
		this.codeReviews = codeReviews;
	}

	public Vector<Member> getMembers() {
		return members;
	}

	public Vector<Member> getMemberSubest(int start, int size) {
		Vector<Member> ret = new Vector<Member>();
		for (int i=start; i<start+size; i++) {
			if (i>=members.size()) break;
			ret.add(members.get(i));
		}
		return ret;
	}
	
	public void setMembers(Vector<Member> members) {
		this.members = members;
	}

	public Vector<CRClass> getClasses() {
		return classes;
	}

	public void setClasses(Vector<CRClass> classes) {
		this.classes = classes;
	}
}