package controller;

import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
       // members = this.getMembers();
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
		try {
			em.getTransaction().begin();
			em.persist(codeReview);
			em.getTransaction().commit();
	    } catch (Exception e) {
	    	em.getTransaction().rollback();
	    }
	}
	
	public Vector<Member> getMembersClass(int id){	
		Query q = em.createQuery("SELECT m FROM Member m WHERE m.crclassId = :id");
		q.setParameter("id",id);
		Vector<Member> members = (Vector) q.getResultList();
		return members;
	}

	/* MEMBER */
	public Vector<Member> getMembers() {
		Query q = em.createQuery("SELECT m FROM Member m ");
		members = ((Vector) q.getResultList());
		return members;
	}

	public void setMember(Member member) {		
		try {
			em.getTransaction().begin();
			em.persist(member);
			em.getTransaction().commit();
	    } catch (Exception e) {
	    	em.getTransaction().rollback();
	    }

		for(int i = 0 ; i < classes.size() ; i++) {
			if(member.getCrclassId() == classes.get(i).getId()){
				classes.get(i).setCount(classes.get(i).getCount()+1);
				i=classes.size();
			}
		}
	}
	
	public void deleteMember(int id) {
		
		try {
			em.getTransaction().begin();
			Member member = em.find(Member.class, id);
			em.remove(member);
			em.getTransaction().commit();
	    } catch (Exception e) {
	    	em.getTransaction().rollback();
	    }
		
		for(int j=0; j<members.size();j++) {
			if(members.get(j).getId() == id) {
				for(int i = 0 ; i < classes.size() ; i++) {
					if(members.get(j).getCrclassId() == classes.get(i).getId()){
						classes.get(i).setCount(classes.get(i).getCount()-1);
						i=classes.size();
					}
				}
				j=members.size();
			}
		}
	}
	
	public void updateMember(Member member, int id) {
		try {
			em.getTransaction().begin();
			member.setId(id);
			em.merge(member);
			em.getTransaction().commit();
	    } catch (Exception e) {
	    	em.getTransaction().rollback();
	    }

		setClassCount();
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
		
		setClassCount();
		
		return classes;
	}
	
	public void setClassCount() {
		long count;
		for(int i = 0 ; i < classes.size() ; i++) {
			Query q2 = em.createQuery("SELECT count(m.id) FROM Member m WHERE m.crclassId = :id");
			q2.setParameter("id",classes.get(i).getId());
			count = (long) q2.getSingleResult();
			classes.get(i).setCount(count);;
		}
	}
	
	public void setClasse(CRClass classe) {
		try {
			em.getTransaction().begin();
			em.persist(classe);
			em.getTransaction().commit();
	    } catch (Exception e) {
	    	em.getTransaction().rollback();
	    }
	}
	
	
	public String getClassName(int id) {
		try{
			Query q = em.createQuery("SELECT c.name FROM CRClass c WHERE c.id = :id");
			q.setParameter("id",id);
			String nom = (String) q.getSingleResult();
			return nom;
	    } catch(NoResultException e) {
	        return null;
	    }
	}
	
	public Integer getClasseId(String nom) {		
		try{
			Query q = em.createQuery("SELECT c.id FROM CRClass c WHERE c.name = :nom");
			q.setParameter("nom",nom);
			Integer id = (Integer) q.getSingleResult();
			return id;
	    } catch(NoResultException e) {
	        return 0;
	    }
	}

}