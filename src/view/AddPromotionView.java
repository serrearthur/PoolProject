package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.MainController;
import model.CRClass;

public class AddPromotionView extends HttpServlet{
	//Obligatoire pour la définition d'un servlet
	private static final long serialVersionUID = 1L;
	
	public static final String View="/WEB-INF/add_promotion.jsp";
	public static final String FIELD_NAME = "className";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		this.getServletContext().getRequestDispatcher(View).forward( request, response );
	}
	
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    	MainController mainController=MainController.getInstance();
    	String name=request.getParameter(FIELD_NAME);
    	String result;
    	boolean error=false;
    	try {
    		validateClassName(name);
    		mainController.getClasses().add(new CRClass(name));
    		result="Promotion \""+name+"\" crée";
    	} catch (Exception e) {
    		result=e.getMessage();
    		error=true;
    	} 
    	request.setAttribute("result", result);
    	request.setAttribute("error", error);
    	this.getServletContext().getRequestDispatcher(View).forward(request, response );
    }
    
    private void validateClassName(String name) throws Exception {
    	MainController mainController=MainController.getInstance();
    	if (name.isEmpty())
    		throw new Exception("Le nom de promotion ne peut pas être vide");
    	else {
    		for (CRClass promo : mainController.getClasses()) {
				if (promo.getName().equals(name)) {
					throw new Exception("La promotion \""+name+"\" existe déjà.");
				}
			}
    	}
    }
}
