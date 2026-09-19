package Pekan2_2511532002;

public class Transaksi_2511532002 {
String idTransaksi;
String jenis;
double nominal;
		
public Transaksi_2511532002(String id, String jenis, double nominal) {
	this.idTransaksi=id;
	this.jenis=jenis;
	this.nominal=nominal;
}
		
public void cetakDetail() {
	System.out.println("ID: "+idTransaksi+"|Jenis: "+jenis+"|Nominal: Rp"+nominal);
} 
}
