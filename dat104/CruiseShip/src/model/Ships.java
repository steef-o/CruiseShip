package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "cruiseship", name = "ships")
public class Ships {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private Integer nummer;
	private String dag;
	private String dato;
	private String ank_avg;
	private String namn_skip;
	private Integer bruttotonn;
	private Integer lengde;
	private String dybde;
	private Integer max_pax;
	private String rederi;
	private String agent;
	private String fraa_hamn;
	private String til_hamn;
	private String nasjonalitet;
	private String bestilt;
	private String merknad;
	
	public Ships(){}
	
	public Ships(Integer nummer, String dag, String dato, String ank_avg, String namn_skip, Integer bruttotonn,
			Integer lengde, String dybde, Integer max_pax, String rederi, String agent, String fraa_hamn,
			String til_hamn, String nasjonalitet, String bestilt, String merknad) {
		this.nummer = nummer;
		this.dag = dag;
		this.dato = dato;
		this.ank_avg = ank_avg;
		this.namn_skip = namn_skip;
		this.bruttotonn = bruttotonn;
		this.lengde = lengde;
		this.dybde = dybde;
		this.max_pax = max_pax;
		this.rederi = rederi;
		this.agent = agent;
		this.fraa_hamn = fraa_hamn;
		this.til_hamn = til_hamn;
		this.nasjonalitet = nasjonalitet;
		this.bestilt = bestilt;
		this.merknad = merknad;
	}
	@Override
	public String toString() {
		return "Ships [id=" + id + ", nummer=" + nummer + ", dag=" + dag + ", dato=" + dato + ", ank_avg=" + ank_avg
				+ ", namn_skip=" + namn_skip + ", bruttotonn=" + bruttotonn + ", lengde=" + lengde + ", dybde=" + dybde
				+ ", max_pax=" + max_pax + ", rederi=" + rederi + ", agent=" + agent + ", fraa_hamn=" + fraa_hamn
				+ ", til_hamn=" + til_hamn + ", nasjonalitet=" + nasjonalitet + ", bestilt=" + bestilt + ", merknad="
				+ merknad + "]";
	}
	public int getId() {
		return id;
	}
	public Integer getNummer() {
		return nummer;
	}
	public String getDag() {
		return dag;
	}
	public String getDato() {
		return dato;
	}
	public String getAnk_avg() {
		return ank_avg;
	}
	public String getNamn_skip() {
		return namn_skip;
	}
	public Integer getBruttotonn() {
		return bruttotonn;
	}
	public Integer getLengde() {
		return lengde;
	}
	public String getDybde() {
		return dybde;
	}
	public Integer getMax_pax() {
		return max_pax;
	}
	public String getRederi() {
		return rederi;
	}
	public String getAgent() {
		return agent;
	}
	public String getFraa_hamn() {
		return fraa_hamn;
	}
	public String getTil_hamn() {
		return til_hamn;
	}
	public String getNasjonalitet() {
		if (nasjonalitet.equals("Eng")){
			return "engelsk";
		}else if(nasjonalitet.equals("Eng_Hol")){
			return "engelsk og nederlansk";
		}else{
		return nasjonalitet;
		}
	}
	public String getBestilt() {
		return bestilt;
	}
	public String getMerknad() {
		return merknad;
	}
}