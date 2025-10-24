import java.util.Scanner; // Pastikan ini ada di paling atas

/**
 * Kelas Main (Program Utama)
 */
public class Main {

    // --- PINDAH ---
    // Variabel ini dipindah ke luar 'main' agar bisa diakses
    // oleh method 'editMenuAdmin' dan 'tampilkanMenu'.
    static Menu[] daftarMenu = new Menu[8];
    static Scanner input = new Scanner(System.in);

    /**
     * Method untuk menampilkan daftar menu.
     * --- UBAH ---
     * Parameter (Menu[] daftarMenu) dihapus karena
     * method ini sekarang membaca variabel 'static daftarMenu' di atas.
     */
    public static void tampilkanMenu() {
        System.out.println("===== Selamat Datang di Restoran Sederhana =====");
        System.out.println("Daftar Menu Kami:");

        System.out.println("\n--- Makanan ---");
        System.out.println("1. " + daftarMenu[0].nama + "\t\t - Rp " + daftarMenu[0].harga);
        System.out.println("2. " + daftarMenu[1].nama + "\t\t - Rp " + daftarMenu[1].harga);
        System.out.println("3. " + daftarMenu[2].nama + "\t\t - Rp " + daftarMenu[2].harga);
        System.out.println("4. " + daftarMenu[3].nama + "\t\t - Rp " + daftarMenu[3].harga);

        System.out.println("\n--- Minuman ---");
        System.out.println("5. " + daftarMenu[4].nama + "\t\t - Rp " + daftarMenu[4].harga);
        System.out.println("6. " + daftarMenu[5].nama + "\t\t - Rp " + daftarMenu[5].harga);
        System.out.println("7. " + daftarMenu[6].nama + "\t\t - Rp " + daftarMenu[6].harga);
        System.out.println("8. " + daftarMenu[7].nama + "\t\t - Rp " + daftarMenu[7].harga);
        System.out.println("==================================================");
    }

    /**
     * --- BARU ---
     * METHOD BARU UNTUK ADMIN MENGEDIT MENU
     * Ini adalah method tambahkan.
     */
    public static void editMenuAdmin() {
        System.out.println("\n===== MODE ADMIN: EDIT MENU =====");
        System.out.println("Pilih nomor menu yang ingin diedit (1-8):");
        System.out.print("Masukkan nomor (1-8): ");
        int pilihan = input.nextInt();
        input.nextLine(); // Membersihkan newline buffer

        System.out.print("Masukkan NAMA BARU menu: ");
        String namaBaru = input.nextLine();
        System.out.print("Masukkan HARGA BARU menu: ");
        double hargaBaru = input.nextDouble();
        input.nextLine(); // Membersihkan newline buffer

        String kategoriLama; // Variabel untuk menyimpan kategori

        // Menggunakan Switch Case (Struktur Keputusan)
        switch (pilihan) {
            case 1:
                kategoriLama = daftarMenu[0].kategori;
                daftarMenu[0] = new Menu(namaBaru, hargaBaru, kategoriLama);
                break;
            case 2:
                kategoriLama = daftarMenu[1].kategori;
                daftarMenu[1] = new Menu(namaBaru, hargaBaru, kategoriLama);
                break;
            case 3:
                kategoriLama = daftarMenu[2].kategori;
                daftarMenu[2] = new Menu(namaBaru, hargaBaru, kategoriLama);
                break;
            case 4:
                kategoriLama = daftarMenu[3].kategori;
                daftarMenu[3] = new Menu(namaBaru, hargaBaru, kategoriLama);
                break;
            case 5:
                kategoriLama = daftarMenu[4].kategori;
                daftarMenu[4] = new Menu(namaBaru, hargaBaru, kategoriLama);
                break;
            case 6:
                kategoriLama = daftarMenu[5].kategori;
                daftarMenu[5] = new Menu(namaBaru, hargaBaru, kategoriLama);
                break;
            case 7:
                kategoriLama = daftarMenu[6].kategori;
                daftarMenu[6] = new Menu(namaBaru, hargaBaru, kategoriLama);
                break;
            case 8:
                kategoriLama = daftarMenu[7].kategori;
                daftarMenu[7] = new Menu(namaBaru, hargaBaru, kategoriLama);
                break;
            default:
                System.out.println("Pilihan tidak valid. Tidak ada menu yang diubah.");
                break;
        }

        System.out.println("Menu berhasil diperbarui!");
        System.out.println("====================================");
    }

