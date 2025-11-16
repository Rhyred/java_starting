import java.util.Arrays;

/**
 * Program Java untuk mendemonstrasikan Merge Sort dan Counting Sort
 * yang diurutkan dari TERBESAR ke TERKECIL.
 */
public class SortingProgram {

    public static void main(String[] args) {
        // --- Soal 1: Merge Sort ---
        System.out.println("--- 1. Merge Sort (Descending) ---");
        int[] dataMergeSort = {38, 27, 43, 3, 9, 82, 10, 50}; // 8 elemen
        System.out.println("Data Awal: " + Arrays.toString(dataMergeSort));
        
        mergeSortDescending(dataMergeSort);
        
        System.out.println("Data Terurut: " + Arrays.toString(dataMergeSort));
        System.out.println("-------------------------------------\n");

        // --- Soal 2: Counting Sort ---
        System.out.println("--- 2. Counting Sort (Descending) ---");
        int[] dataCountingSort = {38, 27, 43, 3, 9, 82, 10, 50}; // 8 elemen
        // Data lain untuk membuktikan (harus non-negatif)
        // int[] dataCountingSort = {5, 2, 9, 5, 2, 3, 8, 1, 10, 8};
        System.out.println("Data Awal: " + Arrays.toString(dataCountingSort));
        
        countingSortDescending(dataCountingSort);
        
        System.out.println("Data Terurut: " + Arrays.toString(dataCountingSort));
        System.out.println("-------------------------------------");
    }

    // =================================================================
    // == BAGIAN 1: MERGE SORT (DESCENDING)
    // =================================================================

    /**
     * Fungsi publik untuk memulai proses Merge Sort.
     */
    public static void mergeSortDescending(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // Tidak perlu di-sort
        }
        sortRecursive(arr, 0, arr.length - 1);
    }

    /**
     * Fungsi rekursif (Divide)
     * Membagi array menjadi dua bagian.
     */
    private static void sortRecursive(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2; // Tentukan titik tengah
            
            // Panggil rekursif untuk sisi kiri
            sortRecursive(arr, left, mid);
            // Panggil rekursif untuk sisi kanan
            sortRecursive(arr, mid + 1, right);
            
            // Gabungkan kedua sisi (Conquer)
            merge(arr, left, mid, right);
        }
    }

    /**
     * Fungsi 'Conquer' untuk menggabungkan dua subarray yang sudah terurut.
     * Ini adalah inti dari modifikasi "Descending".
     */
    private static void merge(int[] arr, int left, int mid, int right) {
        // 1. Tentukan ukuran dua subarray
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // 2. Buat array sementara
        int[] L = new int[n1];
        int[] R = new int[n2];

        // 3. Salin data ke array sementara
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[mid + 1 + j];
        }

        // 4. Proses penggabungan (Merge)
        int i = 0, j = 0; // Index awal untuk L dan R
        int k = left;     // Index awal untuk array 'arr' yang digabung

        while (i < n1 && j < n2) {
            // *** INI MODIFIKASINYA ***
            // Bandingkan L[i] dan R[j]. Ambil yang LEBIH BESAR.
            if (L[i] >= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // 5. Salin sisa elemen (jika ada)
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
     * Fungsi publik untuk memulai proses Counting Sort.
     */
    public static void countingSortDescending(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }

        // 1. Cari nilai maksimum (k) dalam array
        int max = findMax(arr);

        // 2. Buat 'count array' (array pencacah)
        // Ukurannya harus 'max + 1' untuk menampung index dari 0 s.d. max
        int[] count = new int[max + 1];

        // 3. Hitung frekuensi (jumlah) kemunculan setiap elemen
        for (int num : arr) {
            count[num]++;
        }

        // 4. Bangun kembali array 'arr' (overwrite)
        // *** INI MODIFIKASINYA ***
        // Kita iterasi dari 'max' (terbesar) ke 0 (terkecil)
        int outputIndex = 0; // Index untuk menempatkan data di 'arr'
        
        for (int i = max; i >= 0; i--) {
            // Selama 'count[i]' masih lebih dari 0,
            // berarti angka 'i' masih ada
            while (count[i] > 0) {
                arr[outputIndex] = i; // Masukkan angka 'i' ke array
                outputIndex++;        // Pindahkan index output
                count[i]--;           // Kurangi cacah angka 'i'
            }
        }
    }

    /**
     * Helper method untuk mencari nilai maksimum di array.
     * Diperlukan untuk menentukan ukuran 'count array'.
     */
    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}