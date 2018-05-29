package classes;

public class Transaction {
	private String numeSursa, numeDestinatie,ibanSursa, ibanDestinatie, data;
	private Double suma;
	private Integer nrTranzactie;
	public Transaction(String numeSursa, String numeDestinatie, String ibanSursa, String ibanDestinatie, Double suma, String data, Integer nrTranzactie) {
		super();
		this.numeSursa = numeSursa;
		this.numeDestinatie = numeDestinatie;
		this.ibanSursa = ibanSursa;
		this.ibanDestinatie = ibanDestinatie;
		this.suma = suma;
		this.nrTranzactie=nrTranzactie;
		this.data=data;
	}
	
	public String getData() {
		return data;
	}
	
	public Integer getNrTranzactie() {
		return nrTranzactie;
	}
	
	
	public String getNumeSursa() {
		return numeSursa;
	}
	public void setNumeSursa(String numeSursa) {
		this.numeSursa = numeSursa;
	}
	public String getNumeDestinatie() {
		return numeDestinatie;
	}
	public void setNumeDestinatie(String numeDestinatie) {
		this.numeDestinatie = numeDestinatie;
	}
	public String getIbanSursa() {
		return ibanSursa;
	}
	public void setIbanSursa(String ibanSursa) {
		this.ibanSursa = ibanSursa;
	}
	public String getIbanDestinatie() {
		return ibanDestinatie;
	}
	public void setIbanDestinatie(String ibanDestinatie) {
		this.ibanDestinatie = ibanDestinatie;
	}
	public Double getSuma() {
		return suma;
	}
	public void setSuma(Double suma) {
		this.suma = suma;
	}
}
