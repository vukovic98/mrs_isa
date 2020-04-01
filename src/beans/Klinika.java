package beans;

import java.util.ArrayList;

public class Klinika {
	private String naziv;
	private String adresa;
	private String opis;
	
	//Reference po String ID-u, mozda ne mora ovako?
	private ArrayList<String> slobodni_termini;
	private ArrayList<String> lekari;
	private ArrayList<String> sale;
	private String cenovnik;
	public Klinika() {
		super();
	}
	public Klinika(String naziv, String adresa, String opis, ArrayList<String> slobodni_termini,
			ArrayList<String> lekari, ArrayList<String> sale, String cenovnik) {
		super();
		this.naziv = naziv;
		this.adresa = adresa;
		this.opis = opis;
		this.slobodni_termini = slobodni_termini;
		this.lekari = lekari;
		this.sale = sale;
		this.cenovnik = cenovnik;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getOpis() {
		return opis;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public ArrayList<String> getSlobodni_termini() {
		return slobodni_termini;
	}
	public void setSlobodni_termini(ArrayList<String> slobodni_termini) {
		this.slobodni_termini = slobodni_termini;
	}
	public ArrayList<String> getLekari() {
		return lekari;
	}
	public void setLekari(ArrayList<String> lekari) {
		this.lekari = lekari;
	}
	public ArrayList<String> getSale() {
		return sale;
	}
	public void setSale(ArrayList<String> sale) {
		this.sale = sale;
	}
	public String getCenovnik() {
		return cenovnik;
	}
	public void setCenovnik(String cenovnik) {
		this.cenovnik = cenovnik;
	}
}
