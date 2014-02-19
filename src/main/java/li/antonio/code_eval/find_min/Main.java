package li.antonio.code_eval.find_min;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * https://www.codeeval.com/browse/85/
 */
public class Main {

    private static final Pattern COMA_SEPARATED = Pattern.compile(",");

    private Main() {
    }

    public static void main(final String[] argv) throws IOException {
        for (final String s : Files.readAllLines(new File(argv[0]).toPath(), Charset.defaultCharset())) {
            final String[] split = COMA_SEPARATED.split(s);
            final int n = Integer.parseInt(split[0]);
            final int k = Integer.parseInt(split[1]);
            final int a = Integer.parseInt(split[2]);
            final int b = Integer.parseInt(split[3]);
            final int c = Integer.parseInt(split[4]);
            final int r = Integer.parseInt(split[5]);
            //noinspection UseOfSystemOutOrSystemErr
            System.out.println(find_min(n, k, a, b, c, r));
        }
    }

    static int find_min(final int n, final int k, final int a, final int b, final int c, final int r) {
        final int[] m = new int[k];

        seed(m, a, b, c, r);
        final int[] sorted_m = m.clone();
        Arrays.sort(sorted_m);

        for (int i = k; i < n; i++) {
            final int mnc = minimumNotContained(sorted_m);
            final int removed = left_shift(m, mnc);
            replace(sorted_m, removed, mnc);
        }
        return m[m.length - 1];
    }

    static void seed(final int[] m, final int a, final int b, final int c, final int r) {
        m[0] = a;
        for (int i = 1; i < m.length; i++) {
            m[i] = (int) (((long) b * m[i - 1] + c) % r);
        }
    }

    static int minimumNotContained(final int[] m) {
        if (m[0] > 0) {
            return 0;
        }
        for (int i = 1; i < m.length; i++) {
            final int candidate = m[i - 1] + 1;
            if (m[i] > candidate) {
                return candidate;
            }
        }
        return m[m.length - 1] + 1;
    }

    static int left_shift(final int[] m, final int added) {
        final int removed = m[0];
        System.arraycopy(m, 1, m, 0, m.length - 1);
        m[m.length - 1] = added;
        return removed;
    }

    static void replace(final int[] m, final int removed, final int added) {
        if (removed == added) {
            return;
        }

        final int removalIndex = Arrays.binarySearch(m, removed);
        if (m[m.length - 1] <= added) {
            System.arraycopy(m, removalIndex + 1, m, removalIndex, m.length - removalIndex - 1);
            m[m.length - 1] = added;
            return;
        }

        final int insertIndex = Arrays.binarySearch(m, added);
        final int insertAt = insertIndex < 0 ? -(insertIndex + 1) : insertIndex;

        if (removalIndex >= insertAt) {
            System.arraycopy(m, insertAt, m, insertAt + 1, removalIndex - insertAt);
            m[insertAt] = added;
        } else {
            System.arraycopy(m, removalIndex + 1, m, removalIndex, insertAt - removalIndex - 1);
            m[insertAt - 1] = added;
        }
    }

}
