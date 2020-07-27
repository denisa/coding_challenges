package li.antonio.coding_challenges.interview.trade;

import java.util.UUID;
import java.util.function.Consumer;

/**
 * A SymbolBook collects all quotes related to a symbol.
 */
public interface SymbolBook {
    /**
     * Add or update the quote (specified by ID) in symbol's book.
     * If quote is new or no longer in the book, add it. Otherwise update it to
     * match the given price, volume, and symbol.
     *
     * @param quote The quote to add.
     */
    void addOrUpdate(Quote quote);

    /**
     * Remove quote by ID. If the quote is no longer in symbol's book, do nothing.
     *
     * @param id The ID of the quote to remove.
     */
    void remove(UUID id);

    /**
     * Get the best (i.e. lowest) price in the symbol's book that still has
     * available volume. If there is no quote on the symbol's book with available
     * volume, return null. Otherwise return a Quote object with all the fields
     * set. Don't return any quote which is past its expiration time, or has been
     * removed.
     */
    Quote getBestQuoteWithAvailableVolume();

    /**
     * Request that a trade be executed. For the purposes of this interface,
     * assume that the trade is a request to BUY, not sell. Do not trade on
     * expired quotes. To execute a trade:
     * * Search available quotes of the specified symbol from best price to
     * worst price.
     * * Until the requested volume has been filled, use as much available
     * volume as necessary (up to what is available) from each quote,
     * subtracting the used amount from the available amount.
     *
     * @param volumeRequested The requested volume to purchase.
     */
    TradeResult executeTrade(int volumeRequested);

    /**
     * Performs the given action for each Quote in this symbol book
     * until all elements have been processed or the action throws an
     * exception.  Actions are performed in the order of iteration, if that
     * order is specified.  Exceptions thrown by the action are relayed to the
     * caller.
     * <p>
     * Redefined here, instead of implementing Iterable to ensure a read-only view of the quotes.
     *
     * @param action The action to be performed for each element
     * @throws NullPointerException if the specified action is null
     */
    void forEach(Consumer<? super Quote> action);

}
