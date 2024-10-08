import com.aparapi.Kernel;
import com.aparapi.Range;

/**
 * Sum of two arrays
 */
public class Example1 {

    public static void main(String[] _args) {
        final int size = 512;
        final float[] a = new float[size];
        final float[] b = new float[size];

        /* fill the arrays with random values */
        for (int i = 0; i < size; i++){
            a[i] = (float) (Math.random() * 100);
            b[i] = (float) (Math.random() * 100);
        }
        final float[] sum = new float[size];

        Kernel kernel = new Kernel(){
            @Override public void run() {
                int gid = getGlobalId();
                sum[gid] = a[gid] + b[gid];
            }
        };

        kernel.execute(Range.create(size));
        for(int i = 0; i < size; i++) {
            System.out.printf("%6.2f + %6.2f = %8.2f\n", a[i], b[i], sum[i]);
        }
        kernel.dispose();
    }
}