package classes;

public class Transaction {
	private String numeSursa, numeDestinatie,ibanSursa, ibanDestinatie, data;
	double suma, nrTranzactie;
	public Transaction(String numeSursa, String numeDestinatie, String ibanSursa, String ibanDestinatie, double suma, String data, int nrTranzactie) {
		super();
		this.numeSursa = numeSursa;
		this.numeDestinatie = numeDestinatie;
		this.ibanSursa = ibanSursa;
		this.ibanDestinatie = ibanDestinatie;
		this.suma = suma;
		this.nrTranzactie=nrTranzactie;
		this.data=data;
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
	public double getSuma() {
		return suma;
	}
	public void setSuma(double suma) {
		this.suma = suma;
	}
}
