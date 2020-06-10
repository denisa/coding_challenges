package li.antonio.coding_challenges.interview.random_sentence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    /**
     * Paul Verlaine, Chanson d’automne
     */
    private static final String CORPUS = "les sanglots longs " +
            "des violons " +
            "de l’automne " +
            "blessent mon cœur " +
            "d’une langueur " +
            "monotone. " +
            " " +
            "tout suffocant " +
            "et blême, quand " +
            "sonne l’heure, " +
            "je me souviens " +
            "des jours anciens " +
            "et je pleure " +
            " " +
            "et je m’en vais " +
            "au vent mauvais " +
            "qui m’emporte " +
            "deçà, delà, " +
            "pareil à la " +
            "feuille morte.";

    @Test
    void sentence() {
        final var sentence = Main.sentence(CORPUS, 10);
        assertEquals(10, sentence.split(" ").length);
    }
}