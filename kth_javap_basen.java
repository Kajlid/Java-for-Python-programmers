import java.io.IOException;
import java.util.Scanner;

public class kth_javap_basen {
    /*  
        I indata anges först talen N resp. M på första raden, sedan följer talen A och B på varsin egen rad.
        Du kan anta att N och M är heltal, och minst 2 och högst 36, samt att både A och B är positiva heltal som är inte är större än Integer.MAX_VALUE.

        Utdata: Ett heltal C angett i bas M.

        */

    
    /*
        Formatting numeric print output:

        PrintStream class has two formatting methods that can be used to replace print and println:

        format and printf. Equivalent to each other. Can be used instead of print and println.

`       public PrintStream format(String format, Object... args)`
     */

    public static void main (String[] args) {

        Scanner scanObj = new Scanner(System.in);

        int N = scanObj.nextInt();
        int M = scanObj.nextInt();
        scanObj.nextLine();
        int A = Integer.parseInt(scanObj.nextLine().trim(), N);
        int B = Integer.parseInt(scanObj.nextLine().trim(), N);
        scanObj.close();
        String result = javap_basen(N, M, A, B);
        System.out.println(result);


    }

    /* 
    private static int hanteraText(String numString, int n) {
        int num = 0;
        for (int i = 0; i < numString.length(); i++) {

            // Character.digit ger ett numeriskt värde till en char i en viss bas (radix)

            int characterValue = Character.getNumericValue(numString.charAt(i));

            num = num * n + characterValue;

        }

        return num;

    } */

    private static String javap_basen(Integer n, Integer m, Integer a, Integer b) {
        int c = a * b;

        return Integer.toString(c, m).toUpperCase();


    }
    
}
