import java.util.Scanner;

public class orderedString {
    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);     // öppnar ett object av klass scanner
        System.out.println("Skriv en sträng:");
        String S = scanObj.nextLine().trim();       // vad användaren skriver in
        scanObj.close();
        int L = alphaSub(S);
        System.out.println(L); 

        

   
    }

    public static int alphaSub(String s) {

        int length = 1;

        if (s.isEmpty()) {
            return 0;  // Om strängen är tom returnerar vi 0
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
