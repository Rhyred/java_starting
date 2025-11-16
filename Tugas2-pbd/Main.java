// --- IMPORT ---
// Kita butuh library ini untuk program kita
import java.util.ArrayList; // Untuk menyimpan daftar menu & pesanan (Array dinamis)
import java.util.HashMap;   // Untuk menghitung jumlah pesanan (di struk)
import java.util.Map;       // (Pasangan dari HashMap)
import java.util.Scanner;   // Untuk mengambil input dari pengguna

/**
 * Halo, ini adalah file Main.java.
 * Ini adalah KELAS UTAMA yang berisi semua logika aplikasi restoran kita,
 * seperti menampilkan menu, menerima pesanan, menghitung, dan manajemen menu.
 */
public class Main {

    // --- VARIABEL GLOBAL (STATIC) ---
    // Kita buat variabel ini 'static' agar bisa diakses
    // oleh semua method di dalam kelas Main ini.
    
    /**
     * Sesuai soal, kita "Pergunakan Array".
     * Kita pakai 'ArrayList' karena ini adalah versi 'Array' yang dinamis.
     * Ukurannya bisa bertambah (saat admin tambah menu)
     * dan berkurang (saat admin hapus menu).
     *
     * 'daftarMenu' ini bertindak sebagai 'database' menu kita.
     */
    private static ArrayList<Menu> daftarMenu = new ArrayList<>();
    
    /**
     * 'pesanan' digunakan untuk menyimpan item apa saja yang
     * dipesan oleh pelanggan dalam satu transaksi.
     */
    private static ArrayList<Menu> pesanan = new ArrayList<>();
    
