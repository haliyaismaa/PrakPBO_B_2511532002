package Pekan4_2511532002;

import java.util.ArrayList;

public class Rekening_2511532002 {
	private String nomorRekening;
	private String namaPemilik;
	private String pin;
	
	protected double saldo;
	protected ArrayList<Transaksi_2511532002>riwayatTransaksi;
	
	public Rekening_2511532002(String nomor, String nama, double saldoAwal, String pinAwal) {
		this.nomorRekening=nomor;
		this.namaPemilik=nama;
		this.saldo=saldoAwal;
		
		//Validasi pin di dalam Constructor
		if (pinAwal.length()==6) {
			this.pin=pinAwal;
		}else {
			System.out.println("Peringatan: PIN Harus 6 digit! Menggunakan PIN Default 123456");
			this.pin="123456";
		}
		
		this.riwayatTransaksi=new ArrayList<>();
		System.out.println("Rekening atas nama "+ namaPemilik+" berhasil dibuat dengan saldo Rp"+saldo);	
	}
	
	//getter
	public String getNomorRekening() {
		return nomorRekening;
	}
	public String getNamaPemilik() {
		return namaPemilik;
	}
	public double getSaldo() {
		return saldo;
	}
	
	//veriv pin 
	public boolean otentikasi(String pinInput) {
		return this.pin.equals(pinInput);
	}
	public void setorTunai(double nominal) {
		if (nominal >0) {
			saldo+= nominal;
			
			//merekam riwayat (Pembuatan objek Transaksi di dalam method)
			String idTrix="TRX-S-"+System.currentTimeMillis();
			Transaksi_2511532002 txBaru =new Transaksi_2511532002(idTrix, "Kredit", nominal);
			riwayatTransaksi.add(txBaru);
				
			System.out.println("Setor tunai Rp"+nominal+" berhasil. Saldo saat ini: Rp"+saldo);
		}else {
			System.out.println("Gagal: Nominal setor harus lebih dari 0!");
		}
	}
	public void tarikTunai(double nominal) {
		if (nominal < 10000){
			System.out.println("Transaksi Gagal, minimal penarikan Rp 10.000");
		}else if (nominal > saldo) {
			System.out.println("Transaksi Gagal: Saldo tidak mencukupi. Saldo Anda: Rp"+saldo+"!");
		}else {
			saldo -= nominal;
			System.out.println("Tarik tunai Rp" +nominal+" berhasil. Saldo saat ini: Rp"+saldo);
			String idTrix="TRX-T-"+System.currentTimeMillis();
			Transaksi_2511532002 txBaru =new Transaksi_2511532002(idTrix, "Debit", nominal);
			riwayatTransaksi.add(txBaru);
		}
		}
	public void cekInformasi() {
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening :"+nomorRekening);
		System.out.println("Nama Pemilik :"+namaPemilik);
		System.out.println("Saldo Akhir  : Rp"+saldo);
		System.out.println("------------------------");
	}
	public void cetakMutasi() {
		System.out.println("--- Cetak Mutasi ----");
		if (riwayatTransaksi.isEmpty()) {
			System.out.println("Belum ada transaksi pada rekening ini!");
		}else {
			for (Transaksi_2511532002 t:riwayatTransaksi) {
				t.cetakDetail();
			}
		}
	}
}
