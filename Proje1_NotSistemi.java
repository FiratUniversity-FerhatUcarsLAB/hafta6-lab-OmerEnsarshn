/**
 * Ad Soyad: Ömer Ensar Şahin
 * Öğrenci No: 250542022
 * Proje:  Not Sistemi
 * Tarih: 24.11.2025
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Vize notunu girin:  ");
        int vizeNot = input.nextInt();

        System.out.print("Final notunu girin:  ");
        int finalNot = input.nextInt();

        System.out.print("Odev notunu girin:  ");
        int odevNot = input.nextInt();

        // Ortalama hesapla
        double ortalama = calculataAverage(vizeNot, finalNot, odevNot);

        System.out.println("\n--- SONUÇLAR ---");
        System.out.println("Ortalama: " + ortalama);

        // Geçti mi kaldı mı?
        isPassingGrade(ortalama);

        // Harf notu
        getLetter(ortalama);

        // Onur listesi kontrolü
        isHonorList(ortalama, finalNot, odevNot, vizeNot);

        // Bütünleme hakkı var mı?
        hasRetakeRight(ortalama);
    }

    public static double calculataAverage(double vizeNot, double finalNot, double odevNot) {
        return vizeNot * 0.3 + finalNot * 0.4 + odevNot * 0.3;
    }

    public static double isPassingGrade(double ortalama){
        if(ortalama < 50){
            System.out.println("Durum: KALDI");
        }
        else{
            System.out.println("Durum: GEÇTİ");
        }
        return ortalama;
    }

    public static String getLetter(double ortalama){
        System.out.print("Harf Notu: ");
        if(ortalama >= 90){
            System.out.println("A");
        }
        else if(ortalama >= 80){
            System.out.println("B");
        }
        else if(ortalama >= 70){
            System.out.println("C");
        }
        else if(ortalama >= 60){
            System.out.println("D");
        }
        else if(ortalama >= 50){
            System.out.println("E");
        }
        else{
            System.out.println("F");
        }
        return "";
    }

    public static double isHonorList(double ortalama, double finalNot, double odevNot, double vizeNot){
        System.out.print("Onur Listesi: ");
        if(ortalama >= 85 && finalNot > 70 && odevNot > 70 && vizeNot > 70){
            System.out.println("EVET");
        }
        else{
            System.out.println("HAYIR");
        }
        return ortalama;
    }

    public static double hasRetakeRight(double ortalama){
        System.out.print("Bütünleme Hakkı: ");
        if(ortalama >= 40 && ortalama < 50){
            System.out.println("VAR");
        }
        else{
            System.out.println("YOK");
        }
        return ortalama;
    }
}