    /**
     * Kita buat SATU objek Scanner saja di sini.
     * Ini adalah 'best practice' agar tidak boros memori
     * dan menghindari error 'resource leak'.
     */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * --- METHOD MAIN (Titik Awal Program) ---
     * Program Java akan selalu dimulai dari sini.
     */
    public static void main(String[] args) {
        
        // 1. Kita isi dulu 'database' menu kita dengan data awal
        inisialisasiMenu();

        // 2. Ini adalah 'Game Loop' atau 'Navigation Loop' utama.
        // Kita pakai 'while(berjalan)' agar aplikasi terus berjalan
        // dan tidak langsung keluar setelah satu perintah.
        boolean berjalan = true;
        while (berjalan) {
            System.out.println("\n--- Aplikasi Restoran Sederhana ---");
            System.out.println("1. Menu Pelanggan (Pesan)");
            System.out.println("2. Menu Admin (Kelola Menu)");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu (1-3): ");

            // 3. Kita pakai 'switch' untuk navigasi menu utama
            String pilihan = scanner.nextLine();
            switch (pilihan) {
                case "1":
                    menuPelanggan(); // Panggil method untuk pelanggan
                    break;
                case "2":
                    menuAdmin();     // Panggil method untuk admin
                    break;
                case "3":
                    berjalan = false; // Ini akan menghentikan loop 'while'
                    System.out.println("Terima kasih telah menggunakan aplikasi.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        // 4. Tutup scanner setelah loop selesai
        scanner.close();
    }

    /**
     * Method ini hanya untuk mengisi 'daftarMenu'
     * dengan data awal sesuai syarat (minimal 4 makanan, 4 minuman).
     */
    private static void inisialisasiMenu() {
        daftarMenu.add(new Menu("Nasi Goreng", 25000, "Makanan"));
        daftarMenu.add(new Menu("Mie Ayam", 20000, "Makanan"));
        daftarMenu.add(new Menu("Soto Betawi", 35000, "Makanan"));
        daftarMenu.add(new Menu("Gado-Gado", 22000, "Makanan"));
        daftarMenu.add(new Menu("Es Teh Manis", 8000, "Minuman"));
        daftarMenu.add(new Menu("Es Jeruk", 10000, "Minuman"));
        daftarMenu.add(new Menu("Jus Alpukat", 15000, "Minuman"));
        daftarMenu.add(new Menu("Kopi Susu", 18000, "Minuman"));
    }

    // =================================================================
    // == BAGIAN MENU PELANGGAN
    // =================================================================

    /**
     * Method ini adalah 'alur kerja' untuk pelanggan.
     * Dia memanggil method lain secara berurutan.
     */
    private static void menuPelanggan() {
        System.out.println("\n--- Selamat Datang di Restoran Kami ---");
        // 1. Tampilkan daftar menu
        tampilkanMenuPelanggan();
        
        // 2. Terima input pesanan
        terimaPesanan();
        
        // 3. Jika ada pesanan (tidak kosong), hitung dan cetak
        if (!pesanan.isEmpty()) {
            hitungDanCetakStruk();
            
            // 4. Penting: Kosongkan 'ArrayList pesanan'
            //    agar pelanggan berikutnya mulai dari struk kosong.
            pesanan.clear();
        }
    }

    /**
     * Method untuk menampilkan menu ke pelanggan.
     * Sesuai soal: "Format untuk menampilkan data menu adalah dikelompokkan berdasarkan kategori".
     *
     * Untuk melakukan ini, kita pakai DUA buah 'struktur pengulangan' (loop for) terpisah.
     */
    private static void tampilkanMenuPelanggan() {
        System.out.println("\n--- DAFTAR MENU ---");
        int nomor = 1;

        // Loop 1: Hanya untuk 'Makanan'
        System.out.println("Kategori: Makanan");
        for (Menu item : daftarMenu) {
            if (item.getKategori().equalsIgnoreCase("Makanan")) {
                // 'printf' digunakan untuk format cetak yang rapi
                System.out.printf("%d. %-20s - Rp %,.0f%n",
                        nomor++, item.getNama(), item.getHarga());
            }
        }

        // Loop 2: Hanya untuk 'Minuman'
        System.out.println("\nKategori: Minuman");
        for (Menu item : daftarMenu) {
            if (item.getKategori().equalsIgnoreCase("Minuman")) {
                System.out.printf("%d. %-20s - Rp %,.0f%n",
                        nomor++, item.getNama(), item.getHarga());
            }
        }
        System.out.println("---------------------");
    }

    /**
     * Method untuk menerima input pesanan dari pelanggan.
     * Sesuai soal: "pesan tidak terbatas, hingga pelanggan memasukkan input 'selesai'".
     *
     * Ini adalah implementasi sempurna untuk 'struktur pengulangan' 'while(true)'.
     */
    private static void terimaPesanan() {
        System.out.println("\nSilakan masukkan nama menu yang ingin dipesan.");
        System.out.println("(Ketik 'selesai' untuk menyelesaikan pesanan)");

        String inputMenu;
        while (true) { // Loop tidak terbatas
            System.out.print("Pesanan: ");
            inputMenu = scanner.nextLine();

            // 1. Kondisi Berhenti: Jika input 'selesai'
            if (inputMenu.equalsIgnoreCase("selesai")) {
                break; // 'break' akan keluar dari loop 'while(true)'
            }

            // 2. Validasi Input: Cari menu di 'database' kita
            Menu itemDipesan = cariMenu(inputMenu);

            // 3. Struktur Keputusan: if-else
            if (itemDipesan != null) {
                // Jika ketemu, tambahkan ke ArrayList 'pesanan'
                pesanan.add(itemDipesan);
                System.out.println("Berhasil menambahkan: " + itemDipesan.getNama());
            } else {
                // Jika tidak ketemu (null), cetak error
                // Sesuai soal: "sistem akan terus meminta input kembali"
                // Karena kita tidak 'break', loop 'while' akan otomatis
                // mengulang dan meminta input lagi.
                System.out.println("Menu tidak ditemukan. Silakan coba lagi.");
            }
        }
    }

    /**
     * Method 'helper' untuk mencari objek Menu berdasarkan nama (String).
     * Kita pakai 'equalsIgnoreCase' agar input tidak case-sensitive
     * (misal: "nasi goreng" atau "Nasi Goreng" sama-sama valid).
     */
    private static Menu cariMenu(String namaMenu) {
        for (Menu item : daftarMenu) {
            if (item.getNama().equalsIgnoreCase(namaMenu)) {
                return item; // Kembalikan objek Menu jika ketemu
            }
        }
        return null; // Kembalikan 'null' jika tidak ketemu
    }

    /**
     * Method untuk menghitung total biaya dan mencetak struk.
     * Ini adalah tempat utama untuk 'Struktur Keputusan'.
     */
    private static void hitungDanCetakStruk() {
        if (pesanan.isEmpty()) {
            System.out.println("Anda belum memesan apapun.");
            return; // Keluar dari method
        }

        double subTotal = 0;
        Menu minumanGratis = null; // Untuk menyimpan kandidat B1G1

        // --- KENAPA PAKAI MAP? ---
        // 'ArrayList pesanan' kita mungkin berisi:
        // [Es Teh, Nasi Goreng, Es Teh, Es Teh]
        // Kita perlu mengubahnya menjadi:
        // {Es Teh: 3, Nasi Goreng: 1}
        // 'Map' adalah struktur data yang sempurna untuk ini.
        Map<String, Integer> jumlahItem = new HashMap<>(); // Key: Nama, Value: Jumlah
        Map<String, Menu> itemMap = new HashMap<>();      // Key: Nama, Value: Objek Menu

        // --- Loop 1: Menghitung Subtotal dan Kuantitas ---
        for (Menu item : pesanan) {
            // 1. Tambah harga ke subTotal
            subTotal += item.getHarga();
            
            // 2. Hitung kuantitas di Map
            // 'getOrDefault' artinya: "Ambil jumlah saat ini, atau 0 jika belum ada"
            jumlahItem.put(item.getNama(), jumlahItem.getOrDefault(item.getNama(), 0) + 1);
            
            // 3. Simpan objek Menu-nya (agar tidak duplikat)
            itemMap.putIfAbsent(item.getNama(), item);

            // 4. Logika untuk promo B1G1 (Beli 1 Gratis 1 Minuman)
            // Sesuai soal: "salah satu kategori minuman"
            // Kita tentukan saja gratis minuman TERMURAH yang dipesan.
            if (item.getKategori().equalsIgnoreCase("Minuman")) {
                if (minumanGratis == null || item.getHarga() < minumanGratis.getHarga()) {
                    minumanGratis = item; // Update jika ini minuman termurah
                }
            }
        }

        // --- Inisialisasi Biaya Tambahan ---
        double pajak = subTotal * 0.10; // Pajak 10%
        double biayaPelayanan = 20000;
        double diskon = 0;
        double nilaiMinumanGratis = 0;

        // --- STRUKTUR KEPUTUSAN (Diskon & Promo) ---
        // Sesuai soal, kita implementasikan skenario-skenario ini.
        
        // Skenario 1: Diskon 10% jika total > 100.000
        if (subTotal > 100000) {
            diskon = subTotal * 0.10; // Hitung diskon
        }
        
        // Skenario 2: Promo B1G1 jika total > 50.000
        if (subTotal > 50000 && minumanGratis != null) {
            // 'minumanGratis' adalah minuman termurah yg kita temukan tadi
            nilaiMinumanGratis = minumanGratis.getHarga();
        }
        
        // Hitung total akhir
        double totalAkhir = subTotal - diskon - nilaiMinumanGratis + pajak + biayaPelayanan;

        // --- Cetak Struk ---
        System.out.println("\n\n--- STRUK PEMBAYARAN ---");
        // Loop 'Map' untuk mencetak detail item
        for (String namaItem : jumlahItem.keySet()) {
            Menu item = itemMap.get(namaItem);
            int qty = jumlahItem.get(namaItem);
            double totalPerItem = item.getHarga() * qty;
            System.out.printf("- %-20s x%d (Rp %,.0f) = Rp %,.0f%n",
                    item.getNama(), qty, item.getHarga(), totalPerItem);
        }
        System.out.println("----------------------------------------");
        System.out.printf("Subtotal              : Rp %,.0f%n", subTotal);
        System.out.printf("Pajak (10%%)           : Rp %,.0f%n", pajak);
        System.out.printf("Biaya Pelayanan       : Rp %,.0f%n", biayaPelayanan);
        
        // Cetak informasi diskon HANYA JIKA ADA
        System.out.println("--- Diskon & Penawaran ---");
        if (diskon == 0 && nilaiMinumanGratis == 0) {
            System.out.println("Tidak ada diskon yang berlaku.");
        }
        if (diskon > 0) {
            System.out.printf("Diskon (10%% > 100k)   : -Rp %,.0f%n", diskon);
        }
        if (nilaiMinumanGratis > 0) {
            System.out.printf("Promo B1G1 (%s) : -Rp %,.0f%n", minumanGratis.getNama(), nilaiMinumanGratis);
        }
        System.out.println("----------------------------------------");
        System.out.printf("TOTAL AKHIR           : Rp %,.0f%n", totalAkhir);
        System.out.println("--- Terima Kasih atas Kunjungan Anda ---");
    }


    // =================================================================
    // == BAGIAN MENU ADMIN
    // =================================================================

    /**
     * Method untuk navigasi menu Admin.
     * Kita pakai 'struktur pengulangan' 'while' agar
     * admin bisa kembali ke menu ini setelah (misal) menambah data.
     */
    private static void menuAdmin() {
        boolean kembali = false;
        while (!kembali) {
            System.out.println("\n--- Menu Pengelolaan Restoran ---");
            System.out.println("1. Tambah Menu Baru");
            System.out.println("2. Ubah Harga Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilih (1-4): ");

            String pilihan = scanner.nextLine();
            switch (pilihan) {
                case "1":
                    tambahMenu();
                    break;
                case "2":
                    ubahMenu();
                    break;
                case "3":
                    hapusMenu();
                    break;
                case "4":
                    kembali = true; // Ini akan menghentikan loop 'while'
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    /**
     * Method 'helper' untuk menampilkan daftar menu bernomor.
     * Ini digunakan untuk Ubah dan Hapus.
     */
    private static void tampilkanMenuAdmin() {
        System.out.println("\n--- Daftar Menu Saat Ini ---");
        if (daftarMenu.isEmpty()) {
            System.out.println("Menu kosong.");
            return;
        }
        // Kita pakai loop 'for' biasa (bukan for-each)
        // agar kita bisa mendapatkan 'index' (i) untuk penomoran.
        for (int i = 0; i < daftarMenu.size(); i++) {
            Menu item = daftarMenu.get(i);
            System.out.printf("%d. %s (%s) - Rp %,.0f%n",
                    (i + 1), item.getNama(), item.getKategori(), item.getHarga());
        }
        System.out.println("---------------------------");
    }

    /**
     * Method untuk menambah menu baru.
     * Sesuai soal: "menambahkan beberapa menu baru sekaligus".
     * Kita gunakan 'struktur pengulangan' 'do-while'.
     */
    private static void tambahMenu() {
        String tambahLagi;
        do {
            System.out.println("\n--- Tambah Menu Baru ---");
            System.out.print("Nama Menu: ");
            String nama = scanner.nextLine();
            
            // --- Validasi Input Harga (HARUS ANGKA) ---
            double harga = 0;
            boolean hargaValid = false;
            while (!hargaValid) {
                try {
                    System.out.print("Harga Menu (angka): ");
                    // 'Double.parseDouble' akan error jika input bukan angka
                    harga = Double.parseDouble(scanner.nextLine()); 
                    if (harga < 0) {
                         System.out.println("Harga tidak boleh negatif.");
                    } else {
                        hargaValid = true; // Sukses, keluar loop
                    }
                } catch (NumberFormatException e) {
                    // 'catch' akan menangkap error jika input bukan angka
                    System.out.println("Input harga tidak valid, masukkan angka.");
                }
            }

            // --- Validasi Input Kategori ---
            String kategori;
            while (true) {
                System.out.print("Kategori (Makanan / Minuman): ");
                kategori = scanner.nextLine();
                if (kategori.equalsIgnoreCase("Makanan") || kategori.equalsIgnoreCase("Minuman")) {
                    break; // Input valid, keluar loop
                } else {
                    System.out.println("Kategori tidak valid. Harap masukkan 'Makanan' atau 'Minuman'.");
                }
            }

            // Tambahkan ke 'database' ArrayList kita
            daftarMenu.add(new Menu(nama, harga, kategori));
            System.out.println("Menu '" + nama + "' berhasil ditambahkan.");

            System.out.print("Tambah menu lagi? (ya/tidak): ");
            tambahLagi = scanner.nextLine();
        } while (tambahLagi.equalsIgnoreCase("ya")); // Loop selama admin ketik 'ya'
    }
    
    /**
     * Method 'helper' untuk meminta input nomor menu dari Admin.
     * Ini melakukan validasi agar nomornya valid (antara 1 s.d. ukuran daftarMenu).
     */
    private static int getNomorMenuValid(String prompt) {
        int nomor = -1;
        while (nomor < 1 || nomor > daftarMenu.size()) {
            try {
                System.out.print(prompt + " (1-" + daftarMenu.size() + "): ");
                nomor = Integer.parseInt(scanner.nextLine());
                if (nomor < 1 || nomor > daftarMenu.size()) {
                    System.out.println("Nomor tidak valid. Silakan pilih dari daftar.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka.");
            }
        }
        return nomor; // Kembalikan nomor yang SUDAH PASTI VALID
    }

    /**
     * Method untuk mengubah harga menu.
     * Sesuai soal: "menampilkan daftar", "menginput nomor", "konfirmasi 'Ya'".
     */
    private static void ubahMenu() {
        // 1. Tampilkan daftar
        tampilkanMenuAdmin();
        if (daftarMenu.isEmpty()) return; // Keluar jika menu kosong

        // 2. Minta input nomor
        int nomor = getNomorMenuValid("Masukkan nomor menu yang akan diubah");
        int index = nomor - 1; // Konversi nomor (mulai dari 1) ke index (mulai dari 0)
        Menu item = daftarMenu.get(index);

        System.out.println("Mengubah: " + item.getNama());
        
        // (Validasi harga baru, sama seperti di 'tambahMenu')
        double hargaBaru = 0;
        boolean hargaValid = false;
        while (!hargaValid) {
            try {
                System.out.print("Masukkan harga baru: ");
                hargaBaru = Double.parseDouble(scanner.nextLine());
                 if (hargaBaru < 0) {
                    System.out.println("Harga tidak boleh negatif.");
                } else {
                    hargaValid = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Input harga tidak valid, masukkan angka.");
            }
        }

        // 3. Konfirmasi 'Ya'
        System.out.print("Anda yakin ingin mengubah harga? (Ya/Tidak): ");
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("Ya")) {
            // Kita panggil method 'setter' dari Kelas Menu
            item.setHarga(hargaBaru);
            System.out.println("Harga berhasil diubah.");
        } else {
            System.out.println("Perubahan dibatalkan.");
        }
    }

    /**
     * Method untuk menghapus menu.
     * Sesuai soal: "menampilkan daftar", "menginput nomor", "konfirmasi 'Ya'".
     */
    private static void hapusMenu() {
        // 1. Tampilkan daftar
        tampilkanMenuAdmin();
        if (daftarMenu.isEmpty()) return; // Keluar jika menu kosong

        // 2. Minta input nomor
        int nomor = getNomorMenuValid("Masukkan nomor menu yang akan dihapus");
        int index = nomor - 1; // Konversi ke index
        Menu item = daftarMenu.get(index);

        // 3. Konfirmasi 'Ya'
        System.out.print("Anda yakin ingin menghapus '" + item.getNama() + "'? (Ya/Tidak): ");
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("Ya")) {
            // Ini adalah kekuatan ArrayList:
            // Kita bisa hapus elemen berdasarkan index-nya.
            daftarMenu.remove(index);
            System.out.println("Menu berhasil dihapus.");
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }
}