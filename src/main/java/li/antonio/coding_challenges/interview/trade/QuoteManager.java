package li.antonio.coding_challenges.interview.trade;

import java.util.UUID;

/**
 * Implement the QuoteManager interface in a sub-package without modifying anything in this package.
 * <p>
 * This is a take home, 24-hours exercise.
 */
public interface QuoteManager {
    /**
     * Add or update the quote (specified by ID) in symbol's book.
     * If quote is new or no longer in the book, add it. Otherwise update it to
     * match the given price, volume, and symbol.
     *
     * @param quote The quote to add.
     */
    void addOrUpdateQuote(final Quote quote);

    /**
     * Remove quote by ID. If the quote is no longer in symbol's book, do nothing.
     *
     * @param id The ID of the quote to remove.
     */
    void removeQuote(final UUID id);

    /**
     * Remove all quotes on the specified symbol's book.
     *
     * @param symbol The symbol.
     */
    void removeAllQuotes(final String symbol);

    /**
     * Get the best (i.e. lowest) price in the symbol's book that still has
     * available volume. If there is no quote on the symbol's book with available
     * volume, return null. Otherwise return a Quote object with all the fields
     * set. Don't return any quote which is past its expiration time, or has been
     * removed.
     *
     * @param symbol The symbol to get a quote for.
     */
    Quote getBestQuoteWithAvailableVolume(final String symbol);

    /**
     * Request that a trade be executed. For the purposes of this interface,
     * assume that the trade is a request to BUY, not sell. Do not trade on
     * expired quotes.
     * <p>
     * To execute a trade:
     * * Search available quotes of the specified symbol from best price to
     * worst price.
     * * Until the requested volume has been filled, use as much available
     * volume as necessary (up to what is available) from each quote,
     * subtracting the used amount from the available amount.
     * <p>
     * For example, we have two quotes:
     * {Price: 1.0, Volume: 1,000, AvailableVolume: 750}
     * {Price: 2.0, Volume: 1,000, AvailableVolume: 1,000}
     * After calling once for 500 volume, the quotes are:
     * {Price: 1.0, Volume: 1,000, AvailableVolume: 250}
     * {Price: 2.0, Volume: 1,000, AvailableVolume: 1,000}
     * And after calling this a second time for 500 volume, the quotes are:
     * {Price: 1.0, Volume: 1,000, AvailableVolume: 0}
     * {Price: 2.0, Volume: 1,000, AvailableVolume: 750}
     *
     * @param symbol          The symbol to trade.
     * @param volumeRequested The requested volume to purchase.
     */
    TradeResult executeTrade(final String symbol, int volumeRequested);
}
