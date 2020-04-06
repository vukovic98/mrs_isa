package beans;

public abstract class Korisnik {
	private String userID;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	private String email;
	private String lozinka;
	private String ime;
	private String prezime;
	private String adresa;
	private String grad;
	private String drzava;
	private String broj_telefona;
	private String JBO;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public String getAdresa() {
		return adresa;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public String getGrad() {
		return grad;
	}
	public void setGrad(String grad) {
		this.grad = grad;
	}
	public String getDrzava() {
		return drzava;
	}
	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}
	public String getBroj_telefona() {
		return broj_telefona;
	}
	public void setBroj_telefona(String broj_telefona) {
		this.broj_telefona = broj_telefona;
	}
	public String getJBO() {
		return JBO;
	}
	public void setJBO(String jBO) {
		JBO = jBO;
	}

	public Korisnik(String userID, String email, String lozinka, String ime, String prezime, String adresa, String grad,
			String drzava, String broj_telefona, String jBO) {
		super();
		this.userID = userID;
		this.email = email;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.adresa = adresa;
		this.grad = grad;
		this.drzava = drzava;
		this.broj_telefona = broj_telefona;
		JBO = jBO;
	}
	public Korisnik() {
		super();
	}
	
	
}
