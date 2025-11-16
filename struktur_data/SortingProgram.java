// Import library yang kita butuhkan
import java.util.Arrays;   // Untuk menyalin array (copyOf) dan mencetaknya (toString)
import java.util.Scanner;  // Untuk mengambil input dari keyboard pengguna

/**
 * Program Java untuk Merge Sort dan Counting Sort (Descending)
 */
public class SortingProgram {

    // Kita buat 'Scanner' di sini (sebagai variabel 'static')
    // agar bisa dipakai oleh semua method di dalam kelas ini
    // tanpa perlu dibuat berulang-ulang.
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Ini adalah method MAIN, titik awal program kita dieksekusi.
     */
    public static void main(String[] args) {
        
        // --- LANGKAH 1: MENGAMBIL DATA DARI PENGGUNA ---
        // Kita panggil method 'inputDataManual' yang sudah kita buat di bawah.
        // Method ini akan mengembalikan sebuah array integer (int[])
        // yang kita simpan ke variabel 'dataOriginal'.
        int[] dataOriginal = inputDataManual();

        
        // --- LANGKAH 2: MENYALIN ARRAY (SANGAT PENTING!) ---
        
        // KENAPA HARUS DISALIN?
        // Method sorting (Merge Sort & Counting Sort) akan MENGUBAH urutan array
        // yang ada di dalamnya.
        // Jika kita hanya pakai 'dataOriginal' untuk keduanya,
        // maka Counting Sort akan menerima data yang SUDAH DIURUTKAN
        // oleh Merge Sort. Itu akan jadi tidak adil dan salah.
        
        // Karena itu, kita buat DUA salinan terpisah:
        int[] dataUntukMerge = Arrays.copyOf(dataOriginal, dataOriginal.length);
        int[] dataUntukCounting = Arrays.copyOf(dataOriginal, dataOriginal.length);

        
        // --- LANGKAH 3: MENAMPILKAN DAN MENJALANKAN SORTING ---
        
        // Kita gunakan printf agar tampilannya rapi (rata kiri)
        System.out.println("\n==============================================");
        System.out.println("=== HASIL PROSES SORTING (DESCENDING) ===");
        System.out.println("==============================================");
        System.out.printf("%-20s: %s%n", "Data Asli Anda", Arrays.toString(dataOriginal));

        
        // --- BAGIAN MERGE SORT ---
        System.out.println("\n--- 1. Menjalankan Merge Sort ---");
        // Kita panggil method Merge Sort dengan data salinannya
        mergeSortDescending(dataUntukMerge);
        // Cetak hasilnya
        System.out.printf("%-20s: %s%n", "Data Terurut", Arrays.toString(dataUntukMerge));

        
        // --- BAGIAN COUNTING SORT ---
        System.out.println("\n--- 2. Menjalankan Counting Sort ---");
        // Kita panggil method Counting Sort dengan data salinannya
        countingSortDescending(dataUntukCounting);
        // Cetak hasilnya
        System.out.printf("%-20s: %s%n", "Data Terurut", Arrays.toString(dataUntukCounting));
        
        System.out.println("==============================================");
        System.out.println("=== Proses Selesai ===");

        // --- LANGKAH 4: MENUTUP SCANNER ---
        // Ini adalah praktik yang baik (good practice)
        // untuk menutup 'scanner' setelah selesai digunakan
        // agar tidak ada 'resource leak'.
        scanner.close();
    }

