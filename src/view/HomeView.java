package view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.MainController;
import model.CRClass;
import model.CodeReview;
import model.Member;

public class HomeView extends HttpServlet{
	//Obligatoire pour la définition d'un servlet
	private static final long serialVersionUID = 1L;
	
	public static final String View="/WEB-INF/home.jsp";
	public static final int memberPageSize=7;
	public static final String FIELD_NAME_MEMBER   = "memberName";
	public static final String FIELD_EMAIL_MEMBER   = "memberEmail";
	public static final String FIELD_PROMO_MEMBER   = "memberPromotion";
	public static final String FIELD_ID_MEMBER  = "memberId";
	public static final String FIELD_ID_CR  = "crId";
	public static final String FIELD_NAME_CR  = "crName";
	public static final String FIELD_DESC_CR  = "crDesc";
	public static final String FIELD_PROMO_CR  = "crPromotion";
	public static final String FIELD_DATE_CR  = "crDate";
	public static final String FIELD_BIRTH_MEMBER = "memberBirthdate";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		MainController mainController=MainController.getInstance(); //recuperation du controlleur
		int memberPage=1;	//page des membres a afficher
		int memberTotalPages=1+mainController.getMembers().size()/memberPageSize;
		
		//gestion des parametres recus dans la requete
		if(request.getParameter("memberPage") != null)
            memberPage = Integer.parseInt(request.getParameter("memberPage"));
		
		//transmission des parametres a la jsp
		request.setAttribute("controller", mainController);
		request.setAttribute("currentMembers", mainController.getMemberSubest((memberPage-1)*memberPageSize, memberPageSize));
		request.setAttribute("memberTotalPages", memberTotalPages);
		request.setAttribute("memberPage", memberPage);
		
		this.getServletContext().getRequestDispatcher(View).forward( request, response );
    }
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		MainController mainController=MainController.getInstance();
		String bouton="";
		Object test;
		int id=-1;
		
		Enumeration<String> names = request.getParameterNames();
		
		for(;names.hasMoreElements();) {
			test = names.nextElement();
			if(test.equals("supprimerMember")) {
				bouton="supprimerMember";
				id = Integer.parseInt(request.getParameter(FIELD_ID_MEMBER));
				break;
			}
			else if(test.equals("modifierMember")){
				bouton="modifierMember";
				id = Integer.parseInt(request.getParameter(FIELD_ID_MEMBER));
				break;
			}
			else if(test.equals("modifierCR")){
				bouton="modifierCR";
				id = Integer.parseInt(request.getParameter(FIELD_ID_CR));
				break;
			}
			else if(test.equals("supprimerCR")){
				bouton="supprimerCR";
				id = Integer.parseInt(request.getParameter(FIELD_ID_CR));
				break;
			}
		}
		


		if(bouton=="modifierMember") {
			String name=request.getParameter(FIELD_NAME_MEMBER);
			String email=request.getParameter(FIELD_EMAIL_MEMBER);
			String promo=request.getParameter(FIELD_PROMO_MEMBER);
			String birth=request.getParameter(FIELD_BIRTH_MEMBER);
			
			
			String result;
	        Vector<String> errors = new Vector<String>();
			
			try {
	            validationName(name);
	        } catch ( Exception e ) {
	            errors.add(e.getMessage() );
	        }
			try {
	            validationEmail(email);
	        } catch ( Exception e ) {
	            errors.add(e.getMessage() );
	        }
			try {
	            validationPromo(promo);
	        } catch ( Exception e ) {
	            errors.add(e.getMessage() );
	        }
			
	        if ( errors.isEmpty() ) {
	        	CRClass crclass=null;
	        	for (CRClass c : mainController.getClasses()) {
	        		if (c.getName().equals(promo)) {
	        			crclass=c;
	        			break;
	        		}
	        	}
	        	
	        	//mainController.getMembers().add(new Member(name, email, birthdate));
	        	Integer classeId = mainController.getClasseId(promo);
	        	mainController.updateMember(new Member(name, email,birth,classeId),id);
	        	result = "Utilisateur \""+name+"\" crée avec succès.";
	        } else {
	            result = "Erreur lors de la création :";
	        }

			//request.setAttribute("controller", mainController);
			request.setAttribute("errors", errors );
	        request.setAttribute("result", result);
			this.doGet(request, response);
		}
		else if (bouton=="supprimerMember") {
			mainController.deleteMember(id);
			this.doGet(request, response);
		}
		else if (bouton=="supprimerCR") {
			mainController.deleteCR(id);
			this.doGet(request, response);
		}
		else if(bouton=="modifierCR") {
			
		String name=request.getParameter(FIELD_NAME_CR);
		String desc=request.getParameter(FIELD_DESC_CR);
		String promo=request.getParameter(FIELD_PROMO_CR);
		String date=request.getParameter(FIELD_DATE_CR);
		
		
		String result;
        Vector<String> errors = new Vector<String>();
		
		try {
            validationName(name);
        } catch ( Exception e ) {
            errors.add(e.getMessage() );
        }
		try {
            validationDate(date);
        } catch ( Exception e ) {
            errors.add(e.getMessage() );
        }
		try {
            validationPromo(promo);
        } catch ( Exception e ) {
            errors.add(e.getMessage() );
        }
		
        if ( errors.isEmpty() ) {
        	CRClass crclass=null;
        	for (CRClass c : mainController.getClasses()) {
        		if (c.getName().equals(promo)) {
        			crclass=c;
        			break;
        		}
        	}
        	
        	//mainController.getMembers().add(new Member(name, email, birthdate));
        	Integer classeId = mainController.getClasseId(promo);
        	mainController.updateCodeReview(new CodeReview(name, desc,date,classeId),id);
        	result = "Utilisateur \""+name+"\" crée avec succès.";
        } else {
            result = "Erreur lors de la création :";
        }

//		request.setAttribute("controller", mainController);
		request.setAttribute("errors", errors );
        request.setAttribute("result", result);
		this.doGet(request, response);
		}
	}
	
	private void validationName(String name) throws Exception {
		if (name == null || name.trim().length() == 0)
    		throw new Exception("Le nom du nouveau membre ne peut pas être vide");
	}
	
	private void validationEmail(String email) throws Exception {
		if ( email != null && email.trim().length() != 0 ) {
	        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new Exception( "L'addresse email n'est pas valide." );
	        }
	    } else {
	        throw new Exception( "L'addresse email ne peut pas être vide" );
	    }
	}
	
	private void validationDate(String date) throws Exception {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/mm/yyyy");
		if ( date != null && date.trim().length() != 0 ) {
	        try {
	        	fmt.parse(date);
	        } catch (ParseException e) {
	        	throw new Exception( "Le format de la date n'est pas valide." );
	        }
	    } else {
	        throw new Exception( "La date ne peut pas être vide" );
	    }
	}
	
	private void validationBirthdate(String birthdate) throws Exception {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/mm/yyyy");
		if ( birthdate != null && birthdate.trim().length() != 0 ) {
	        try {
	        	fmt.parse(birthdate);
	        } catch (ParseException e) {
	        	throw new Exception( "Le format de la date de naissance n'est pas valide." );
	        }
	    } else {
	        throw new Exception( "La date de naissance ne peut pas être vide" );
	    }
	}
	
	private void validationPromo(String promo) throws Exception {
		
	}
}