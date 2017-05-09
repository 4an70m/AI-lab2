package kpi.labs.ai.lab1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 4an70m on 01.05.2017.
 */
public class TestSolving {
    private static int coincounter = 0;
    private List<CoinsStack> coins;
    private Solver solver;

    public TestSolving() {
        this.coins = new ArrayList<>();
        this.solver = new Solver();
    }

    public void solve() {
        solver.solve(this);
    }

    public CoinsStack getStack(int stackNumber) {
        return coins.get(stackNumber);
    }

    public void addStack(CoinsStack stack) {
        coins.add(stack);
    }

    public static class CoinsStack {
        List<Coin> stack;
        final int maxSize;

        public CoinsStack(Integer maxSize) {
            stack = new LinkedList<>();
            this.maxSize = maxSize;
        }

        public boolean putIfCan(Coin coin) {
            if (stack.size() + 1 > maxSize) {
                return false;
            }
            stack.add(coin);
            return true;
        }

        public boolean moveIfCan() {
            return true;
        }
    }

    public static class Coin {
        int id;
        boolean active;

        public Coin() {
            this.id = ++coincounter;
            this.active = true;
        }
    }

    public class Solver {
        public void solve(TestSolving coins) {

        }

        private void solveBfs() {

        }

    }

    public static void main(String[] args) {
        TestSolving ts = new TestSolving();
        for (int i = 0; i < 4; i++) {
            CoinsStack cs = new CoinsStack(2);
            cs.putIfCan(new Coin());
            ts.addStack(cs);
        }

    }
}
