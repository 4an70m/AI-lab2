package kpi.labs.ai.lab1.coinproblem;

/**
 * Created by 4an70m on 09.04.2017.
 */
public class Coin {

    private static int counter = 0;
    private String name;

    public Coin() {
        this.name = "Coin-" + String.valueOf(++counter);
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
