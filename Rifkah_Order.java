package uas_praktik_oop_b;

public class Rifkah_Order {
    private int orderId;
    private Rifkah_Pelanggan customer;
    private Rifkah_Item itemTerpilih;
    private int qtyItem;
    private double totalHarga;

    public Rifkah_Order(int orderId, Rifkah_Pelanggan customer, Rifkah_Item itemTerpilih, int qtyItem) {
        this.orderId = orderId;
        this.customer = customer;
        this.itemTerpilih = itemTerpilih;
        this.qtyItem = qtyItem;
        this.totalHarga = itemTerpilih.hitungTotalDariJumlah(qtyItem);
        itemTerpilih.kurangiStok(qtyItem);
    }

    public String printDetailOrder() {
        return "Order ID: " + orderId + "\nPelanggan: " + customer.getDetailPelanggan() + "\nBarang: " + itemTerpilih.getDetailItem() + "\nJumlah: " + qtyItem + "\nTotal Harga: Rp " + totalHarga;
    }
}