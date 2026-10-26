package Pekan5_2511532002;

public class RekeningGiro_2511532002 extends Rekening_2511532002 {
	private double batasOverdraft;
	public RekeningGiro_2511532002(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft){
		super (nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft=batasOverdraft;
	}
	public double getBatasOverdraft() {
		return batasOverdraft;
	}
	
	@Override
	public boolean tarikTunai(double nominal) {
	    if (nominal <= 0) {
	        System.out.println("Gagal: Nominal tarik Giro harus lebih dari 0!");
	        return false;
	    } else if (nominal > (saldo + batasOverdraft)) {
	        System.out.println("Transaksi Gagal: Melebihi batas overdraft! Maksimal penarikan: Rp" + (saldo + batasOverdraft));
	        return false;
	    } else {
	        saldo -= nominal;
	        String idTrx = "TRX-G-" + System.currentTimeMillis();
	        riwayatTransaksi.add(new Transaksi_2511532002(idTrx, "Debit Giro", nominal));
	        System.out.println("Tarik tunai Giro Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
	        return true;
	    }
	}

}
