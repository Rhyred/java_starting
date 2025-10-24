/**
 * Kelas Menu (Blueprint/Rancangan)
 * Merepresentasikan satu item menu di restoran.
 * Ini memenuhi Indikator 1: Implementasi class, tipe data,
 * variabel, identifier, dan keyword.
 */
public class Menu {
    // 1. Variabel (Attributes) dengan tipe data (String, double)
    String nama;
    double harga;
    String kategori; // "Makanan" atau "Minuman"

    /**
     * Constructor (Method khusus untuk membuat object)
     * Digunakan untuk menginisialisasi object Menu baru.
     */
    public Menu(String nama, double harga, String kategori) {
        // 'this' adalah keyword untuk merujuk ke variabel milik object ini
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
}