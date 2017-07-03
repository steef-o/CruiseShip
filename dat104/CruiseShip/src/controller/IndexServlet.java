package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ShipsEAO;

@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ShipsEAO shipsEAO;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dagensB�t = shipsEAO.findTodaysShip();
		request.setAttribute("svar", dagensB�t);
		
		request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String brukerInput = request.getParameter("input");
		String sok = shipsEAO.SearchQuery(brukerInput);
		
		//feilmelding om s�ket ikke returnerer noen treff.
		if (sok == null){
			sok = "<b>Fant ingen s�ketreff</b>. </br> </br> <font size=\"3\"> <i> S�ketips: </br> Dato m� skrives i formatet: dd,mm,yyyy med komma i mellom tallene. </Br>"
					+ "Skipsnavn m� ha stor forbokstav (du kan ogs� s�ke p� deler av navnet).</i></font>";
		}
		request.setAttribute("sokSvar", sok);
		
		doGet(request, response);
	}

}
