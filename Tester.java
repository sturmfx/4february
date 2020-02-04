
import java.util.Random;

public class Tester
{
    public static Random random = new Random();
    public static int[] a = {10, 12, 13, 14, 7, 6, 11, 17, 24, 25};
    public static int[] b = {32, 25, 48, 30, 13, 47, 15, 46, 47, 15};
    public static int[] c = {7, 6, 9, 4, 2, 5, 6, 9, 1, 0};
    public static int[] d = {1, 3, 2, 7, 0, 3, 5, 9, 0, 6};

    public static void main(String[] args)
    {

    }

    public void init()
    {
        a = create_array(10, 100, 200);
        b = create_array(10, 0, 10);
        c = create_array(10, 200, 500);
        d = create_array(10, 10, 30);
    }

    public static void print_array(int[] array)
    {
        StringBuilder s = new StringBuilder();

        s.append('[');

        for(int i = 0; i < array.length; i++)
        {
            s.append(array[i]);
            if(i < array.length - 1)
            {
                s.append(',');
                s.append(' ');
            }

        }
        s.append(']');
        System.out.println(s.toString());
    }

    public static int[] create_array(int n, int min, int max)
    {
        Random r = new Random();

        int[] result = new int[n];
        for(int i = 0; i < n; i++)
        {
            result[i] = min + r.nextInt(max - min);
        }
        return result;
    }
}
