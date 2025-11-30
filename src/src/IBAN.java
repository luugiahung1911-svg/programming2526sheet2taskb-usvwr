package src/;
import java.math.BigInteger;

public class IBAN {
    public static void main(String[] args) {
        //Nhập vào IBAN(i))
        String iban = args[0].replace(" ", "");

        // Biến đổi Länderkennung thành số
        String laenderkennung = iban.substring(0, 2);
        BigInteger bankleitzahl = new BigInteger(iban.substring(2, 10));
        BigInteger kontonummer = new BigInteger(iban.substring(10));  
        // Biến đổi Laenderkennung thành số
        char a = iban.charAt(0);
        char b = iban.charAt(1);
        // Thêm String Alphabet (z)
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        BigInteger a1 = BigInteger.valueOf(alphabet.indexOf(a));
        BigInteger b1 = BigInteger.valueOf(alphabet.indexOf(b));

        a1 = a1.add(BigInteger.valueOf(10));
        b1 = b1.add(BigInteger.valueOf(10));
        BigInteger gL = a1.multiply(BigInteger.valueOf(100)).add(b1).multiply(BigInteger.valueOf(100));
        // Bankleitzahl, Kontonummer und Länderkennung
        BigInteger baKo = new BigInteger(bankleitzahl.toString()).multiply(BigInteger.valueOf(100000));
        baKo = baKo.multiply(BigInteger.valueOf(100000)).add(new BigInteger(kontonummer.toString()));
        BigInteger verk = baKo.multiply(BigInteger.valueOf(1000000));
        verk = verk.add(gL);

        BigInteger mod = verk.mod(BigInteger.valueOf(97));
        int sub98 = 98 - mod.intValue();

        // Đổi biến từ int, BigInt về String
        String sub98s = String.valueOf(sub98);
        String baKos = baKo.toString();

        while (sub98s.length() < 2) {
            sub98s = "0" + sub98s;
        }
        String baKos04 = baKos.substring(0, 4);
        String baKos48 = baKos.substring(4, 8);
        String baKos812 = baKos.substring(8, 12); 
        String baKos1216 = baKos.substring(12, 16); 
        String baKos16 = baKos.substring(16); 
        System.out.println(laenderkennung + sub98s + " " + baKos04 + " " + baKos48 + " " + baKos812 + " " + baKos1216 + " " + baKos16);
    }
}
