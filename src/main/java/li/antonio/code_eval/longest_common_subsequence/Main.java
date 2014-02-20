package li.antonio.code_eval.longest_common_subsequence;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.regex.Pattern;

/**
 * https://www.codeeval.com/browse/6/
 */
public class Main {
    private static final Pattern SEMICOLON_SEPARATED = Pattern.compile(";");

    private Main() {
    }

    public static void main(final String[] argv) throws IOException {
        for (final String s : Files.readAllLines(new File(argv[0]).toPath(), Charset.defaultCharset())) {
            final String[] split = SEMICOLON_SEPARATED.split(s);
            if (split.length != 2) {
                continue;
            }
            //noinspection UseOfSystemOutOrSystemErr
            System.out.println(lcs(split[0], split[1]));
        }
    }

    static String lcs(final String x, final String y) {
        return printLcs(lcsLengths(x, y), x, x.length(), y.length());
    }

    static int[][] lcsLengths(final String x, final String y) {
        int[][] c = new int[x.length() + 1][y.length() + 1];
        for (int i = 1; i < c.length; i++) {
            for (int j = 1; j < c[i].length; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                } else {
                    c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
                }
            }
        }
        return c;
    }

    private static String printLcs(final int[][] c, final String x, final int i, final int j) {
        if (i == 0 || j == 0) {
            return "";
        }
        if (c[i][j] == c[i - 1][j]) {
            return printLcs(c, x, i - 1, j);
        }
        if (c[i][j] == c[i - 1][j - 1] + 1) {
            return printLcs(c, x, i - 1, j - 1) + x.charAt(i - 1);
        }
        return printLcs(c, x, i, j - 1);
    }

}
