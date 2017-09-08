package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.MainController;
import model.Member;

public class ShowPromotionView extends HttpServlet{
	//Obligatoire pour la définition d'un servlet
	private static final long serialVersionUID = 1L;
	
	public static final String View="/WEB-INF/show_promotion.jsp";
	public static final String FIELD_PROMO_ID = "promoId";
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		MainController mainController=MainController.getInstance();
		request.setAttribute("controller", mainController);
		request.setAttribute("promoId",	request.getParameter("promoId"));
		this.getServletContext().getRequestDispatcher(View).forward( request, response );
	}
	
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
    	MainController mainController=MainController.getInstance();
    	String promoId=request.getParameter("promoId");
    	Member m;
    	switch(request.getParameter("submit")) {
    	case "delete" :
    		m = mainController.getMemberById(Integer.parseInt(request.getParameter("memberId")));
    		m.setCrclassId(null);
    		mainController.updateMember(m, m.getId());
    		break;
    	case "adduser" :
    		m = mainController.getMemberById(Integer.parseInt(request.getParameter("memberId")));
    		m.setCrclassId(Integer.parseInt(promoId));
    		mainController.updateMember(m, m.getId());
    		break;
    	}
    	
    	this.doGet(request, response);
    }
}