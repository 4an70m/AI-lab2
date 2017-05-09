package kpi.labs.ai.lab1.deprecated;

/**
 * Created by 4an70m on 09.04.2017.
 */
public class Coin {

    private static int counter = 0;
    private String name;
    private Boolean wasCoinMoved;

    public Coin() {
        this.name = "Coin-" + String.valueOf(++counter);
        this.wasCoinMoved = false;
    }

    public Boolean wasCoinMoved() {
        return wasCoinMoved;
    }

    public void moveCoin() {
        this.wasCoinMoved = true;
    }

    public void setWasCoinMoved(Boolean wasCoinMoved) {
        this.wasCoinMoved = wasCoinMoved;
    }

    @Override
    public String toString() {
        return "" + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coin coin = (Coin) o;

        return name.equals(coin.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