    /**
     * Method helper untuk meminta input data manual dari pengguna.
     * Method ini akan melakukan validasi:
     * 1. Jumlah elemen minimal 8.
     * 2. Angka yang diinput harus non-negatif (karena syarat Counting Sort).
     */
    private static int[] inputDataManual() {
        int jumlah = 0;
        
        // --- Validasi 1: Jumlah Elemen ---
        // Kita pakai loop 'while' agar program terus bertanya
        // jika pengguna memasukkan angka di bawah 8.
        while (jumlah < 8) {
            System.out.print("Mau input berapa angka? (Minimal 8): ");
            try {
                // Integer.parseInt() adalah perintah untuk mengubah Teks -> Angka
                jumlah = Integer.parseInt(scanner.nextLine());
                
                if (jumlah < 8) {
                    System.out.println("Input minimal 8 elemen.");
                }
            } catch (NumberFormatException e) {
                // Ini akan terjadi jika pengguna input 'abc' (bukan angka)
                System.out.println("Input tidak valid. Masukkan angka.");
            }
        }

        // Buat array kosong dengan ukuran 'jumlah' yang sudah valid
        int[] arr = new int[jumlah];
        System.out.println("Silakan masukkan " + jumlah + " angka:");
        System.out.println("(Catatan: Angka harus positif/nol untuk Counting Sort)");

        int i = 0; // 'i' adalah index untuk array 'arr'
        
        // --- Validasi 2: Input Angka Satu per Satu ---
        // Loop 'while' ini akan berjalan sebanyak 'jumlah' kali
        while (i < jumlah) {
            System.out.print("Angka ke-" + (i + 1) + ": ");
            try {
                int angka = Integer.parseInt(scanner.nextLine());
                
                // --- Validasi 3: Angka Non-Negatif (PENTING!) ---
                // Counting Sort tidak bisa memproses angka negatif.
                // Jadi kita harus validasi di sini.
                if (angka < 0) {
                    System.out.println("Error: Counting Sort tidak bisa memproses angka negatif. Masukkan angka positif atau nol.");
                    // 'i' TIDAK BERTAMBAH, jadi program akan bertanya
                    // lagi untuk "Angka ke-X" yang sama.
                } else {
                    arr[i] = angka; // Simpan angka jika valid
                    i++; // Pindah ke index berikutnya
                }
            } catch (NumberFormatException e) {
                // Jika input bukan angka
                System.out.println("Input tidak valid. Masukkan angka.");
            }
        }
        return arr; // Kembalikan array yang sudah terisi
    }


    // =================================================================
    // == BAGIAN 1: MERGE SORT (DESCENDING)
    // =================================================================

