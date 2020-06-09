package li.antonio.coding_challenges.code_eval.longest_common_subsequence;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

import static java.nio.file.Files.readAllLines;

/**
 * https://www.codeeval.com/browse/6/
 */
public class Main {
    private static final Pattern SEMICOLON_SEPARATED = Pattern.compile(";");

    private Main() {
    }

    public static void main(final String[] argv) throws IOException {
        readAllLines(new File(argv[0]).toPath(), Charset.defaultCharset())
                .stream()
                .map(SEMICOLON_SEPARATED::split)
                .filter(s -> s.length == 2)
                .forEach(s -> {
                    //noinspection UseOfSystemOutOrSystemErr
                    System.out.println(lcs(s[0], s[1]));
                });
    }

    static String lcs(final String x, final String y) {
        return printLcs(lcsLengths(x, y), x);
    }

    private static int[][] lcsLengths(final String x, final String y) {
        final var c = new int[x.length() + 1][y.length() + 1];
        for (var i = 1; i < c.length; i++) {
            for (var j = 1; j < c[i].length; j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                } else {
                    c[i][j] = Math.max(c[i - 1][j], c[i][j - 1]);
                }
            }
        }
        return c;
    }

    private static String printLcs(final int[][] c, final String x) {
        final var result = new StringBuilder();
        for (int i = c.length - 1, j = c[0].length - 1; i > 0 && j > 0; ) {
            if (c[i][j] == c[i - 1][j]) {
                i--;
            } else if (c[i][j] == c[i][j - 1]) {
                j--;
            } else {
                result.insert(0, x.charAt(i - 1));
                i--;
                j--;
            }
        }
        return result.toString();
    }

}
