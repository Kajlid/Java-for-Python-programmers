import java.util.Scanner;

// OBS Scanner är långsamt och kan orsaka Runtime Error i Kattis. Byt ut mot BufferedReader vid problem.
/*
 Labb 1:
    Hitta längsta sorterade delsträngen i en längre sträng.
    Indata: icke radbruten sträng S. 
    Utdata: Heltal L (längd på längsta sorterade delsträng)
 */

public class orderedString {
    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);     // öppnar ett object av klass scanner
        // Hjälpsträng för att komma ihåg input-typ, får inte skickas in i Kattis
        System.out.println("Skriv en sträng:");
        String S = scanObj.nextLine().trim();       // vad användaren skriver in
        scanObj.close();
        int L = alphaSub(S);    // utdata
        System.out.println(L); 
   
    }

    public static int alphaSub(String s) {


        int length = 1;

        if (s.isEmpty()) {
            return 0;      // Om strängen är tom, returnera 0 direkt
        }

        int curr = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) >= s.charAt(i - 1)) {
                curr++;
            } else {
                curr = 1;
            }
            length = Math.max(length, curr);
        }
        return length;
    }

}
