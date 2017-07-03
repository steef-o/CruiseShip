package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import model.Ships;

public class ViewManager {
	private String search;
	/**
	 * Mottar en objekt ship og formaterer dette til med dagens dato til å bli presentert på hjemmesiden.
	 * @param s
	 * @return
	 */
	public String viewTodaysShipByTodaysDate(List<Ships> liste){
		StringBuilder sb = new StringBuilder();
		if(liste.size()== 1){
			sb.append("I dag kommer skipet " + liste.get(0).getNamn_skip()+".</br> Nasjonaliteten på de ombord er " +liste.get(0).getNasjonalitet()
			+ ". </br> Skipet skal ligge her i tidsrommet " +liste.get(0).getAnk_avg() +",</br> og har med seg "+ liste.get(0).getMax_pax()+" passasjerer.");
		}else if(liste.isEmpty()){
			sb.append("Det er ingen skip i dag.");
		}else{
			sb.append("I dag kommer det to skip.</br> </br> Første skipet er " + liste.get(0).getNamn_skip()+ " </br> Nasjonaliteten på de ombord er "+liste.get(0).getNasjonalitet()
					+". </br> Skipet skal ligge her i tidsrommet " + liste.get(0).getAnk_avg()+ ",</br> og har med seg "+liste.get(0).getMax_pax()+" passasjerer.");
			sb.append( "</br> </br> Andre skip er " + liste.get(1).getNamn_skip()+ " </br> Nasjonaliteten på de ombord er "+liste.get(1).getNasjonalitet()
					+". </br> Skipet skal ligge her i tidsrommet " + liste.get(1).getAnk_avg()+ ",</br> og har med seg "+liste.get(1).getMax_pax()+" passasjerer.");
		}
		String svar = sb.toString();
		return svar;
	}
	
	public String createView(List<Ships> liste, boolean type){
		String svar = null;
		for (int i = 0; i < liste.size(); i++) {
			if(type){
				svar = viewShipByDate(liste);
			}else{
				svar =viewShipByName(liste);
			}
		}
		return svar;
	}
	
	public String createConditions(String search,boolean type){
	String searchType = null;
		if(type){
			searchType = "s.dato";
		}else{
			searchType = "s.namn_skip";
		}
		
		StringBuilder queryString = new StringBuilder();
		queryString.append("SELECT s FROM Ships s WHERE ");

		if(search != null && !search.isEmpty()){
			search = StringEscapeUtils.escapeSql(search);
			String[] words = search.split("\\s+"); // regexp for whitespace

			if (words.length == 1) {
				queryString.append(searchType + " LIKE '%")
				.append(words[0])
				.append("%'");
			} else if (words.length != 0) {
				
				String formatted = formatSearch(words);
				
				//queryString.append(searchType + "LIKE ALL (array[")
				queryString.append(searchType + " LIKE '%")
				.append(formatted)
				.append("%'");
			}
		}
		return queryString.toString();
	}
	/**
	 * Hjelpemetode for å formatere søk med flere enn ett ord, slik at det blir riktig når SQL spørringen skal gjennomføres.
	 * @param words
	 * @return String med formatert SQL delstreng for flere ord.
	 */
	private String formatSearch(String[] words){
		StringBuilder tagString = new StringBuilder();
		int numTags = 0;
		for (String tag : words) {

			if (tag.isEmpty())
				continue;

			if (numTags != 0)
				tagString.append(" ");

			tagString.append(tag);
			numTags++;
		} 
	return tagString.toString();
	
}
	private String viewShipByDate(List<Ships> liste){
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < liste.size(); j++) {
			sb.append(liste.get(j).getDato()+" Kommer skipet " + liste.get(j).getNamn_skip()+".</br> Nasjonaliteten på de ombord er " +liste.get(j).getNasjonalitet()
			+ ". </br> Skipet skal ligge her i tidsrommet " +liste.get(j).getAnk_avg() +",</br> og har med seg "+ liste.get(j).getMax_pax()+" passasjerer. </br></br>");
		}
		String svar = sb.toString();
		return svar;
	}
	private String viewShipByName(List<Ships> liste) {
		StringBuilder sb = new StringBuilder();
		sb.append("Skipet " + liste.get(0).getNamn_skip() +" har "+liste.get(0).getMax_pax()+" passasjerer med "+liste.get(0).getNasjonalitet()+" nasjonalitet,"+" </br> og kommer følgende datoer: </br> ");
		for (int i = 0; i < liste.size(); i++) {
			sb.append(liste.get(i).getDag()+" "+liste.get(i).getDato() +" : "+liste.get(i).getAnk_avg() + " </br>");
		}
		return sb.toString();
	}
	
}
