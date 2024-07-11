package uas_praktik_oop_b;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Rifkah_RekamanKonser extends Rifkah_Item {
    private String namaArtis;
    private Date tanggalKonser;
    private String venue;
    private String link;

    public Rifkah_RekamanKonser(String nama, float harga, int stok, String namaArtis, Date tanggalKonser, String venue, String link) {
        super(nama, harga, stok);
        this.namaArtis = namaArtis;
        this.tanggalKonser = tanggalKonser;
        this.venue = venue;
        this.link = link;
    }

    @Override
    public String getDetailItem() {
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        return "Nama: " + getNama() + ", Harga: Rp " + getHarga() + ", Stok: " + getStok() + ", Artis: " + namaArtis + ", Tanggal: " + dateTimeFormat.format(tanggalKonser) + ", Venue: " + venue + ", Link: " + link;
    }
}