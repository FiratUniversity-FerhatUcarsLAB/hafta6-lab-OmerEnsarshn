/**
 * Ad Soyad: Ömer Ensar Şahin
 * Öğrenci No: 250542022
 * Proje:  Sinema Bielti
 * Tarih: 24.11.2025
 */
import java.util.Scanner;

public class Main {

    final static double HAFTA_ICI_MATINE = 45.0;
    final static double HAFTA_ICI_NORMAL = 65.0;
    final static double HAFTA_SONU_MATINE = 55.0;
    final static double HAFTA_SONU_NORMAL = 85.0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Gün Seçimi (1=Pzt, 2=Sal, 3=Çar, 4=Per, 5=Cuma, 6=Cts, 7=Paz): ");
        int gun = input.nextInt();

        System.out.print("Saat (0-23): ");
        int saat = input.nextInt();

        System.out.println("Film Türü (1=2D, 2=3D, 3=IMAX, 4=4DX): ");
        int filmTuru = input.nextInt();

        System.out.print("Yaşınızı girin: ");
        int yas = input.nextInt();

        System.out.println("Meslek Seçimi (1=Öğrenci, 2=Öğretmen, 3=Diğer): ");
        int meslek = input.nextInt();

        boolean haftaSonu = isWeekend(gun);
        boolean matine = isMatinee(saat);
        double basePrice = calculateBasePrice(haftaSonu, matine);
        double discount = calculateDiscount(yas, meslek, gun, haftaSonu);
        double ekstra = getFormatExtra(filmTuru);
        double finalPrice = calculateFinalPrice(basePrice, discount, ekstra);

        generateTicketInfo(gun, saat, filmTuru, yas, meslek, basePrice, discount, ekstra, finalPrice);


        input.close();
    }

    public static boolean isWeekend(int gun){
        return gun == 6 || gun == 7;
    }
    public static boolean isMatinee(int saat){
        return saat < 12;
    }
    public static double calculateBasePrice(boolean haftaSonu, boolean matine){
        if(haftaSonu){
            if(matine) return HAFTA_SONU_MATINE;
            else return HAFTA_SONU_NORMAL;
        }
        else{
            if(matine) return HAFTA_SONU_MATINE;
            else return HAFTA_SONU_NORMAL;
            }
    }
    public static double calculateDiscount(int yas, int meslek, int gun, boolean haftaSonu) {
        double discount = 0.0;

        // 65+ yaş
        if (yas >= 65) discount = 30.0;
            // 12 yaş altı
        else if (yas < 12) discount = 25.0;
            // Öğrenci indirimi
        else if (meslek == 1) {
            switch (gun) {
                case 1: case 2: case 4: // Pzt, Sal, Per
                    discount = 20.0; break;
                case 3: case 5: case 6: case 7: // Çar, Cuma, Cts, Pazar
                    discount = 15.0; break;
            }
        }
        // Öğretmen indirimi
        else if (meslek == 2 && gun == 3) discount = 35.0; // Çarşamba

        return discount;
    }

    public static double getFormatExtra(int filmTuru) {
        switch(filmTuru) {
            case 2: return 25.0; // 3D
            case 3: return 35.0; // IMAX
            case 4: return 50.0; // 4DX
            default: return 0.0; // 2D
        }
    }

    public static double calculateFinalPrice(double basePrice, double discount, double ekstra) {
        return basePrice - (basePrice * discount / 100.0) + ekstra;
    }

    public static void generateTicketInfo(int gun, int saat, int filmTuru, int yas, int meslek,
                                          double basePrice, double discount, double ekstra, double finalPrice) {

        String gunAdi = switch (gun) {
            case 1 -> "Pazartesi";
            case 2 -> "Salı";
            case 3 -> "Çarşamba";
            case 4 -> "Perşembe";
            case 5 -> "Cuma";
            case 6 -> "Cumartesi";
            case 7 -> "Pazar";
            default -> "Bilinmiyor";
        };

        String filmAdi = switch (filmTuru) {
            case 1 -> "2D";
            case 2 -> "3D";
            case 3 -> "IMAX";
            case 4 -> "4DX";
            default -> "2D";
        };

        String meslekAdi = switch (meslek) {
            case 1 -> "Öğrenci";
            case 2 -> "Öğretmen";
            case 3 -> "Diğer";
            default -> "Diğer";
        };

        String matineStr = saat < 12 ? "Matine" : "Normal";

        System.out.println("\n----- Bilet Özeti -----");
        System.out.printf("Gün: %s\n", gunAdi);
        System.out.printf("Saat: %02d:00 (%s)\n", saat, matineStr);
        System.out.printf("Film Türü: %s\n", filmAdi);
        System.out.printf("Yaş: %d, Meslek: %s\n", yas, meslekAdi);
        System.out.printf("Temel Fiyat: %.2f TL\n", basePrice);
        System.out.printf("İndirim: %.0f%%\n", discount);
        System.out.printf("Format Ekstra: %.2f TL\n", ekstra);
        System.out.printf("Toplam Fiyat: %.2f TL\n", finalPrice);
        System.out.println("-----------------------");
    }

}

