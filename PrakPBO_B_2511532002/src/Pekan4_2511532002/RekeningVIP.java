package Pekan4_2511532002;

public class RekeningVIP extends Rekening_2511532002{
	private double tambahan;
	
	public RekeningVIP(String nomor, String nama, double saldoAwal, String pinAwal, double tambahan) {
		super (nomor, nama, saldoAwal, pinAwal);
		this.tambahan=tambahan;
		this.saldo += tambahan;
		
	}

	public double tambahan() {
		return tambahan;
	}
}
