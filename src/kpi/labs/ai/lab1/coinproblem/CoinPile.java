package kpi.labs.ai.lab1.coinproblem;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/**
 * Created by 4an70m on 09.04.2017.
 */
public class CoinPile {

    private Deque<Coin> pile;

    public CoinPile(Coin coin) {
        this.pile = new ArrayDeque<>();
        this.pushCoin(coin);
    }

    public CoinPile() {
        this.pile = new ArrayDeque<>();
    }

    public void pushCoin(Coin coin) {
        this.pile.add(coin);
    }

    public Coin popCoin() {
        Coin result = null;
        if (!this.pile.isEmpty()) {
            result = this.pile.pop();
        }
        if (result.wasCoinMoved()) {

        }
        result.moveCoin();
        return result;
    }

    public int pileSize() {
        return this.pile.size();
    }

    @Override
    public String toString() {
        return this.pile + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoinPile coinPile = (CoinPile) o;

        return pile.equals(coinPile.pile);
    }

    @Override
    public int hashCode() {
        return pile.hashCode();
    }

    public boolean hasCoin() {
        return !pile.isEmpty();
    }

    public Coin peekCoin() {
        return pile.peekLast();
    }
}
