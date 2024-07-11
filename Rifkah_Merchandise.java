package uas_praktik_oop_b;

public class Rifkah_Merchandise extends Rifkah_Item {
    private String namaMerek;
    private String kategori;

    public Rifkah_Merchandise(String nama, float harga, int stok, String namaMerek, String kategori) {
        super(nama, harga, stok);
        this.namaMerek = namaMerek;
        this.kategori = kategori;
    }

    @Override
    public String getDetailItem() {
        return "Nama: " + getNama() + ", Harga: Rp " + getHarga() + ", Stok: " + getStok() + ", Merek: " + namaMerek + ", Kategori: " + kategori;
    }
}