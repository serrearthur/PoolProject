package controller;

import java.text.ParseException;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.CRClass;
import model.CodeReview;
import model.Member;

public class MainController {
	// Une liste par table de la base de données
	private Vector<CodeReview> codeReviews=new Vector<CodeReview>();
	private Vector<Member> members=new Vector<Member>();
	private Vector<CRClass> classes=new Vector<CRClass>();

	// Pour établir la connexion avec la BDD
	private static final String PERSISTENCE_UNIT_NAME = "Test";
    private static EntityManagerFactory factory;
    private static EntityManager em;
    
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
	
	public MainController() {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        em = factory.createEntityManager();
        
		// Remplit les listes avec les infos de la BDD
        members = this.getMembers();
		codeReviews = this.getCodeReviews();
		classes = this.getClasses();

        //em.close();
	}
	
	// fonction GetList, SetItem de la liste, deleteItem en fonction de l'id 
	// et updateItem en passant l'item ayant subit une ou plusieurs modification au préalable
	
	/* CODEREVIEW */
	public Vector<CodeReview> getCodeReviews() {
		Query q = em.createQuery("SELECT cr FROM CodeReview cr ");
		codeReviews = ((Vector) q.getResultList());
		return codeReviews;
	}

	public void setCodeReview(CodeReview codeReview) {
		em.getTransaction().begin();
		em.persist(codeReview);
		em.getTransaction().commit();
	}
	
	public int deleteCodeReview(int id) {
		em.getTransaction().begin();
		Query q = em.createQuery("DELETE FROM CodeReview WHERE id = :id");
		q.setParameter("id", id);
		int result = q.executeUpdate();
		em.getTransaction().commit();
		return result;
	}
	
	public int updateCodeReview(CodeReview codeReview) {
		em.getTransaction().begin();
		Query q = em.createQuery(" UPDATE CodeReview SET name= :name"
				+ ", description= :description"
				+ ", dateTime= :dateTime"
				+ " WHERE id = :id ");
		
		q.setParameter("name", codeReview.getName());
		q.setParameter("desciption", codeReview.getDescription());
		q.setParameter("dateTime", codeReview.getDateTime());
		q.setParameter("id", codeReview.getId());
		int result = q.executeUpdate();
		em.getTransaction().commit();
		return result;
	}

	/* MEMBER */
	public Vector<Member> getMembers() {
		Query q = em.createQuery("SELECT m FROM Member m ");
		members = ((Vector) q.getResultList());
		return members;
	}

	public void setMember(Member member) {
		em.getTransaction().begin();
		em.persist(member);
		em.getTransaction().commit();
	}
	
	public int deleteMember(int id) {
		em.getTransaction().begin();
		Query q = em.createQuery("DELETE FROM Member WHERE id = :id");
		q.setParameter("id", id);
		int result = q.executeUpdate();
		em.getTransaction().commit();
		return result;
	}
	
	public int updateMember(Member member) {
		em.getTransaction().begin();
		Query q = em.createQuery(" UPDATE Member SET name= :name"
				+ ", email= :email"
				+ ", birthdate= :birthdate"
				+ ", crclassId= :crclass_id "
				+ " WHERE id = :id ");
		
		q.setParameter("name", member.getName());
		q.setParameter("email", member.getEmail());
		q.setParameter("birthdate", member.getBirthdate());
		q.setParameter("crclass_id", member.getCrclassId());
		q.setParameter("id", member.getId());
		int result = q.executeUpdate();
		em.getTransaction().commit();
		return result;
	}
	
	public Vector<Member> getMemberSubest(int start, int size) {
		Vector<Member> ret = new Vector<Member>();
		for (int i=start; i<start+size; i++) {
			if (i>=members.size()) break;
			ret.add(members.get(i));
		}
		return ret;
}

	/* CLASS */
	public Vector<CRClass> getClasses() {
		Query q = em.createQuery("SELECT crc FROM CRClass crc ");
		classes = ((Vector) q.getResultList());
		return classes;
	}

	public void setClasse(CRClass classe) {
		em.getTransaction().begin();
		em.persist(classe);
		em.getTransaction().commit();
	}
	
	public int deleteClasse(int id) {
		em.getTransaction().begin();
		Query q = em.createQuery("DELETE FROM CRClass WHERE id = :id");
		q.setParameter("id", id);
		int result = q.executeUpdate();
		em.getTransaction().commit();
		return result;
	}
	
	public int updateClasse(CRClass classe) {
		em.getTransaction().begin();
		Query q = em.createQuery(" UPDATE CRClass SET name= :name"
				+ " WHERE id = :id ");
		
		q.setParameter("name", classe.getName());
		q.setParameter("id", classe.getId());
		int result = q.executeUpdate();
		em.getTransaction().commit();
		return result;
	}

}