package li.antonio.coding_challenges.interview.trade.in_memory;

import li.antonio.coding_challenges.interview.trade.Quote;
import li.antonio.coding_challenges.util.ClockControlExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;

class SymbolBookTest {
    @RegisterExtension
    ClockControlExtension clockControlExtension = QuoteManagerTest.setTestClock("2021-07-04T00:00:00Z");

    @Test
    void addUpdateRemove() {
        final var sb = new SymbolBook("UBS");
        final var uuid = UUID.randomUUID();
        {
            final var quote = new Quote();
            quote.setId(uuid);
            quote.setSymbol("UBS");
            quote.setAvailableVolume(750);
            quote.setPrice(1.0);
            quote.setExpirationDate(Instant.parse("2020-07-09T00:00:00Z"));
            sb.addOrUpdate(quote);
            assertNotSame(quote, sb.getQuote(uuid));
        }
        assertEquals(750, sb.getQuote(uuid).getAvailableVolume());
        {
            final var quote = new Quote();
            quote.setId(uuid);
            quote.setSymbol("UBS");
            quote.setAvailableVolume(50);
            quote.setPrice(1.0);
            quote.setExpirationDate(Instant.parse("2021-07-09T00:00:00Z"));
            sb.addOrUpdate(quote);
            assertNotSame(quote, sb.getQuote(uuid));
        }
        assertEquals(50, sb.getQuote(uuid).getAvailableVolume());
        sb.remove(uuid);
        assertNull(sb.getQuote(uuid));
    }

    @Test
    void getBestQuoteWithAvailableVolume() {
        final var sb = new SymbolBook("UBS");
        { // most expansive quote
            final var quote = new Quote();
            quote.setId(UUID.randomUUID());
            quote.setSymbol("UBS");
            quote.setAvailableVolume(750);
            quote.setPrice(3.0);
            quote.setExpirationDate(Instant.parse("2021-07-09T00:00:00Z"));
            sb.addOrUpdate(quote);
        }
        { // nothing available
            final var quote = new Quote();
            quote.setId(UUID.randomUUID());
            quote.setSymbol("UBS");
            quote.setAvailableVolume(0);
            quote.setPrice(1.0);
            quote.setExpirationDate(Instant.parse("2021-12-24T00:00:00Z"));
            sb.addOrUpdate(quote);
        }
        {
            final var quote = new Quote();
            quote.setId(UUID.randomUUID());
            quote.setSymbol("UBS");
            quote.setAvailableVolume(50);
            quote.setPrice(2.0);
            quote.setExpirationDate(Instant.parse("2021-07-09T00:00:00Z"));
            sb.addOrUpdate(quote);
        }
        { // quote has expired
            final var quote = new Quote();
            quote.setId(UUID.randomUUID());
            quote.setSymbol("UBS");
            quote.setAvailableVolume(5000);
            quote.setPrice(0.5);
            quote.setExpirationDate(Instant.parse("2018-01-09T00:00:00Z"));
            sb.addOrUpdate(quote);
        }
        final var quote = sb.getBestQuoteWithAvailableVolume();
        assertNotNull(quote);
        assertEquals(50, quote.getAvailableVolume());
        assertEquals(2.0, quote.getPrice());
    }
}