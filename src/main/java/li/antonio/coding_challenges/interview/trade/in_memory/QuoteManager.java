package li.antonio.coding_challenges.interview.trade.in_memory;

import li.antonio.coding_challenges.interview.trade.Quote;
import li.antonio.coding_challenges.interview.trade.TradeResult;

import java.time.Clock;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QuoteManager implements li.antonio.coding_challenges.interview.trade.QuoteManager {
    static Clock tradingClock = Clock.systemUTC();
    /**
     * An index of SymbolBook by the symbol they manage. Provide for efficient lookup.
     */
    private final Map<String, li.antonio.coding_challenges.interview.trade.SymbolBook> symbolBookBySymbol = new HashMap<>();
    /**
     * An index of SymbolBook by the id of the Quote they contains. Provide for efficient lookup.
     */
    private final Map<UUID, li.antonio.coding_challenges.interview.trade.SymbolBook> symbolBookById = new HashMap<>();

    @Override
    public void addOrUpdateQuote(final Quote quote) {
        var symbolBook = symbolBookById.get(quote.getId());
        if (symbolBook == null) {
            symbolBook = symbolBookBySymbol.get(quote.getSymbol());
            if (symbolBook == null) {
                symbolBook = new SymbolBook(quote.getSymbol());
                symbolBookBySymbol.put(quote.getSymbol(), symbolBook);
            }
            symbolBookById.put(quote.getId(), symbolBook);
        }
        symbolBook.addOrUpdate(quote);
    }

    @Override
    public void removeQuote(final UUID id) {
        final var symbolBook = symbolBookById.remove(id);
        if (symbolBook == null) {
            return;
        }
        symbolBook.remove(id);
    }

    @Override
    public void removeAllQuotes(final String symbol) {
        final var symbolBook = symbolBookBySymbol.remove(symbol);
        if (symbolBook != null) {
            symbolBook.forEach(q -> symbolBookById.remove(q.getId()));
        }
    }

    @Override
    public Quote getBestQuoteWithAvailableVolume(final String symbol) {
        final var symbolBook = symbolBookBySymbol.get(symbol);
        if (symbolBook == null) {
            return null;
        }
        return symbolBook.getBestQuoteWithAvailableVolume();
    }

    @Override
    public TradeResult executeTrade(final String symbol, final int volumeRequested) {
        final var symbolBook = symbolBookBySymbol.get(symbol);
        if (symbolBook == null) {
            final var result = new TradeResult();
            result.setID(UUID.randomUUID());
            result.setSymbol(symbol);
            result.setVolumeRequested(volumeRequested);
            return result;
        }
        return symbolBook.executeTrade(volumeRequested);
    }

    /**
     * For testing only, returns the symbol book associated with the symbol.
     * Returns null if no book is defined
     */
    SymbolBook getBook(final String symbol) {
        return (SymbolBook) symbolBookBySymbol.get(symbol);
    }

    /**
     * For testing only, sets an alternate clock.
     * Production code would need a global solution.
     * Missing JodaTime here...
     *
     * @return the previous clock
     */
    public static Clock setClock(final Clock clock) {
        final var previous = tradingClock;
        tradingClock = clock;
        return previous;
    }

}
