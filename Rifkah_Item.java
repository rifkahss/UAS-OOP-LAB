package uas_praktik_oop_b;

public abstract class Rifkah_Item {
    private int id;
    private String nama;
    private float harga;
    private int stok;

    public Rifkah_Item(String nama, float harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public float getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public void kurangiStok(int qty) {
        if (stok >= qty) {
            stok -= qty;
        }
    }

    public abstract String getDetailItem();

    public double hitungTotalDariJumlah(int qty) {
        return harga * qty;
    }
}