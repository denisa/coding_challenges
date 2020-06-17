package li.antonio.coding_challenges.interview.word_matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.String.join;
import static java.util.Arrays.asList;

/**
 * Given a list of four-letter words, stack every combination horizontally without duplicating the words.
 * </p>This builds matrix of four 4-letter words.
 *
 * </p>In each matrix, look vertically for words in the original list; if there are matches, emit the matrix and all the matching words.
 * <p>
 * Example:
 * <br>
 * list: <code>prey, yard, lava, even, over, rave, tend</code>
 *
 * </p>The word even is in one of the matrix (capitalized for highlight, not part of the exercise)
 * <pre>
 *   ovEr
 *   laVa
 *   evEn
 *   teNd
 * </pre>
 * Another matrix has the words rave, even, yard
 * <pre>
 *   prey
 *   lava
 *   over
 *   tend
 * </pre>
 * The result for that simple list:
 * <pre>
 * matrix: over, lava, prey, tend; matched: even
 * matrix: over, lava, even, tend; matched: even
 * matrix: over, rave, prey, tend; matched: even
 * matrix: over, rave, even, tend; matched: even
 * matrix: prey, yard, over, tend; matched: rave
 * matrix: prey, yard, even, tend; matched: rave
 * matrix: prey, lava, over, tend; matched: yard, even, rave
 * matrix: prey, lava, over, yard; matched: yard
 * matrix: prey, lava, even, tend; matched: even, rave
 * matrix: prey, rave, over, tend; matched: even, rave
 * matrix: prey, rave, even, tend; matched: even, rave
 * matrix: prey, rave, even, yard; matched: prey
 * matrix: even, lava, over, tend; matched: even
 * matrix: even, lava, prey, tend; matched: even
 * matrix: even, rave, over, tend; matched: even
 * matrix: even, rave, prey, tend; matched: even
 * </pre>
 */
public class Main {
    private static final int WORD_LENGTH = 4;
    private static final List<String> STRINGS = asList("prey", "yard", "lava", "even", "over", "rave", "tend");
    private static final Set<String> INPUT = new HashSet<>();

    private static int matchCount;

    public static void main(final String[] args) {
        INPUT.addAll(STRINGS);
        if (STRINGS.size() != INPUT.size()) {
            System.out.println("Duplicate words in the corpus!");
            System.exit(1);
        }
        combine(new ArrayList<>(new ArrayList<>()), INPUT, 0);
        System.out.println("Find set " + matchCount);
    }

    private static void combine(final List<List<String>> initialCombinations, final Set<String> words, final int size) {
        if (size == WORD_LENGTH) {
            for (final var combination : initialCombinations) {
                testCombination(combination);
            }
            return;
        }

        for (final var word : words) {
            final var combinations = new ArrayList<List<String>>();

            if (initialCombinations.isEmpty()) {
                combinations.add(Collections.singletonList(word));
            } else {
                for (final var initialCombination : initialCombinations) {
                    final var newCombination = new ArrayList<>(initialCombination);
                    newCombination.add(word);
                    combinations.add(newCombination);
                }
            }

            final var remainingWords = new HashSet<>(words);
            remainingWords.remove(word);

            combine(combinations, remainingWords, size + 1);
        }
    }

    private static void testCombination(final List<String> combination) {
        final var matchings = new HashSet<String>();
        for (final var word : pivot(combination)) {
            if (Main.INPUT.contains(word)) {
                matchings.add(word);
            }
        }

        if (!matchings.isEmpty()) {
            System.out.println("matrix: " + join(", ", combination) + "; matched: " + join(", ", matchings));
            matchCount++;
        }

    }

    private static List<String> pivot(final List<String> combination) {
        final var result = new ArrayList<String>();
        for (int i = 0; i < WORD_LENGTH; i++) {
            result.add("");
        }

        for (final String s : combination) {
            final var chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                result.set(i, result.get(i) + chars[i]);
            }

        }
        return result;
    }
}
