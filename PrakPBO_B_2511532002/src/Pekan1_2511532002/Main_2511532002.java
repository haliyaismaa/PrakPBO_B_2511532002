package Pekan1_2511532002;

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
				
				//Instalasi Object/ Menjalankan Constructor
				akunAktif =new Rekening_2511532002(no, nama, saldo);
				daftarRekening.add(akunAktif);
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
					System.out.println("Masukkan nominal tarik: ");
					double setor = input.nextDouble();
					akunAktif.tarikTunai(setor); //Memanggil behavior/ method
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
						if (r.nomorRekening.equals(noCari)) {
							hasil =r;
							break;
						}
					}
					if (hasil==null) {
						System.out.println("Error: Nomor rekenining tidak ditemukan!");
					}else {
						akunAktif=hasil;
						System.out.println("Berhasil berpindah ke rekening atas nama "+ akunAktif.namaPemilik);
					}
					break;
				}
				
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
