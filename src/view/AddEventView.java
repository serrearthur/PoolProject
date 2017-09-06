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
import model.CodeReview;

public class AddEventView extends HttpServlet {
	//Obligatoire pour la définition d'un servlet
	private static final long serialVersionUID = 1L;
	
	public static final String View="/WEB-INF/add_event.jsp";
	public static final String FIELD_NAME   = "eventName";
	public static final String FIELD_DATE   = "eventDate";
	public static final String FIELD_DESCRIPTION   = "eventBirthdate";
	public static final String FIELD_PROMO   = "eventPromotion";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		MainController mainController=MainController.getInstance();
		request.setAttribute("controller", mainController);
		this.getServletContext().getRequestDispatcher(View).forward(request, response );
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		MainController mainController=MainController.getInstance();
		String name=request.getParameter(FIELD_NAME);
		String date=request.getParameter(FIELD_DATE);
		String description=request.getParameter(FIELD_DESCRIPTION);
		String promo=request.getParameter(FIELD_PROMO);
		
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
            validationDescription(description);
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
        	
        	mainController.getCodeReviews().add(new CodeReview(name, description, date, crclass));
        	result = "Code Review \""+name+"\" crée avec succès.";
        } else {
            result = "Erreur lors de la création :";
        }
		
		request.setAttribute("controller", mainController);
		request.setAttribute("errors", errors );
        request.setAttribute("result", result);
		this.getServletContext().getRequestDispatcher(View).forward(request, response );
	}

	private void validationPromo(String promo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	private void validationDescription(String description) throws Exception {
		// TODO Auto-generated method stub
		
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

	private void validationName(String name) throws Exception {
		if (name == null || name.trim().length() == 0)
    		throw new Exception("Le nom de la Code Review ne peut pas être vide");
	}
}
