import java.io.IOException;
import java.util.Scanner;
import java.math.BigInteger;

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

        System.out.println("N och M");
        int N = scanObj.nextInt();
        int M = scanObj.nextInt();
        scanObj.nextLine();
        System.out.println("A");
        BigInteger A = new BigInteger(scanObj.next().trim(), N);
        System.out.println("B");
        BigInteger B = new BigInteger(scanObj.next().trim(), N);
        scanObj.close();

        String result = javap_basen(N, M, A, B);
        System.out.println(result);


    }

    private static String javap_basen(Integer n, Integer m, BigInteger a, BigInteger b) {
        BigInteger c = a.multiply(b);

        return c.toString(m).toUpperCase();


    }
    
}
