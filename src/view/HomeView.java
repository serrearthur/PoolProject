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
import model.Member;

public class HomeView extends HttpServlet{
	//Obligatoire pour la définition d'un servlet
	private static final long serialVersionUID = 1L;
	
	public static final String View="/WEB-INF/home.jsp";
	public static final int memberPageSize=7;
	public static final String FIELD_NAME   = "memberName";
	public static final String FIELD_EMAIL   = "memberEmail";
	public static final String FIELD_PROMO   = "memberPromotion";
	public static final String FIELD_ID  = "memberId";
	
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
		boolean flag = false;
		
		Enumeration names = request.getParameterNames();
		
		for(;names.hasMoreElements();) {
			if(names.nextElement().equals("supprimer")) flag=true;			
		}
		
		int id=Integer.parseInt(request.getParameter(FIELD_ID));
		
		if(flag==false) {
		String name=request.getParameter(FIELD_NAME);
		String email=request.getParameter(FIELD_EMAIL);
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
        	mainController.updateMember(new Member(name, email,classeId),id);
        	result = "Utilisateur \""+name+"\" crée avec succès.";
        } else {
            result = "Erreur lors de la création :";
        }

		//request.setAttribute("controller", mainController);
		request.setAttribute("errors", errors );
        request.setAttribute("result", result);
		this.doGet(request, response);
		}
		else {
			mainController.deleteMember(id);
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