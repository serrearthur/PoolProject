package view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.MainController;

public class HomeView extends HttpServlet{
	//Obligatoire pour la définition d'un servlet
	private static final long serialVersionUID = 1L;
	
	public static final String View="/WEB-INF/home.jsp";
	public static final int memberPageSize=7;
	
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
}