package li.antonio.coding_challenges.interview.trade.in_memory;

import li.antonio.coding_challenges.interview.trade.Quote;
import li.antonio.coding_challenges.util.ClockControlExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static java.time.Clock.fixed;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuoteManagerTest {
    @RegisterExtension
    ClockControlExtension clockControlExtension = QuoteManagerTest.setTestClock("2020-07-04T00:00:00Z");

    @Test
    void removeQuoteFromEmptyManager() {
        final var qm = new QuoteManager();
        qm.removeQuote(UUID.randomUUID());
        assertEquals(0, QuoteManagerTest.getAllQuotes(qm, "UBS").size());
    }

    @Test
    void removeQuote() {
        final var qm = new QuoteManager();
        final var firstUUID = UUID.randomUUID();
        {
            final var quote = new Quote();
            quote.setId(firstUUID);
            quote.setSymbol("UBS");
            quote.setAvailableVolume(750);
            quote.setPrice(3.0);
            quote.setExpirationDate(Instant.parse("2021-07-09T00:00:00Z"));
            qm.addOrUpdateQuote(quote);
        }
        {
            final var quote = new Quote();
            quote.setId(UUID.randomUUID());
            quote.setSymbol("UBS");
            quote.setAvailableVolume(0);
            quote.setPrice(1.0);
            quote.setExpirationDate(Instant.parse("2021-12-24T00:00:00Z"));
            qm.addOrUpdateQuote(quote);
        }
        {
            final var quote = new Quote();
            quote.setId(UUID.randomUUID());
            quote.setSymbol("UBS");
            quote.setAvailableVolume(50);
            quote.setPrice(2.0);
            quote.setExpirationDate(Instant.parse("2021-07-09T00:00:00Z"));
            qm.addOrUpdateQuote(quote);
        }
        assertEquals(3, QuoteManagerTest.getAllQuotes(qm, "UBS").size());
        qm.removeQuote(firstUUID);
        assertEquals(2, QuoteManagerTest.getAllQuotes(qm, "UBS").size());
        // second call is a no-op
        qm.removeQuote(firstUUID);
        assertEquals(2, QuoteManagerTest.getAllQuotes(qm, "UBS").size());
    }

    @Test
    void removeAllQuotesFromEmptyManager() {
        final var qm = new QuoteManager();
        qm.removeAllQuotes("UBS");
        assertEquals(0, QuoteManagerTest.getAllQuotes(qm, "UBS").size());
    }

    @Test
    void removeAllQuotes() {
        final var qm = new QuoteManager();
        {
            final var quote = new Quote();
            quote.setId(UUID.randomUUID());
            quote.setSymbol("UBS");
            quote.setAvailableVolume(750);
            quote.setPrice(3.0);
            quote.setExpirationDate(Instant.parse("2021-07-09T00:00:00Z"));
            qm.addOrUpdateQuote(quote);
        }
        {
            final var quote = new Quote();
            quote.setId(UUID.randomUUID());
            quote.setSymbol("UBS");
            quote.setAvailableVolume(0);
            quote.setPrice(1.0);
            quote.setExpirationDate(Instant.parse("2021-12-24T00:00:00Z"));
            qm.addOrUpdateQuote(quote);
        }
        {
            final var quote = new Quote();
            quote.setId(UUID.randomUUID());
            quote.setSymbol("UBS");
            quote.setAvailableVolume(50);
            quote.setPrice(2.0);
            quote.setExpirationDate(Instant.parse("2021-07-09T00:00:00Z"));
            qm.addOrUpdateQuote(quote);
        }
        assertEquals(3, QuoteManagerTest.getAllQuotes(qm, "UBS").size());
        qm.removeAllQuotes("UBS");
        assertEquals(0, QuoteManagerTest.getAllQuotes(qm, "UBS").size());
    }

    /**
     * Test helper
     *
     * @param qm     the QuoteManager instance
     * @param symbol the symbol to look-up
     * @return all quotes for the symbol, sorted by increasing price
     */
    public static List<Quote> getAllQuotes(final QuoteManager qm, final String symbol) {
        final var book = qm.getBook(symbol);
        if (book == null) {
            return List.of();
        }

        final var quotes = new ArrayList<Quote>();
        book.forEach(quotes::add);
        quotes.sort(Comparator.comparingDouble(Quote::getPrice));
        return quotes;
    }

    /**
     * Test helper.
     * Ensure test clock is set to a fixed time
     *
     * @param testTime the instant (iso 8601 formatted) at which the test happens.
     * @return a junit 5 extension that will set the clock to the time before running a test and reset afterward.
     */
    public static ClockControlExtension setTestClock(final String testTime) {
        final var instant = ZonedDateTime.parse(testTime);
        return new ClockControlExtension(
                fixed(instant.toInstant(), ZoneId.from(instant)),
                QuoteManager::setClock
        );
    }
}