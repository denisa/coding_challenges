package li.antonio.coding_challenges.interview.trade;

import java.time.Instant;
import java.util.UUID;

public class Quote {
    private UUID id;
    private String symbol;
    private double price;
    private int availableVolume;
    private Instant expirationDate;

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public int getAvailableVolume() {
        return availableVolume;
    }

    public void setAvailableVolume(final int availableVolume) {
        this.availableVolume = availableVolume;
    }

    public Instant getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(final Instant date) {
        expirationDate = date;
    }
}
