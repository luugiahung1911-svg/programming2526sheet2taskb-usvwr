import java.util.Scanner;
import java.math.BigInteger;

public class IBAN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String IBAN = sc.nextLine();
        sc.close();

        // Biến đổi Laenderkennung thành số
        String Laenderkennung = IBAN.substring(0, 2);
        BigInteger Bankleitzahl = new BigInteger(IBAN.substring(3, 11));
        BigInteger Kontonummer = new BigInteger(IBAN.substring(12));

        // Biến đổi Laenderkennung thành số
        char a = IBAN.charAt(0);
        char b = IBAN.charAt(1);

        String Alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        BigInteger a1 = BigInteger.valueOf(Alphabet.indexOf(a));
        BigInteger b1 = BigInteger.valueOf(Alphabet.indexOf(b));

        a1 = a1.add(BigInteger.valueOf(10));
        b1 = b1.add(BigInteger.valueOf(10));

        BigInteger gL = a1.multiply(BigInteger.valueOf(100)).add(b1).multiply(BigInteger.valueOf(100));

        // Bankleitzahl, Kontonummer und Länderkennung
        BigInteger BaKo = new BigInteger(Bankleitzahl.toString()).multiply(BigInteger.valueOf(100000)).multiply(BigInteger.valueOf(100000)).add(new BigInteger(Kontonummer.toString()));
        BigInteger Verk = BaKo.multiply(BigInteger.valueOf(1000000));
        Verk = Verk.add(gL);

        BigInteger mod = Verk.mod(BigInteger.valueOf(97));
        int sub98 = 98 - mod.intValue();

        // Đổi biến từ int, BigInt về String
        String sub98s = String.valueOf(sub98);
        String BaKos = BaKo.toString();

        while (sub98s.length() < 2) {
            sub98s = "0" + sub98s;
        }

        String IBANg = Laenderkennung + sub98s + BaKos;
        System.out.println(Laenderkennung + sub98s + " " + BaKos.substring(0, 4) + " " + BaKos.substring(4, 8) + " " + BaKos.substring(8, 12) + " " + BaKos.substring(12, 16) + " " + BaKos.substring(16));
    }
}