    /**
     * Method utama (main)
     * Tempat alur program dieksekusi.
     */
    public static void main(String[] args) {

        // --- 1. Input Menu Restoran (Inisialisasi Array) ---
        // Ini tetap sama, mengisi data awal ke variabel 'static'
        daftarMenu[0] = new Menu("Nasi Padang", 25000.0, "Makanan");
        daftarMenu[1] = new Menu("Sate Ayam", 30000.0, "Makanan");
        daftarMenu[2] = new Menu("Gado-Gado", 20000.0, "Makanan");
        daftarMenu[3] = new Menu("Bakso", 22000.0, "Makanan");
        daftarMenu[4] = new Menu("Es Teh Manis", 8000.0, "Minuman");
        daftarMenu[5] = new Menu("Jus Alpukat", 15000.0, "Minuman");
        daftarMenu[6] = new Menu("Kopi Susu", 18000.0, "Minuman");
        daftarMenu[7] = new Menu("Es Jeruk", 10000.0, "Minuman");

        // --- Bagian untuk Admin ---
        System.out.println("--- Menu Awal ---");
        tampilkanMenu(); // Menampilkan menu awal

        System.out.print("\nApakah Anda ingin mengedit menu (admin)? (y/n): ");
        String isAdmin = input.nextLine();

        // Struktur keputusan 'if' untuk menjalankan mode admin
        if (isAdmin.equalsIgnoreCase("y")) {
            editMenuAdmin(); // Panggil method edit
        }
        // --- Akhir Bagian Admin ---

        // --- Tampilkan Menu Final ---
        // Menampilkan menu lagi, siapa tahu tadi diubah oleh admin
        System.out.println("\n--- Menu Final Untuk Pelanggan ---");
        tampilkanMenu();

        // --- 2. Pemesanan (Input) ---
        System.out.println("\nSilakan masukkan pesanan Anda (Maks. 4 jenis menu):");
        System.out.println("(Kosongkan nama menu jika sudah selesai memesan)");

        System.out.print("Pesanan 1 (Nama Menu): ");
        String pesanan1 = input.nextLine();
        System.out.print("Jumlah 1: ");
        int jumlah1 = input.nextInt();
        input.nextLine(); // Membersihkan newline buffer

        System.out.print("Pesanan 2 (Nama Menu): ");
        String pesanan2 = input.nextLine();
        int jumlah2 = 0;
        if (!pesanan2.isEmpty()) {
            System.out.print("Jumlah 2: ");
            jumlah2 = input.nextInt();
            input.nextLine();
        }

        System.out.print("Pesanan 3 (Nama Menu): ");
        String pesanan3 = input.nextLine();
        int jumlah3 = 0;
        if (!pesanan3.isEmpty()) {
            System.out.print("Jumlah 3: ");
            jumlah3 = input.nextInt();
            input.nextLine();
        }

        System.out.print("Pesanan 4 (Nama Menu): ");
        String pesanan4 = input.nextLine();
        int jumlah4 = 0;
        if (!pesanan4.isEmpty()) {
            System.out.print("Jumlah 4: ");
            jumlah4 = input.nextInt();
            input.nextLine();
        }

        // --- 3. Menghitung Total Biaya (Processing) ---

        double hargaItem1 = 0, hargaItem2 = 0, hargaItem3 = 0, hargaItem4 = 0;
        double totalItem1 = 0, totalItem2 = 0, totalItem3 = 0, totalItem4 = 0;
        String kategori1 = "", kategori2 = "", kategori3 = "", kategori4 = "";

        // Proses Pesanan 1
        if (pesanan1.equalsIgnoreCase(daftarMenu[0].nama)) {
            hargaItem1 = daftarMenu[0].harga;
            kategori1 = daftarMenu[0].kategori;
        } else if (pesanan1.equalsIgnoreCase(daftarMenu[1].nama)) {
            hargaItem1 = daftarMenu[1].harga;
            kategori1 = daftarMenu[1].kategori;
        } else if (pesanan1.equalsIgnoreCase(daftarMenu[2].nama)) {
            hargaItem1 = daftarMenu[2].harga;
            kategori1 = daftarMenu[2].kategori;
        } else if (pesanan1.equalsIgnoreCase(daftarMenu[3].nama)) {
            hargaItem1 = daftarMenu[3].harga;
            kategori1 = daftarMenu[3].kategori;
        } else if (pesanan1.equalsIgnoreCase(daftarMenu[4].nama)) {
            hargaItem1 = daftarMenu[4].harga;
            kategori1 = daftarMenu[4].kategori;
        } else if (pesanan1.equalsIgnoreCase(daftarMenu[5].nama)) {
            hargaItem1 = daftarMenu[5].harga;
            kategori1 = daftarMenu[5].kategori;
        } else if (pesanan1.equalsIgnoreCase(daftarMenu[6].nama)) {
            hargaItem1 = daftarMenu[6].harga;
            kategori1 = daftarMenu[6].kategori;
        } else if (pesanan1.equalsIgnoreCase(daftarMenu[7].nama)) {
            hargaItem1 = daftarMenu[7].harga;
            kategori1 = daftarMenu[7].kategori;
        } else {
            jumlah1 = 0;
        }
        totalItem1 = hargaItem1 * jumlah1;

        // Proses Pesanan 2
        if (pesanan2.equalsIgnoreCase(daftarMenu[0].nama)) {
            hargaItem2 = daftarMenu[0].harga;
            kategori2 = daftarMenu[0].kategori;
        } else if (pesanan2.equalsIgnoreCase(daftarMenu[1].nama)) {
            hargaItem2 = daftarMenu[1].harga;
            kategori2 = daftarMenu[1].kategori;
        } else if (pesanan2.equalsIgnoreCase(daftarMenu[2].nama)) {
            hargaItem2 = daftarMenu[2].harga;
            kategori2 = daftarMenu[2].kategori;
        } else if (pesanan2.equalsIgnoreCase(daftarMenu[3].nama)) {
            hargaItem2 = daftarMenu[3].harga;
            kategori2 = daftarMenu[3].kategori;
        } else if (pesanan2.equalsIgnoreCase(daftarMenu[4].nama)) {
            hargaItem2 = daftarMenu[4].harga;
            kategori2 = daftarMenu[4].kategori;
        } else if (pesanan2.equalsIgnoreCase(daftarMenu[5].nama)) {
            hargaItem2 = daftarMenu[5].harga;
            kategori2 = daftarMenu[5].kategori;
        } else if (pesanan2.equalsIgnoreCase(daftarMenu[6].nama)) {
            hargaItem2 = daftarMenu[6].harga;
            kategori2 = daftarMenu[6].kategori;
        } else if (pesanan2.equalsIgnoreCase(daftarMenu[7].nama)) {
            hargaItem2 = daftarMenu[7].harga;
            kategori2 = daftarMenu[7].kategori;
        } else {
            jumlah2 = 0;
        }
        totalItem2 = hargaItem2 * jumlah2;

        // Proses Pesanan 3
        if (pesanan3.equalsIgnoreCase(daftarMenu[0].nama)) {
            hargaItem3 = daftarMenu[0].harga;
            kategori3 = daftarMenu[0].kategori;
        } else if (pesanan3.equalsIgnoreCase(daftarMenu[1].nama)) {
            hargaItem3 = daftarMenu[1].harga;
            kategori3 = daftarMenu[1].kategori;
        } else if (pesanan3.equalsIgnoreCase(daftarMenu[2].nama)) {
            hargaItem3 = daftarMenu[2].harga;
            kategori3 = daftarMenu[2].kategori;
        } else if (pesanan3.equalsIgnoreCase(daftarMenu[3].nama)) {
            hargaItem3 = daftarMenu[3].harga;
            kategori3 = daftarMenu[3].kategori;
        } else if (pesanan3.equalsIgnoreCase(daftarMenu[4].nama)) {
            hargaItem3 = daftarMenu[4].harga;
            kategori3 = daftarMenu[4].kategori;
        } else if (pesanan3.equalsIgnoreCase(daftarMenu[5].nama)) {
            hargaItem3 = daftarMenu[5].harga;
            kategori3 = daftarMenu[5].kategori;
        } else if (pesanan3.equalsIgnoreCase(daftarMenu[6].nama)) {
            hargaItem3 = daftarMenu[6].harga;
            kategori3 = daftarMenu[6].kategori;
        } else if (pesanan3.equalsIgnoreCase(daftarMenu[7].nama)) {
            hargaItem3 = daftarMenu[7].harga;
            kategori3 = daftarMenu[7].kategori;
        } else {
            jumlah3 = 0;
        }
        totalItem3 = hargaItem3 * jumlah3;

        // Proses Pesanan 4
        if (pesanan4.equalsIgnoreCase(daftarMenu[0].nama)) {
            hargaItem4 = daftarMenu[0].harga;
            kategori4 = daftarMenu[0].kategori;
        } else if (pesanan4.equalsIgnoreCase(daftarMenu[1].nama)) {
            hargaItem4 = daftarMenu[1].harga;
            kategori4 = daftarMenu[1].kategori;
        } else if (pesanan4.equalsIgnoreCase(daftarMenu[2].nama)) {
            hargaItem4 = daftarMenu[2].harga;
            kategori4 = daftarMenu[2].kategori;
        } else if (pesanan4.equalsIgnoreCase(daftarMenu[3].nama)) {
            hargaItem4 = daftarMenu[3].harga;
            kategori4 = daftarMenu[3].kategori;
        } else if (pesanan4.equalsIgnoreCase(daftarMenu[4].nama)) {
            hargaItem4 = daftarMenu[4].harga;
            kategori4 = daftarMenu[4].kategori;
        } else if (pesanan4.equalsIgnoreCase(daftarMenu[5].nama)) {
            hargaItem4 = daftarMenu[5].harga;
            kategori4 = daftarMenu[5].kategori;
        } else if (pesanan4.equalsIgnoreCase(daftarMenu[6].nama)) {
            hargaItem4 = daftarMenu[6].harga;
            kategori4 = daftarMenu[6].kategori;
        } else if (pesanan4.equalsIgnoreCase(daftarMenu[7].nama)) {
            hargaItem4 = daftarMenu[7].harga;
            kategori4 = daftarMenu[7].kategori;
        } else {
            jumlah4 = 0;
        }
        totalItem4 = hargaItem4 * jumlah4;

        // --- Kalkulasi Total ---

        double subTotal = totalItem1 + totalItem2 + totalItem3 + totalItem4;
        double diskon = 0;
        double bogoDiscountValue = 0;
        String catatanDiskon = "";
        String catatanBOGO = "";
        double biayaPelayanan = 20000.0;

        if (subTotal > 100000.0) {
            diskon = subTotal * 0.10;
            catatanDiskon = "Diskon 10% (Pembelian > 100rb)";
        } else {
            diskon = 0;
        }

        if (subTotal > 50000.0) {
            double minHargaMinuman = Double.MAX_VALUE;

            if (kategori1.equals("Minuman") && jumlah1 > 0) {
                minHargaMinuman = hargaItem1;
            }
            if (kategori2.equals("Minuman") && jumlah2 > 0 && hargaItem2 < minHargaMinuman) {
                minHargaMinuman = hargaItem2;
            }
            if (kategori3.equals("Minuman") && jumlah3 > 0 && hargaItem3 < minHargaMinuman) {
                minHargaMinuman = hargaItem3;
            }
            if (kategori4.equals("Minuman") && jumlah4 > 0 && hargaItem4 < minHargaMinuman) {
                minHargaMinuman = hargaItem4;
            }

            if (minHargaMinuman != Double.MAX_VALUE) {
                bogoDiscountValue = minHargaMinuman;
                catatanBOGO = "Promo BOGO (Gratis 1 Minuman Termurah)";
            } else {
                catatanBOGO = "Promo BOGO (Tidak ada minuman dipesan)";
            }
        } else {
            catatanBOGO = "Promo BOGO (Pembelian < 50rb)";
        }

        double pajak = subTotal * 0.10;
        double totalAkhir = (subTotal + pajak + biayaPelayanan) - diskon - bogoDiscountValue;

        // --- 4. Mencetak Struk Pesanan (Output) ---

        System.out.println("\n\n==================================================");
        System.out.println("            STRUK PEMBAYARAN RESTORAN");
        System.out.println("==================================================");

        if (jumlah1 > 0) {
            System.out.println(pesanan1);
            System.out.println("  " + jumlah1 + " x Rp " + hargaItem1 + "\t\t= Rp " + totalItem1);
        }

        if (jumlah2 > 0) {
            System.out.println(pesanan2);
            System.out.println("  " + jumlah2 + " x Rp " + hargaItem2 + "\t\t= Rp " + totalItem2);
        }

        if (jumlah3 > 0) {
            System.out.println(pesanan3);
            System.out.println("  " + jumlah3 + " x Rp " + hargaItem3 + "\t\t= Rp " + totalItem3);
        }

        if (jumlah4 > 0) {
            System.out.println(pesanan4);
            System.out.println("  " + jumlah4 + " x Rp " + hargaItem4 + "\t\t= Rp " + totalItem4);
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Subtotal \t\t\t: Rp " + subTotal);
        System.out.println("Pajak (10%) \t\t\t: Rp " + pajak);
        System.out.println("Biaya Pelayanan \t\t: Rp " + biayaPelayanan);

        if (diskon > 0) {
            System.out.println(catatanDiskon + "\t: Rp -" + diskon);
        }
        if (bogoDiscountValue > 0) {
            System.out.println(catatanBOGO + "\t: Rp -" + bogoDiscountValue);
        }

        System.out.println("--------------------------------------------------");
        System.out.println("TOTAL AKHIR \t\t\t: Rp " + totalAkhir);
        System.out.println("==================================================");
        System.out.println("             Terima Kasih Atas Kunjungan Anda");
        System.out.println("==================================================");

        // input.close(); // Sebaiknya jangan ditutup jika Scanner itu static
    }
}
