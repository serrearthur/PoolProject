package view;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.MainController;
import model.CRClass;
import model.Member;

public class AddMemberView extends HttpServlet{
	//Obligatoire pour la définition d'un servlet
	private static final long serialVersionUID = 1L;
	
	public static final String View="/WEB-INF/add_member.jsp";
	public static final String FIELD_NAME   = "memberName";
	public static final String FIELD_EMAIL   = "memberEmail";
	public static final String FIELD_BIRTHDATE   = "memberBirthdate";
	public static final String FIELD_PROMO   = "memberPromotion";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		MainController mainController=MainController.getInstance();
		request.setAttribute("controller", mainController);
		this.getServletContext().getRequestDispatcher(View).forward( request, response );
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		MainController mainController=MainController.getInstance();
		String name=request.getParameter(FIELD_NAME);
		String email=request.getParameter(FIELD_EMAIL);
		String birthdate=request.getParameter(FIELD_BIRTHDATE);
		String promo=request.getParameter(FIELD_PROMO);
		
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
            validationBirthdate(birthdate);
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
        	
        	mainController.getMembers().add(new Member(name, email, birthdate, crclass));
        	result = "Utilisateur \""+name+"\" crée avec succès.";
        } else {
            result = "Erreur lors de la création :";
        }

		request.setAttribute("controller", mainController);
		request.setAttribute("errors", errors );
        request.setAttribute("result", result);
		this.getServletContext().getRequestDispatcher(View).forward(request, response );
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