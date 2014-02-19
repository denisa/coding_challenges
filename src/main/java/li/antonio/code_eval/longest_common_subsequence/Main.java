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

    static String lcs(final String s, final String s1) {
        return "";
    }

}
