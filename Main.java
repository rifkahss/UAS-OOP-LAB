package uas_praktik_oop_b;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import exceptions.InvalidEmailException;
import exceptions.InvalidPhoneNumberException;

public class Main {
    private ArrayList<Rifkah_Item> daftarItem = new ArrayList<>();
    private ArrayList<Rifkah_Pelanggan> daftarPelanggan = new ArrayList<>();
    private ArrayList<Rifkah_Order> daftarOrder = new ArrayList<>();

    private int itemIdCounter = 1;
    private int customerIdCounter = 1;
    private int orderIdCounter = 1;

    public void tambahItem(Rifkah_Item item) {
        item.setId(itemIdCounter++);
        daftarItem.add(item);
    }

    public void tambahTiketKonser(Rifkah_TiketKonser tiketKonser) {
        tiketKonser.setId(itemIdCounter++);
        daftarItem.add(tiketKonser);
    }

    public void tambahRekamanKonser(Rifkah_RekamanKonser rekamanKonser) {
        rekamanKonser.setId(itemIdCounter++);
        daftarItem.add(rekamanKonser);
    }

    public void tambahMerchandise(Rifkah_Merchandise merchandise) {
        merchandise.setId(itemIdCounter++);
        daftarItem.add(merchandise);
    }
    
    public void tambahPelanggan(Rifkah_Pelanggan pelanggan) {
        pelanggan.setCustomerID(customerIdCounter++);
        daftarPelanggan.add(pelanggan);
        System.out.println("Pelanggan berhasil didaftarkan dengan ID: " + pelanggan.getCustomerID());
    }

    public void buatOrder(int customerId, int itemId, int qty) {
        Rifkah_Pelanggan customer = null;
        for (Rifkah_Pelanggan p : daftarPelanggan) {
            if (p.getCustomerID() == customerId) {
                customer = p;
                break;
            }
        }

        if (customer == null) {
            System.out.println("Error: Pelanggan dengan ID " + customerId + " tidak ditemukan dalam database. Silakan daftarkan pelanggan terlebih dahulu.");
            return;
        }

        Rifkah_Item item = null;
        for (Rifkah_Item i : daftarItem) {
            if (i.getId() == itemId) {
                item = i;
                break;
            }
        }

        if (item == null) {
            System.out.println("Error: Barang dengan ID " + itemId + " tidak ditemukan.");
            return;
        }

        if (item.getStok() < qty) {
            System.out.println("Error: Maaf, stok " + item.getNama() + " tidak mencukupi. Silakan pesan barang lain atau coba lagi nanti.");
            return;
        }

        Rifkah_Order order = new Rifkah_Order(orderIdCounter++, customer, item, qty);
        daftarOrder.add(order);
        item.kurangiStok(qty);
        System.out.println("Order berhasil dibuat!");
    }

    public void lihatSemuaBarang() {
        System.out.println("Menu Lihat Semua Barang:");
        System.out.println("1. Tiket Konser:");
        for (Rifkah_Item item : daftarItem) {
            if (item instanceof Rifkah_TiketKonser) {
                System.out.println("- ID: " + item.getId() + ", " + item.getDetailItem());
            }
        }
        System.out.println("2. Merchandise:");
        for (Rifkah_Item item : daftarItem) {
            if (item instanceof Rifkah_Merchandise) {
                System.out.println("- ID: " + item.getId() + ", " + item.getDetailItem());
            }
        }
        System.out.println("3. Rekaman Konser:");
        for (Rifkah_Item item : daftarItem) {
            if (item instanceof Rifkah_RekamanKonser) {
                System.out.println("- ID: " + item.getId() + ", " + item.getDetailItem());
            }
        }
        System.out.print("Press any key to continue ...");
        new Scanner(System.in).nextLine();
    }

    public void cetakSemuaOrder() {
        System.out.println("Daftar Semua Pesanan:");
        for (Rifkah_Order order : daftarOrder) {
            System.out.println(order.printDetailOrder());
        }
        System.out.print("Press any key to continue ...");
        new Scanner(System.in).nextLine();
    }

