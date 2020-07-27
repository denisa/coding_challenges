package li.antonio.coding_challenges.interview.trade.in_memory;

import li.antonio.coding_challenges.interview.trade.Quote;
import li.antonio.coding_challenges.interview.trade.TradeResult;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import static java.lang.Math.min;
import static java.util.Comparator.comparingDouble;

public class SymbolBook implements li.antonio.coding_challenges.interview.trade.SymbolBook {
    private final String symbol;
    /**
     * An index of Quote by their id. Provide for efficient lookup.
     */
    private final Map<UUID, Quote> quoteById = new HashMap<>();

    public SymbolBook(final String symbol) {
        this.symbol = symbol;
    }

    @Override
    public void addOrUpdate(final Quote quote) {
        if (!symbol.equals(quote.getSymbol())) {
            throw new IllegalArgumentException("Quote for " + quote.getSymbol() + " added to symbol book for " + symbol);
        }

        final var currentQuote = quoteById.get(quote.getId());
        if (currentQuote == null) {
            quoteById.put(quote.getId(), copyOf(quote));
            return;
        }
        currentQuote.setPrice(quote.getPrice());
        currentQuote.setAvailableVolume(quote.getAvailableVolume());
    }

    /**
     * present for testing
     *
     * @param id the id of the quote
     * @return the desired quote or null
     */
    public Quote getQuote(final UUID id) {
        return copyOf(quoteById.get(id));
    }

    @Override
    public void remove(final UUID id) {
        quoteById.remove(id);
    }

    @Override
    public Quote getBestQuoteWithAvailableVolume() {
        final var queryTime = Instant.now(QuoteManager.tradingClock);
        return quoteById
                .values()
                .stream()
                .filter(q -> q.getAvailableVolume() > 0)
                .filter(q -> q.getExpirationDate().isAfter(queryTime))
                .min(comparingDouble(Quote::getPrice))
                .map(SymbolBook::copyOf)
                .orElse(null);
    }

    /*
    Computing the VolumeWeightedAveragePrice might overflow in this method.
    Here, as in other places, the use of a Currency class instead of double, would solve that.
    */
    @Override
    public TradeResult executeTrade(final int volumeRequested) {
        final var result = new TradeResult();
        result.setID(UUID.randomUUID());
        result.setSymbol(symbol);
        result.setVolumeRequested(volumeRequested);

        var totalTradePrice = 0.0;
        var leftToFulfill = volumeRequested;
        while (leftToFulfill > 0) {
            final var quote = getBestQuoteWithAvailableVolume();
            if (quote == null) {
                break;
            }
            final var volumeToUse = min(quote.getAvailableVolume(), leftToFulfill);
            leftToFulfill -= volumeToUse;
            quote.setAvailableVolume(quote.getAvailableVolume() - volumeToUse);
            addOrUpdate(quote);
            totalTradePrice += volumeToUse * quote.getPrice();
        }

        final var volumeExecuted = volumeRequested - leftToFulfill;
        result.setVolumeExecuted(volumeExecuted);
        result.setVolumeWeightedAveragePrice(totalTradePrice / volumeExecuted);
        return result;
    }

    @Override
    public void forEach(final Consumer<? super Quote> action) {
        quoteById.values().forEach(quote -> action.accept(copyOf(quote)));
    }

    /**
     * Makes a defensive copy of the quote.
     * Quote is a mutable object and we do not want the values in this symbol book to be modified outside of this class control.
     * <p>
     * Should this copying of object become too expensive, we could introduce a thin immutable quote wrapper
     * (along to the Unmodifiable collection pattern and let the client duplicate the Quote as needed)
     * <p>
     * This method belongs to Quote, but the exercise asks not to modify the existing classes.
     *
     * @param quote a quote
     * @return a copy of the quote
     */
    private static Quote copyOf(final Quote quote) {
        if (quote == null) {
            return null;
        }
        final var result = new Quote();
        result.setSymbol(quote.getSymbol());
        result.setAvailableVolume(quote.getAvailableVolume());
        result.setExpirationDate(quote.getExpirationDate());
        result.setId(quote.getId());
        result.setPrice(quote.getPrice());
        return result;
    }
}
