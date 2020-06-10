package li.antonio.coding_challenges.interview.random_sentence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Given a corpus, build a sentence of desired length by picking a starting word at random,
 * then random randomly picking a word following the selected word in the corpus.
 * <p>
 * Example
 * corpus=this is a sentence it is not a good one and it is also bad
 * length=5
 * <p>
 * The sequence of random numbers [4, 1, 1, 3, 1] generates the solution: "sentence it is also bad"
 */
public class Main {
    private static final Pattern SPACE_SEPARATED = Pattern.compile("[ ,.]+");

    private Main() {
    }

    static Map<String, List<String>> buildFollowers(final String[] words) {
        final var followers = new HashMap<String, List<String>>();
        for (int i = 0; i < words.length; i++) {
            addWord(followers, words[i], nextWord(words, i));
        }
        System.out.println(followers);
        return followers;
    }

    private static void addWord(final Map<String, List<String>> followers, final String key, final String nextWord) {
        followers.putIfAbsent(key, new ArrayList<>());
        followers.get(key).add(nextWord);
    }

    private static String nextWord(final String[] words, final int i) {
        if (i + 1 < words.length) {
            return words[i + 1];
        }
        return words[0];
    }

    public static String sentence(final String input, final int length) {
        final var words = SPACE_SEPARATED.split(input);

        final var followers = buildFollowers(words);

        final var resultWords = new ArrayList<String>();
        final var random = new Random();
        for (var current = words[random.nextInt(words.length)]; resultWords.size() < length; ) {
            resultWords.add(current);

            final var candidates = followers.get(current);
            current = candidates.get(random.nextInt(candidates.size()));
        }

        final var result = String.join(" ", resultWords);
        System.out.println(result);
        return result;
    }

}
