package li.antonio.coding_challenges.interview.trade;

import li.antonio.coding_challenges.interview.trade.in_memory.QuoteManager;
import li.antonio.coding_challenges.util.ClockControlExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.Instant;
import java.util.UUID;

import static li.antonio.coding_challenges.interview.trade.in_memory.QuoteManagerTest.getAllQuotes;
import static li.antonio.coding_challenges.interview.trade.in_memory.QuoteManagerTest.setTestClock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class QuoteManagerTest {
    @RegisterExtension
    ClockControlExtension clockControlExtension = setTestClock("2020-07-04T00:00:00Z");

    @Test
    void tradingExamplePerJavadoc() {
        final var qm = new QuoteManager();
        {
            final var quote = new Quote();
            quote.setId(UUID.randomUUID());
            quote.setSymbol("UBS");
            quote.setAvailableVolume(750);
            quote.setPrice(1.0);
            quote.setExpirationDate(Instant.parse("2021-07-09T00:00:00Z"));
            qm.addOrUpdateQuote(quote);
        }
        {
            final var quote = new Quote();
            quote.setId(UUID.randomUUID());
            quote.setSymbol("UBS");
            quote.setAvailableVolume(1000);
            quote.setPrice(2.0);
            quote.setExpirationDate(Instant.parse("2021-05-09T00:00:00Z"));
            qm.addOrUpdateQuote(quote);
        }
        {
            final var quotes = getAllQuotes(qm, "UBS");
            {
                final var quote = quotes.get(0);
                assertEquals(1.0, quote.getPrice());
                assertEquals(750, quote.getAvailableVolume());
            }
            {
                final var quote = quotes.get(1);
                assertEquals(2.0, quote.getPrice());
                assertEquals(1000, quote.getAvailableVolume());
            }
        }
        {
            final var tradeResult = qm.executeTrade("UBS", 500);
            assertEquals(1.0, tradeResult.getVolumeWeightedAveragePrice());
        }
        {
            final var quotes = getAllQuotes(qm, "UBS");
            {
                final var quote = quotes.get(0);
                assertEquals(1.0, quote.getPrice());
                assertEquals(250, quote.getAvailableVolume());
            }
            {
                final var quote = quotes.get(1);
                assertEquals(2.0, quote.getPrice());
                assertEquals(1000, quote.getAvailableVolume());
            }
        }
        {
            final var tradeResult = qm.executeTrade("UBS", 500);
            assertEquals((1.0 * 250 + 2.0 * 250) / 500, tradeResult.getVolumeWeightedAveragePrice());
        }
        {
            final var quotes = getAllQuotes(qm, "UBS");
            {
                final var quote = quotes.get(0);
                assertEquals(1.0, quote.getPrice());
                assertEquals(0, quote.getAvailableVolume());
            }
            {
                final var quote = quotes.get(1);
                assertEquals(2.0, quote.getPrice());
                assertEquals(750, quote.getAvailableVolume());
            }
        }
    }

    @Test
    void getBestQuoteWithAvailableVolume() {
        final var qm = new QuoteManager();
        { // most expansive quote
            final var quote = new Quote();
            quote.setId(UUID.randomUUID());
            quote.setSymbol("UBS");
            quote.setAvailableVolume(750);
            quote.setPrice(3.0);
            quote.setExpirationDate(Instant.parse("2021-07-09T00:00:00Z"));
            qm.addOrUpdateQuote(quote);
        }
        { // nothing available
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
        { // quote has expired
            final var quote = new Quote();
            quote.setId(UUID.randomUUID());
            quote.setSymbol("UBS");
            quote.setAvailableVolume(5000);
            quote.setPrice(0.5);
            quote.setExpirationDate(Instant.parse("2018-01-09T00:00:00Z"));
            qm.addOrUpdateQuote(quote);
        }
        final var quote = qm.getBestQuoteWithAvailableVolume("UBS");
        assertNotNull(quote);
        assertEquals(50, quote.getAvailableVolume());
        assertEquals(2.0, quote.getPrice());
    }

}
