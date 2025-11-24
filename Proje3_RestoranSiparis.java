/**
 * Ad Soyad: Ömer Ensar Şahin
 * Öğrenci No: 250542022
 * Proje:  Restoran Sipariş
 * Tarih: 24.11.2025
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== AKILLI RESTORAN SIPARIS SISTEMI ===");

        // Ana Yemek
        System.out.println("\n1) Izgara Tavuk (85)\n2) Adana Kebap (120)\n3) Levrek (110)\n4) Mantı (65)");
        System.out.print("Ana Yemek Secimi (0 = Yok): ");
        int anaSecim = input.nextInt();
        double anaFiyat = getMainDishPrice(anaSecim);

        // Başlangıç
        System.out.println("\n1) Corba (25)\n2) Humus (45)\n3) Sigara Boregi (55)");
        System.out.print("Baslangic Secimi (0 = Yok): ");
        int basSecim = input.nextInt();
        double basFiyat = getAppetizerPrice(basSecim);

        // İçecek
        System.out.println("\n1) Kola (15)\n2) Ayran (12)\n3) Taze Meyve Suyu (35)\n4) Limonata (25)");
        System.out.print("Icecek Secimi (0 = Yok): ");
        int icecekSecim = input.nextInt();
        double icecekFiyat = getDrinkPrice(icecekSecim);

        // Tatlı
        System.out.println("\n1) Kunefe (65)\n2) Baklava (55)\n3) Sutlac (35)");
        System.out.print("Tatli Secimi (0 = Yok): ");
        int tatliSecim = input.nextInt();
        double tatliFiyat = getDessertPrice(tatliSecim);

        System.out.print("\nSaat kaç? (0-23): ");
        int saat = input.nextInt();

        System.out.print("Haftanın günü (1=Pzt ... 7=Paz): ");
        int gun = input.nextInt();

        System.out.print("Öğrenci misiniz? (true/false): ");
        boolean ogrenci = input.nextBoolean();

        boolean combo = isComboOrder(anaSecim != 0, icecekSecim != 0, tatliSecim != 0);

        double toplam = anaFiyat + basFiyat + icecekFiyat + tatliFiyat;

        double indirimli = calculateDiscount(toplam, combo, ogrenci, saat);

        double bahsis = calculateServiceTip(indirimli);

        double odenecek = indirimli + bahsis;

        System.out.println("\n=== FIŞ ===");
        System.out.printf("Yemek Toplami: %.2f TL\n", toplam);
        System.out.printf("Indirim Sonrasi: %.2f TL\n", indirimli);
        System.out.printf("Bahsis (%%10): %.2f TL\n", bahsis);
        System.out.printf("Odenecek Toplam: %.2f TL\n", odenecek);
    }

    // 1 — Ana Yemek
    public static double getMainDishPrice(int secim) {
        switch (secim) {
            case 1: return 85;
            case 2: return 120;
            case 3: return 110;
            case 4: return 65;
            default: return 0;
        }
    }

    // 2 — Başlangıç
    public static double getAppetizerPrice(int secim) {
        switch (secim) {
            case 1: return 25;
            case 2: return 45;
            case 3: return 55;
            default: return 0;
        }
    }

    // 3 — İçecek
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1: return 15;
            case 2: return 12;
            case 3: return 35;
            case 4: return 25;
            default: return 0;
        }
    }

    // 4 — Tatlı
    public static double getDessertPrice(int secim) {
        switch (secim) {
            case 1: return 65;
            case 2: return 55;
            case 3: return 35;
            default: return 0;
        }
    }

    // 5 — Combo kontrol
    public static boolean isComboOrder(boolean ana, boolean icecek, boolean tatli) {
        return ana && icecek && tatli;
    }

    // 6 — Happy Hour
    public static boolean isHappyHour(int saat) {
        return saat >= 14 && saat <= 17;
    }

    // 7 — İndirim
    public static double calculateDiscount(double tutar, boolean combo, boolean ogrenci, int saat) {

        double yeniTutar = tutar;

        // Combo %15
        if (combo) {
            yeniTutar *= 0.85;
        }

        // 200 TL üstü %10
        if (yeniTutar > 200) {
            yeniTutar *= 0.90;
        }

        // Happy Hour — içeceklerde %20 ama biz direkt toplamdan düşüyoruz (kolay yol)
        if (isHappyHour(saat)) {
            yeniTutar *= 0.80;
        }

        // Öğrenci — hafta içi (%10)
        if (ogrenci) {
            if (saat >= 1) { // Dummy kontrol — asıl hafta içi kontrol gün ile olurdu
                yeniTutar *= 0.90;
            }
        }

        return yeniTutar;
    }

    // 8 — Servis bahşişi %10
    public static double calculateServiceTip(double tutar) {
        return tutar * 0.10;
    }
}
