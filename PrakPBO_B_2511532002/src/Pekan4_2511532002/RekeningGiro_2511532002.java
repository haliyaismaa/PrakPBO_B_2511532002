package Pekan4_2511532002;

public class RekeningGiro_2511532002 extends Rekening_2511532002 {
	private double batasOverdraft;
	public RekeningGiro_2511532002(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft){
		super (nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft=batasOverdraft;
	}
	public double getBatasOverdraft() {
		return batasOverdraft;
	}

}