    /**
     * Fungsi "pintu masuk" untuk memulai Merge Sort.
     * Fungsi ini hanya memanggil fungsi rekursif utama.
     */
    public static void mergeSortDescending(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // Tidak perlu di-sort jika elemennya 0 atau 1
        }
        // Memulai proses rekursif dari index 0 sampai akhir
        sortRecursive(arr, 0, arr.length - 1);
    }

    /**
     * Ini adalah inti dari 'DIVIDE' (Pecah) dalam "Divide and Conquer".
     * Fungsi ini akan memanggil dirinya sendiri (rekursif)
     * untuk memecah array menjadi bagian-bagian kecil.
     */
    private static void sortRecursive(int[] arr, int left, int right) {
        // 'left < right' adalah kondisi berhenti.
        // Jika left == right, artinya array tinggal 1 elemen (sudah terurut)
        if (left < right) {
            // 1. Cari titik tengah
            int mid = (left + right) / 2;
            
            // 2. 'DIVIDE': Panggil rekursif untuk sisi KIRI
            sortRecursive(arr, left, mid);
            // 3. 'DIVIDE': Panggil rekursif untuk sisi KANAN
            sortRecursive(arr, mid + 1, right);
            
            // 4. 'CONQUER': Gabungkan kedua sisi yang sudah terurut
            merge(arr, left, mid, right);
        }
    }

    /**
     * Ini adalah inti dari 'CONQUER' (Gabung).
     * Fungsi ini menggabungkan dua subarray (L dan R) yang SUDAH TERURUT
     * kembali ke array 'arr'.
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        // 1. Hitung ukuran dua subarray
        int n1 = mid - left + 1; // Ukuran array kiri (L)
        int n2 = right - mid;    // Ukuran array kanan (R)

        // 2. Buat array sementara (L dan R)
        // Inilah yang membuat Merge Sort butuh memori O(n)
        int[] L = new int[n1];
        int[] R = new int[n2];

        // 3. Salin data dari 'arr' ke array sementara L dan R
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[mid + 1 + j];
        }

        // 4. Proses PENGGABUNGAN (Merge)
        int i = 0; // Index untuk array L
        int j = 0; // Index untuk array R
        int k = left; // Index untuk array 'arr' (array utama)

        // Loop ini berjalan selama L dan R masih punya elemen
        while (i < n1 && j < n2) {
            
            // *** INI KUNCI PENGURUTAN DESCENDING (TERBESAR KE TERKECIL) ***
            // Kita bandingkan L[i] dan R[j].
            // Jika L[i] LEBIH BESAR atau SAMA DENGAN R[j],
            // masukkan L[i] ke 'arr'.
            // (Untuk Ascending, kita pakai L[i] <= R[j])
            
            if (L[i] >= R[j]) {
                arr[k] = L[i];
                i++; // Pindahkan index L
            } else {
                arr[k] = R[j];
                j++; // Pindahkan index R
            }
            k++; // Pindahkan index 'arr'
        }

        // 5. Salin sisa elemen (jika ada)
        // Jika salah satu array (L atau R) habis duluan,
        // sisa dari array yang lain tinggal disalin saja.
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }


    // =================================================================
    // == BAGIAN 2: COUNTING SORT (DESCENDING)
    // =================================================================

    /**
     * Fungsi utama untuk Counting Sort.
     */
    public static void countingSortDescending(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // Tidak perlu di-sort
        }

        // --- LANGKAH 1: CARI NILAI MAKSIMUM (k) ---
        // Kita butuh 'max' untuk menentukan ukuran 'count array'.
        // Ini adalah 'k' dalam kompleksitas O(n + k).
        int max = findMax(arr);

        // --- LANGKAH 2: BUAT 'COUNT ARRAY' (Ember) ---
        // Ukurannya harus 'max + 1' karena array index-nya
        // mulai dari 0 (untuk menampung angka 0 s/d max).
        // Array ini otomatis terisi 0 semua.
        int[] count = new int[max + 1];

        // --- LANGKAH 3: HITUNG FREKUENSI ---
        // Kita loop array 'arr'
        // 'num' akan berisi setiap angka (misal: 38, 27, 43, dst.)
        for (int num : arr) {
            // 'count[num]++' artinya:
            // "Tambah 1 di 'ember' pada index 'num'"
            // Contoh: jika num = 43, maka count[43] akan jadi 1.
            // Jika nanti ketemu 43 lagi, count[43] akan jadi 2.
            count[num]++;
        }

        // --- LANGKAH 4: BANGUN KEMBALI ARRAY 'arr' ---
        // Kita akan timpa array 'arr' yang lama dengan data terurut.
        
        int outputIndex = 0; // Penanda posisi di 'arr' (mulai dari 0)

        // *** INI KUNCI PENGURUTAN DESCENDING (TERBESAR KE TERKECIL) ***
        // Kita loop 'count array' (ember) secara MUNDUR.
        // Mulai dari 'max' (angka terbesar) ke 0 (angka terkecil).
        // (Untuk Ascending, kita loop 'for (int i = 0; i <= max; i++)')
        
        for (int i = max; i >= 0; i--) {
            
            // Loop 'while' ini akan berjalan sebanyak frekuensi
            // angka 'i' yang kita hitung di Langkah 3.
            // Contoh: jika count[575] = 2, loop ini jalan 2 kali.
            while (count[i] > 0) {
                // Masukkan angka 'i' (angka yang sedang kita cek)
                // ke dalam array 'arr'
                arr[outputIndex] = i;
                
                outputIndex++; // Pindahkan index output
                count[i]--;    // Kurangi isi 'ember'
            }
        }
    }

    /**
     * Method helper sederhana untuk mencari nilai maksimum di array.
     * Diperlukan oleh Counting Sort.
     */
    private static int findMax(int[] arr) {
        int max = arr[0]; // Asumsikan angka pertama adalah terbesar
        // Loop mulai dari 1 (karena 0 sudah jadi 'max')
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i]; // Update 'max' jika ada yg lebih besar
            }
        }
        return max; // Kembalikan nilai terbesar
    }
}