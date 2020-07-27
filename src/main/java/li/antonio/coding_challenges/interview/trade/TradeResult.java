package li.antonio.coding_challenges.interview.trade;

import java.util.UUID;

public class TradeResult {
    private UUID id;
    private String symbol;
    private double volumeWeightedAveragePrice;
    private int volumeRequested;
    private int volumeExecuted;

    public UUID getID() {
        return id;
    }

    public void setID(final UUID id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    public double getVolumeWeightedAveragePrice() {
        return volumeWeightedAveragePrice;
    }

    public void setVolumeWeightedAveragePrice(final double price) {
        volumeWeightedAveragePrice = price;
    }

    public int getVolumeRequested() {
        return volumeRequested;
    }

    public void setVolumeRequested(final int volume) {
        volumeRequested = volume;
    }

    public int getVolumeExecuted() {
        return volumeExecuted;
    }

    public void setVolumeExecuted(final int volume) {
        volumeExecuted = volume;
    }
}
