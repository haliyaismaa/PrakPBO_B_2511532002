package shop.app;

import java.util.List;
import shop.StudentInfo;
import shop.model.DigitalProduct;
import shop.model.PhysicalProduct;
import shop.model.Product;
import shop.notification.CustomerContact;
import shop.notification.EmailChannel;
import shop.notification.NotificationChannel;
import shop.notification.NotificationService;
import shop.notification.SmsChannel;
import shop.notification.WhatsAppChannel;
import shop.payment.BankTransferPayment;
import shop.payment.CreditCardPayment;
import shop.payment.EWalletPayment;
import shop.payment.PaymentMethod;
import shop.service.CheckoutService;
import shop.shipping.ExpressShipping;
import shop.shipping.ShippingService;

public class TugasApp {
    public static void main(String[] args) {
        System.out.println("Tugas oleh " + StudentInfo.NAME + " (" + StudentInfo.NIM + ")");

        Product coffee = new PhysicalProduct("KOPI-001", "Kopi Arabika Gayo 250g", 85_000, 300);
        Product rendang = new PhysicalProduct("RDG-002", "Rendang Kemasan 250g", 120_000, 400);
        Product ebook = new DigitalProduct("EBK-003", "E-book Resep Kue Nusantara", 50_000,
                "https://toko-umkm.example/unduh/ebk-003");
        List<Product> cart = List.of(coffee, rendang, ebook);

        CheckoutService checkout = new CheckoutService();
        ShippingService express = new ExpressShipping();

        // 1. Grand total
        EWalletPayment wallet = new EWalletPayment("DompetKu", 300_000);
        PaymentMethod transfer = new BankTransferPayment("Bank Nagari");
        CreditCardPayment card = new CreditCardPayment("Siti Rahma", 1_000_000);

        System.out.println("\n== T1: Total dengan biaya admin (Ekspres) ==");
        printTotal(checkout, cart, express, wallet);
        printTotal(checkout, cart, express, transfer);
        printTotal(checkout, cart, express, card);

        // 2. policy() kartu kredit dan e-wallet
        System.out.println("Kebijakan kartu kredit : " + card.policy());
        System.out.println("Kebijakan e-wallet : " + wallet.policy());

        // 3. Daftar tiga kanal + satu kanal log (anonymous class)
        NotificationService service = new NotificationService();
        service.register(new EmailChannel());
        service.register(new SmsChannel(StudentInfo.SMS_MAX_LENGTH));
        service.register(new WhatsAppChannel());
        service.register(new NotificationChannel() {

            @Override
            public String channelName() {
                return "Log";
            }

            @Override
            public boolean send(CustomerContact contact, String message) {
                System.out.println("[LOG] " + contact.getName() + ": " + message);
                return true;
            }
        });

        // 4. Pesan panjang 
        String message = "Pesanan ORD-0001 sudah dikirim dengan kurir Ekspres. "
                + "Nomor resi akan muncul di aplikasi dalam 1x24 jam. "
                + "Jika ada kendala pada pesanan Anda, silakan hubungi layanan pelanggan "
                + "kami melalui aplikasi atau balas pesan ini. "
                + "Terima kasih telah berbelanja produk UMKM Sumatera Barat di toko kami!";

        CustomerContact siti = new CustomerContact("Siti Rahma", "siti@mail.com", "081234567890");
        CustomerContact budi = new CustomerContact("Budi Santoso", "", "+6281987654321");

        System.out.println("\n== T2: Notifikasi Status Pesanan ==");
        int sitiCount = service.broadcast(siti, message);
        System.out.println("Terkirim ke Siti lewat " + sitiCount + " kanal");
        int budiCount = service.broadcast(budi, message);
        System.out.println("Terkirim ke Budi lewat " + budiCount + " kanal");
    }

    private static void printTotal(CheckoutService checkout, List<Product> cart,
                                   ShippingService shipping, PaymentMethod payment) {
        long total = checkout.total(cart, shipping);
        long admin = payment.adminFee(total);
        System.out.println(payment.getName() + " : Rp"
                + checkout.grandTotal(cart, shipping, payment) + " (admin Rp" + admin + ")");
    }
}