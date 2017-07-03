package database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import model.Ships;
import util.ViewManager;

@Stateless
public class ShipsEAO {
	
	@PersistenceContext(name = "myPersistenceUnit")
	private EntityManager em;
	
	ViewManager vm = new ViewManager();
	
	/**
	 * Finner båt ved hjelp av unik id i databasen.
	 * @param id
	 * @return
	 */
	public Ships findById(int id){
		Ships s = null;
		try{
			TypedQuery<Ships> tq = 
					em.createQuery("SELECT s FROM Ships s WHERE s.id =" + id,
							Ships.class);
			s = tq.getSingleResult();
		}catch(NoResultException e){}
		return s;
	}
	/**
	 * Finner dagens båt(er).
	 * @return String for å fremvise dagens båt(er).
	 */
	public String findTodaysShip(){
		List<Ships> liste = null;
		try{
			TypedQuery<Ships> tq = 
					em.createQuery("SELECT s FROM Ships s WHERE s.dato =" + "'"+findTodaysDate()+"'",
				//	em.createQuery("SELECT s FROM Ships s WHERE s.dato =" + "'"+"11,06,2017"+"'",
							Ships.class);
			 liste = tq.getResultList();
		}catch(NoResultException e){}
	 String svar = vm.viewTodaysShipByTodaysDate(liste);
		return svar;
	}
	/**
	 * Hjelpemetode for å finne dagens dato.
	 * @return dagens dato som String formatert som: dd,MM,yyyy
	 */
	private String findTodaysDate(){
		Date dato = new Date();
		DateFormat DF = new SimpleDateFormat("dd,MM,yyyy");
		dato = Calendar.getInstance().getTime();
		String dagensDato = DF.format(dato);
		return dagensDato;
	}
	
	/**
	 * Metode for å behandle søk gjort på siden.
	 * @param input
	 * @return søketreff som String.
	 */
	public String SearchQuery(String input){
		List<Ships> liste = null;
		boolean type = isDate(input);
		String query = vm.createConditions(input, type);
		try{
			TypedQuery<Ships> tq = em.createQuery(query,Ships.class);
			liste = tq.getResultList();
		}catch(NoResultException e){}
		String svar = vm.createView(liste,type);
		return svar;
	}
	
	/**
	 * Sjekker om input er dato formatert som dd,MM,yyyy
	 * @param 
	 * @return true om det er en dato formatert som dd,MM,yyyy
	 */
	private boolean isDate(String input){
		return input.matches("[0-3]{1}[0-9]{1},[0-1]{1}[0-9]{1},[0-9]{4}");
	}

}
