import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Kelas Utama (Main.java)
 * Mengelola seluruh logika aplikasi restoran, termasuk pemesanan dan manajemen
 * menu.
 */
public class Main {

    // 'static' agar bisa diakses di semua method dalam kelas Main
    private static ArrayList<Menu> daftarMenu = new ArrayList<>();
    private static ArrayList<Menu> pesanan = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Method main - Titik awal program
     */
    public static void main(String[] args) {
        // Mengisi data menu awal
        inisialisasiMenu();

        boolean berjalan = true;
        while (berjalan) {
            System.out.println("\n--- Aplikasi Restoran Sederhana ---");
            System.out.println("1. Menu Pelanggan (Pesan)");
            System.out.println("2. Menu Admin (Kelola Menu)");
            System.out.println("3. Keluar");
            System.out.print("Pilih menu (1-3): ");

            String pilihan = scanner.nextLine();
            switch (pilihan) {
                case "1":
                    menuPelanggan();
                    break;
                case "2":
                    menuAdmin();
                    break;
                case "3":
                    berjalan = false;
                    System.out.println("Terima kasih telah menggunakan aplikasi.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
        scanner.close();
    }

    /**
     * Mengisi daftar menu dengan data awal.
     * Minimal 4 makanan dan 4 minuman.
     */
    private static void inisialisasiMenu() {
        // Makanan
        daftarMenu.add(new Menu("Nasi Goreng", 25000, "Makanan"));
        daftarMenu.add(new Menu("Mie Ayam", 20000, "Makanan"));
        daftarMenu.add(new Menu("Soto Betawi", 35000, "Makanan"));
        daftarMenu.add(new Menu("Gado-Gado", 22000, "Makanan"));
        // Minuman
        daftarMenu.add(new Menu("Es Teh Manis", 8000, "Minuman"));
        daftarMenu.add(new Menu("Es Jeruk", 10000, "Minuman"));
        daftarMenu.add(new Menu("Jus Alpukat", 15000, "Minuman"));
        daftarMenu.add(new Menu("Kopi Susu", 18000, "Minuman"));
    }

    // =================================================================
    // == BAGIAN MENU PELANGGAN
    // =================================================================

    /**
     * Menampilkan menu utama untuk pelanggan.
     */
    private static void menuPelanggan() {
        System.out.println("\n--- Selamat Datang di Restoran Kami ---");
        tampilkanMenuPelanggan();
        terimaPesanan();
        if (!pesanan.isEmpty()) {
            hitungDanCetakStruk();
            pesanan.clear(); // Kosongkan pesanan setelah struk dicetak
        }
    }

    /**
     * Menampilkan daftar menu yang dikelompokkan berdasarkan kategori.
     */
    private static void tampilkanMenuPelanggan() {
        System.out.println("\n--- DAFTAR MENU ---");

        // Menampilkan Makanan
        System.out.println("Kategori: Makanan");
        int nomor = 1;
        for (Menu item : daftarMenu) {
            if (item.getKategori().equalsIgnoreCase("Makanan")) {
                System.out.printf("%d. %-20s - Rp %,.0f%n",
                        nomor++, item.getNama(), item.getHarga());
            }
        }

        // Menampilkan Minuman
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
     * Menerima input pesanan dari pelanggan.
     * Loop berlanjut hingga pelanggan mengetik 'selesai'.
     */
    private static void terimaPesanan() {
        System.out.println("\nSilakan masukkan nama menu yang ingin dipesan.");
        System.out.println("(Ketik 'selesai' untuk menyelesaikan pesanan)");

        String inputMenu;
        while (true) {
            System.out.print("Pesanan: ");
            inputMenu = scanner.nextLine();

            if (inputMenu.equalsIgnoreCase("selesai")) {
                break;
            }

            // Cari menu di daftarMenu
            Menu itemDipesan = cariMenu(inputMenu);

            if (itemDipesan != null) {
                pesanan.add(itemDipesan); // Tambahkan ke list pesanan
                System.out.println("Berhasil menambahkan: " + itemDipesan.getNama());
            } else {
                System.out.println("Menu tidak ditemukan. Silakan coba lagi.");
            }
        }
    }

    /**
     * Helper method untuk mencari menu berdasarkan nama.
     * Menggunakan equalsIgnoreCase untuk case-insensitive.
     */
    private static Menu cariMenu(String namaMenu) {
        for (Menu item : daftarMenu) {
            if (item.getNama().equalsIgnoreCase(namaMenu)) {
                return item;
            }
        }
        return null; // Return null jika tidak ditemukan
    }

    /**
     * Menghitung total biaya dan mencetak struk.
     */
    private static void hitungDanCetakStruk() {
        if (pesanan.isEmpty()) {
            System.out.println("Anda belum memesan apapun.");
            return;
        }

        double subTotal = 0;
        double diskon = 0;
        double nilaiMinumanGratis = 0;
        Menu minumanGratis = null;

        // Map untuk menghitung jumlah (quantity) setiap item
        Map<String, Integer> jumlahItem = new HashMap<>();
        Map<String, Menu> itemMap = new HashMap<>();

        // Loop 1: Hitung subtotal dan data untuk struk
        for (Menu item : pesanan) {
            subTotal += item.getHarga();

            // Menghitung kuantitas
            jumlahItem.put(item.getNama(), jumlahItem.getOrDefault(item.getNama(), 0) + 1);
            itemMap.putIfAbsent(item.getNama(), item);

            // Cari minuman termurah untuk promo B1G1
            if (item.getKategori().equalsIgnoreCase("Minuman")) {
                if (minumanGratis == null || item.getHarga() < minumanGratis.getHarga()) {
                    minumanGratis = item;
                }
            }
        }

        // Inisialisasi biaya
        double pajak = subTotal * 0.10; // Pajak 10%
        double biayaPelayanan = 20000;

        // Terapkan penawaran/diskon
        boolean adaDiskon10 = false;
        boolean adaB1G1 = false;

        if (subTotal > 100000) {
            diskon = subTotal * 0.10; // Diskon 10%
            adaDiskon10 = true;
        }

        if (subTotal > 50000 && minumanGratis != null) {
            nilaiMinumanGratis = minumanGratis.getHarga(); // Gratis minuman termurah
            adaB1G1 = true;
        }

        // Hitung total akhir
        double totalAkhir = subTotal - diskon - nilaiMinumanGratis + pajak + biayaPelayanan;

        // Cetak Struk
        System.out.println("\n\n--- STRUK PEMBAYARAN ---");
        System.out.println("Item yang Dipesan:");
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

        System.out.println("--- Diskon & Penawaran ---");
        if (!adaDiskon10 && !adaB1G1) {
            System.out.println("Tidak ada diskon yang berlaku.");
        }
        if (adaDiskon10) {
            System.out.printf("Diskon (10%% > 100k)   : -Rp %,.0f%n", diskon);
        }
        if (adaB1G1) {
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
     * Menampilkan menu utama untuk admin (pengelolaan menu).
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
                    kembali = true;
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    /**
     * Menampilkan daftar menu bernomor untuk admin (ubah/hapus).
     */
    private static void tampilkanMenuAdmin() {
        System.out.println("\n--- Daftar Menu Saat Ini ---");
        if (daftarMenu.isEmpty()) {
            System.out.println("Menu kosong.");
            return;
        }
        for (int i = 0; i < daftarMenu.size(); i++) {
            Menu item = daftarMenu.get(i);
            System.out.printf("%d. %s (%s) - Rp %,.0f%n",
                    (i + 1), item.getNama(), item.getKategori(), item.getHarga());
        }
        System.out.println("---------------------------");
    }

    /**
     * Logika untuk menambah menu baru.
     * Menggunakan loop untuk "menambahkan beberapa menu sekaligus".
     */
    private static void tambahMenu() {
        String tambahLagi;
        do {
            System.out.println("\n--- Tambah Menu Baru ---");
            System.out.print("Nama Menu: ");
            String nama = scanner.nextLine();

            double harga = 0;
            boolean hargaValid = false;
            while (!hargaValid) {
                try {
                    System.out.print("Harga Menu (angka): ");
                    harga = Double.parseDouble(scanner.nextLine());
                    if (harga < 0) {
                        System.out.println("Harga tidak boleh negatif.");
                    } else {
                        hargaValid = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input harga tidak valid, masukkan angka.");
                }
            }

            String kategori;
            while (true) {
                System.out.print("Kategori (Makanan / Minuman): ");
                kategori = scanner.nextLine();
                if (kategori.equalsIgnoreCase("Makanan") || kategori.equalsIgnoreCase("Minuman")) {
                    break;
                } else {
                    System.out.println("Kategori tidak valid. Harap masukkan 'Makanan' atau 'Minuman'.");
                }
            }

            // Konfirmasi penambahan
            daftarMenu.add(new Menu(nama, harga, kategori));
            System.out.println("Menu '" + nama + "' berhasil ditambahkan.");

            System.out.print("Tambah menu lagi? (ya/tidak): ");
            tambahLagi = scanner.nextLine();
        } while (tambahLagi.equalsIgnoreCase("ya"));
    }

    /**
     * Helper untuk mendapatkan input nomor menu yang valid dari admin.
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
        return nomor;
    }

    /**
     * Logika untuk mengubah harga menu.
     */
    private static void ubahMenu() {
        tampilkanMenuAdmin();
        if (daftarMenu.isEmpty())
            return; // Keluar jika tidak ada menu

        int nomor = getNomorMenuValid("Masukkan nomor menu yang akan diubah");
        int index = nomor - 1; // Konversi ke index array (0-based)
        Menu item = daftarMenu.get(index);

        System.out.println("Mengubah: " + item.getNama());

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

        System.out.print("Anda yakin ingin mengubah harga? (Ya/Tidak): ");
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("Ya")) {
            item.setHarga(hargaBaru);
            System.out.println("Harga berhasil diubah.");
        } else {
            System.out.println("Perubahan dibatalkan.");
        }
    }

    /**
     * Logika untuk menghapus menu.
     */
    private static void hapusMenu() {
        tampilkanMenuAdmin();
        if (daftarMenu.isEmpty())
            return; // Keluar jika tidak ada menu

        int nomor = getNomorMenuValid("Masukkan nomor menu yang akan dihapus");
        int index = nomor - 1; // Konversi ke index array (0-based)
        Menu item = daftarMenu.get(index);

        System.out.print("Anda yakin ingin menghapus '" + item.getNama() + "'? (Ya/Tidak): ");
        String konfirmasi = scanner.nextLine();

        if (konfirmasi.equalsIgnoreCase("Ya")) {
            daftarMenu.remove(index);
            System.out.println("Menu berhasil dihapus.");
        } else {
            System.out.println("Penghapusan dibatalkan.");
        }
    }
}
