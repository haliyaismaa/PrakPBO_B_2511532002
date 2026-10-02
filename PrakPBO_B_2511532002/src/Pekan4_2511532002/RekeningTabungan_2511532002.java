package Pekan4_2511532002;

public class RekeningTabungan_2511532002 extends Rekening_2511532002{
	private double sukuBunga;
	
	public RekeningTabungan_2511532002(String nomor, String nama, double saldoAwal, String pinAwal, double sukuBunga) {
		super (nomor, nama, saldoAwal, pinAwal);
		this.sukuBunga=sukuBunga;
		
	}

	public void tambahBungaAkhirBulan() {
		double nominalBunga=saldo*(sukuBunga/100);
		saldo+=nominalBunga;
		
		String idTrx="TRX-B-" +System.currentTimeMillis();
		riwayatTransaksi.add(new Transaksi_2511532002(idTrx, "Bunga", nominalBunga));
		System.out.println("Bunga "+ sukuBunga+ "% berhasil ditambahkan: Rp"+ nominalBunga);
	}
}