    public static void main(String[] args) {
        Main mainApp = new Main();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        while (true) {
            System.out.println("===== Menu Kasir Festival Musik =====");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Lihat Semua Barang");
            System.out.println("3. Daftar Pelanggan Baru");
            System.out.println("4. Buat Pesanan");
            System.out.println("5. Lihat Semua Pesanan");
            System.out.println("6. Keluar");
            System.out.print("Pilihan: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (pilihan) {
                case 1:
                    System.out.println("Menu Tambah Barang:");
                    System.out.println("1. Tiket Konser");
                    System.out.println("2. Merchandise");
                    System.out.println("3. Rekaman Konser");
                    System.out.println("4. Kembali ke Menu Utama");
                    System.out.print("Pilihan: ");
                    int pilihanTambah = scanner.nextInt();
                    scanner.nextLine();  // Consume newline

                    switch (pilihanTambah) {
                        case 1:
                            System.out.print("Nama Tiket: ");
                            String namaTiket = scanner.nextLine();
                            System.out.print("Harga Tiket: ");
                            float hargaTiket = scanner.nextFloat();
                            scanner.nextLine();  // Consume newline
                            System.out.print("Stok Tiket: ");
                            int stokTiket = scanner.nextInt();
                            scanner.nextLine();  // Consume newline
                            System.out.print("Nama Artis: ");
                            String namaArtis = scanner.nextLine();
                            System.out.print("Tanggal dan Jam Konser (dd/MM/yyyy HH:mm): ");
                            String tanggalKonserStr = scanner.nextLine();
                            System.out.print("Venue: ");
                            String venue = scanner.nextLine();
                            try {
                                Date tanggalKonser = dateTimeFormat.parse(tanggalKonserStr);
                                Rifkah_TiketKonser tiket = new Rifkah_TiketKonser(namaTiket, hargaTiket, stokTiket, namaArtis, tanggalKonser, venue);
                                mainApp.tambahTiketKonser(tiket);
                            } catch (ParseException e) {
                                System.out.println("Format tanggal tidak valid.");
                            }
                            break;
                        case 2:
                            System.out.print("Nama Barang: ");
                            String namaMerch = scanner.nextLine();
                            System.out.print("Harga Barang: ");
                            float hargaMerch = scanner.nextFloat();
                            scanner.nextLine();  // Consume newline
                            System.out.print("Stok Barang: ");
                            int stokMerch = scanner.nextInt();
                            scanner.nextLine();  // Consume newline
                            System.out.print("Nama Merek: ");
                            String namaMerek = scanner.nextLine();
                            System.out.print("Kategori Barang: ");
                            String kategoriMerch = scanner.nextLine();
                            Rifkah_Merchandise merchandise = new Rifkah_Merchandise(namaMerch, hargaMerch, stokMerch, namaMerek, kategoriMerch);
                            mainApp.tambahMerchandise(merchandise);
                            break;
                        case 3:
                            System.out.print("Nama Rekaman: ");
                            String namaRekaman = scanner.nextLine();
                            System.out.print("Harga Rekaman: ");
                            float hargaRekaman = scanner.nextFloat();
                            scanner.nextLine();  // Consume newline
                            System.out.print("Stok Rekaman: ");
                            int stokRekaman = scanner.nextInt();
                            scanner.nextLine();  // Consume newline
                            System.out.print("Nama Artis: ");
                            String namaArtisRekaman = scanner.nextLine();
                            System.out.print("Tanggal dan Jam Konser (dd/MM/yyyy HH:mm): ");
                            String tanggalRekamanStr = scanner.nextLine();
                            System.out.print("Venue: ");
                            String venueRekaman = scanner.nextLine();
                            System.out.print("Link Rekaman: ");
                            String linkRekaman = scanner.nextLine();
                            try {
                                Date tanggalRekaman = dateTimeFormat.parse(tanggalRekamanStr);
                                Rifkah_RekamanKonser rekaman = new Rifkah_RekamanKonser(namaRekaman, hargaRekaman, stokRekaman, namaArtisRekaman, tanggalRekaman, venueRekaman, linkRekaman);
                                mainApp.tambahRekamanKonser(rekaman);
                            } catch (ParseException e) {
                                System.out.println("Format tanggal tidak valid.");
                            }
                            break;
                        case 4:
                            // Kembali ke Menu Utama
                            break;
                        default:
                            System.out.println("Pilihan tidak valid. Silakan pilih opsi yang sesuai.");
                    }
                    break;
                case 2:
                    mainApp.lihatSemuaBarang();
                    break;
                case 3:
                    System.out.print("Nama Pelanggan: ");
                    String namaPelanggan = scanner.nextLine();
                    System.out.print("Email: ");
                    String emailPelanggan = scanner.nextLine();
                    System.out.print("Nomor Telepon: ");
                    String noHpPelanggan = scanner.nextLine();
                    try {
                        Rifkah_Pelanggan pelanggan = new Rifkah_Pelanggan(mainApp.customerIdCounter, namaPelanggan, emailPelanggan, noHpPelanggan);
                        mainApp.tambahPelanggan(pelanggan);
                    } catch (InvalidEmailException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (InvalidPhoneNumberException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("ID Pelanggan: ");
                    int customerId = scanner.nextInt();
                    System.out.print("ID Barang: ");
                    int itemId = scanner.nextInt();
                    System.out.print("Jumlah Barang: ");
                    int qty = scanner.nextInt();
                    mainApp.buatOrder(customerId, itemId, qty);
                    break;
                case 5:
                    mainApp.cetakSemuaOrder();
                    break;
                case 6:
                    System.out.println("Terima kasih telah menggunakan program kasir kami.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih opsi yang sesuai.");
            }
        }
    }
}