package Pekan4_2511532002;

import java.util.Scanner;
import java.util.ArrayList;

public class Main_2511532002 {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		ArrayList<Rekening_2511532002>daftarRekening=new ArrayList<>();
		Rekening_2511532002 akunAktif=null; 
		boolean isRunning =true;
	
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
		while (isRunning) {
			System.out.println( "\nMenu Utama:");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("5. Ganti Akun");
			System.out.println("6. Cetak Mutasi (Riwayat)");
			System.out.println("7. Simulasi Akhir Bulan (Khusus Tabungan )");
			System.out.println("0. Keluar");
			System.out.println("Pilih Menu: ");
			
			int pilihan =input.nextInt();
			input.nextLine(); //Membersihkan buffer enter
			
			switch (pilihan) {
			case 1:
				System.out.println("Masukkan No Rekening: ");
				String no =input.nextLine();
				System.out.println("Masukkan Nama Pemilik: ");
				String nama =input.nextLine();
				System.out.println("Msukkan Saldo Awal: ");
				double saldo=input.nextDouble();
				input.nextLine();
				System.out.println("Masukkan PIN anda! (6 Digit)");
				String pinBaru=input.nextLine();
				System.out.println("Pilih Produk: 1. Tabungan Umum | 2. Giro Bisnis | 3. RekeningVIP");
				int produk=input.nextInt();
				input.nextLine();
				
				Rekening_2511532002 akunBaru=null;
				
				if(produk == 1) {
					System.out.println("Masukan Suku Bunga (%): ");
					double sukuBunga=input.nextDouble();
					input.nextLine();
					akunBaru =new RekeningTabungan_2511532002(no, nama, saldo, pinBaru, sukuBunga);
				}else if (produk==2) {
					System.out.println("Masukkan batas Overdraft (limitPinjaman): ");
					double batasOverdraft=input.nextDouble();
					input.nextLine();
					akunBaru=new RekeningGiro_2511532002(no, nama, saldo, pinBaru, batasOverdraft);
				}else if (produk ==3) {
					akunBaru=new RekeningVIP(no, nama, saldo, pinBaru, 100000);
					System.out.println("Anda akan menadapatkan saldo tambahan!");
					System.out.println("Saldo anda menjadi "+ akunBaru.getSaldo());
				}else {
					System.out.println("Pilihan tidak valid!");
				}
				if (akunBaru != null) {
					daftarRekening.add(akunBaru);
					akunAktif=akunBaru;
				}
		
				break;
				
			case 2:
				if (akunAktif ==null) {
					System.out.println("Eror Mohon maaf, Anda belum memiliki nomor rekening!");
				}else {
					System.out.println("Masukkan nominal setor: ");
					double setor = input.nextDouble();
					akunAktif.setorTunai(setor); //Memanggil behavior/ method
				}break;
				
			case 3:
				if (akunAktif ==null) {
					System.out.println("Eror Mohon maaf, Anda belum memiliki nomor rekening!");
				}else {
					System.out.println("Masukkan PIN anda:");
					String pinInput=input.nextLine();
					if (akunAktif.otentikasi(pinInput)) {
					System.out.println("Masukkan nominal tarik: ");
					double tarik = input.nextDouble();
					input.nextLine();
					akunAktif.tarikTunai(tarik); //Memanggil behavior/ method
					}else {
						System.out.println("PIN Anda Salah!");
					}
				}break;	
				
			case 4:
				if (akunAktif == null) {
					System.out.println("Error Anda belum membuka rekening!");
				}else {
					akunAktif.cekInformasi();
				}break;
				
			case 5:
				if(daftarRekening.isEmpty()) {
					System.out.println("Error: Belum ada Rekening yang terdaftar!");
				}else {
					System.out.println("Masukkan No Rekening yang ignin diaktifkan:");
					String noCari =input.nextLine();
					Rekening_2511532002 hasil =null;
					
					for (int i=0; i<daftarRekening.size(); i++) {
						Rekening_2511532002 r =daftarRekening.get(i);
						if (r.getNomorRekening().equals(noCari)) {
							hasil =r;
							break;
						}
				}
					if (hasil==null) {
						System.out.println("Error: Nomor rekenining tidak ditemukan!");
					}else {
						akunAktif=hasil;
						System.out.println("Berhasil berpindah ke rekening atas nama "+ akunAktif.getNamaPemilik());
					}}break;

				
			case 6:
				if (akunAktif==null)
					System.out.println("Anda belum membuka Rekeniing");
			else {
				System.out.println("Masukkan PIN anda:");
				String pinInput=input.nextLine();
				if (akunAktif.otentikasi(pinInput)) {
				akunAktif.cetakMutasi();
			}else {
				System.out.println("Akses Ditolak: PIN yang Anda masukkan salah! ");
			}
			}break;
			
			case 7:
				if (akunAktif==null) {
					System.out.println("Error: Anda belum membuka Rekening!");
				}else if (akunAktif instanceof RekeningTabungan_2511532002) {
					RekeningTabungan_2511532002 tab =(RekeningTabungan_2511532002)akunAktif;
					tab.tambahBungaAkhirBulan();
				}else {
				System.out.println("Gagal: Fitur Bunga Akhir Bulan hanya berlaku untuk Rekening Tabungan");
				}break;
				
			case 0:
				isRunning =false;
				System.out.println("Sistem ditutup. Terima kasih!");
				break;
			
			default:
				System.out.println("Pilihan tidak valid!");
					}
			}
		input.close();
		}
	}
