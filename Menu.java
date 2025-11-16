/**
 * Kelas Menu (Menu.java)
 * Merepresentasikan satu item menu di restoran.
 */
public class Menu {
    // Atribut untuk setiap item menu
    private String nama;
    private double harga;
    private String kategori; // "Makanan" atau "Minuman"

    /**
     * Constructor untuk membuat objek Menu baru.
     */
    public Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    // Getter untuk mendapatkan nama
    public String getNama() {
        return nama;
    }

    // Getter untuk mendapatkan harga
    public double getHarga() {
        return harga;
    }

    // Getter untuk mendapatkan kategori
    public String getKategori() {
        return kategori;
    }

    // Setter untuk mengubah harga (digunakan di fitur ubah menu)
    public void setHarga(double harga) {
        this.harga = harga;
    }
}