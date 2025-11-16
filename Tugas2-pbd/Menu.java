/**
 * Halo, di file Menu.java ini, kita mendefinisikan sebuah 'blueprint'
 * atau cetakan untuk objek Menu di restoran kita.
 *
 * Kelas ini bertugas untuk menyimpan data setiap item menu.
 * Ini adalah bagian dari konsep OOP (Object-Oriented Programming).
 * Kita pisahkan DATANYA (di sini) dari LOGIKANYA (di Main.java).
 */
public class Menu {
    
    // --- ATRIBUT ---
    // Kita gunakan 'private' untuk 'encapsulation'
    // agar data ini tidak bisa diubah sembarangan dari luar.
    
    private String nama;      // Untuk menyimpan nama, contoh: "Nasi Goreng"
    private double harga;     // Untuk menyimpan harga, contoh: 25000
    private String kategori;  // Untuk menyimpan kategori, "Makanan" atau "Minuman"

    /**
     * --- CONSTRUCTOR ---
     * Ini adalah method spesial yang dipanggil saat kita membuat objek Menu baru.
     * (Saat kita panggil 'new Menu(...)').
     *
     * Dia bertugas menerima data (nama, harga, kategori)
     * dan memasukkannya ke dalam atribut di atas.
     */
    public Menu(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }

    // --- METHODS (Getter & Setter) ---

    /**
     * --- GETTER ---
     * Ini adalah method 'getter' untuk MENDAPATKAN data 'private' dari luar.
     * Kita butuh ini untuk menampilkan nama di struk atau menu.
     */
    public String getNama() {
        return nama;
    }

    /**
     * Getter untuk mendapatkan harga.
     * Kita butuh ini untuk perhitungan total biaya.
     */
    public double getHarga() {
        return harga;
    }

    /**
     * Getter untuk mendapatkan kategori.
     * Kita butuh ini untuk mengelompokkan menu
     * dan untuk promo Beli 1 Gratis 1 Minuman.
     */
    public String getKategori() {
        return kategori;
    }

    /**
     * --- SETTER ---
     * Ini adalah method 'setter' untuk MENGUBAH data 'private'.
     * Kita hanya buat setter untuk harga, karena ini
     * dibutuhkan di fitur 'Ubah Harga Menu' oleh Admin.
     */
    public void setHarga(double harga) {
        this.harga = harga;
    }
}