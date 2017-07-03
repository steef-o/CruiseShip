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
		String dagensBåt = shipsEAO.findTodaysShip();
		request.setAttribute("svar", dagensBåt);
		
		request.getRequestDispatcher("WEB-INF/jsp/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String brukerInput = request.getParameter("input");
		String sok = shipsEAO.SearchQuery(brukerInput);
		
		//feilmelding om søket ikke returnerer noen treff.
		if (sok == null){
			sok = "<b>Fant ingen søketreff</b>. </br> </br> <font size=\"3\"> <i> Søketips: </br> Dato må skrives i formatet: dd,mm,yyyy med komma i mellom tallene. </Br>"
					+ "Skipsnavn må ha stor forbokstav (du kan også søke på deler av navnet).</i></font>";
		}
		request.setAttribute("sokSvar", sok);
		
		doGet(request, response);
	}

}
