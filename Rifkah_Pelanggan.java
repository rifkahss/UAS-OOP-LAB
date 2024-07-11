package uas_praktik_oop_b;

import exceptions.InvalidEmailException;
import exceptions.InvalidPhoneNumberException;

public class Rifkah_Pelanggan {
    private int customerID;
    private String nama;
    private String email;
    private String noHp;

    public Rifkah_Pelanggan(int customerID, String nama, String email, String noHp) throws InvalidEmailException, InvalidPhoneNumberException {
        if (!email.contains("@")) {
            throw new InvalidEmailException("Email tidak valid.");
        }
        if (!noHp.matches("[0-9]+")) {
            throw new InvalidPhoneNumberException("Nomor telepon tidak valid.");
        }
        this.customerID = customerID;
        this.nama = nama;
        this.email = email;
        this.noHp = noHp;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getDetailPelanggan() {
        return nama + " (" + email + ", " + noHp + ")";
    }
}