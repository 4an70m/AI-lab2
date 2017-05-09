package kpi.labs.ai.lab1.deprecated;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by 4an70m on 01.05.2017.
 */
public class DFSSolver {
    private List<Integer> stacks;
    private LinkedList<HistoryPoint> history;
    private static final int STEP = 3;
    private static final int STACK_SIZE = 2;

    class HistoryPoint {
        private final List<Integer> stack;
        private final int index;

        public HistoryPoint(List<Integer> stack, int index) {
            this.stack = new ArrayList<>(stack);
            this.index = index;
        }

        @Override
        public String toString() {
            return "State{" +
                    "stack=" + stack +
                    ", index=" + index +
                    '}';
        }

        public int getIndex() {
            return index;
        }

        public List<Integer> getStack() {
            return new ArrayList<>(this.stack);
        }
    }

    public DFSSolver(List<Integer> stacks) {
        this.stacks = stacks;
        this.history = new LinkedList<>();
        this.history.add(new HistoryPoint(stacks, 0));
    }

    private int getNextAvailable(int startIndex) {
        for (int i = startIndex; i < stacks.size(); i++) {
            if (stacks.get(i) <= STACK_SIZE && (canMoveLeft(i) || canMoveRight(i))) {
                return i;
            }
        }
        return -1;
    }

    private boolean canMoveLeft(int index) {
        if (index >= stacks.size() || index < 0 || index - STEP < 0 || stacks.get(index) != 1) {
            return false;
        }

        int coins = stacks.get(index - STEP) + 1;
        if (coins > STACK_SIZE) {
            return false;
        }
        return true;
    }

    private boolean moveLeft(int index) {
        if (!canMoveLeft(index)) {
            return false;
        }
        int coins = stacks.get(index - STEP);
        stacks.set(index - STEP, coins + 1);
        stacks.remove(index);
        return true;
    }

    private boolean canMoveRight(int index) {
        if (index >= stacks.size() || index < 0 || index + STEP >= stacks.size() || stacks.get(index) != 1) {
            return false;
        }
        int coins = stacks.get(index + STEP) + 1;
        if (coins > STACK_SIZE) {
            return false;
        }
        return true;
    }

    private boolean moveRight(int index) {
        if (!canMoveRight(index)) {
            return false;
        }
        int coins = stacks.get(index + STEP);
        stacks.set(index + STEP, coins + 1);
        stacks.remove(index);
        return true;
    }

    public void solve(int startIndex) {
        System.out.println(stacks);
        int index = getNextAvailable(startIndex);

        if (moveLeft(index)) {
            history.push(new HistoryPoint(stacks, index + STEP));
            solve(startIndex);
        }

        if (moveRight(index)) {
            history.push(new HistoryPoint(stacks, index + STEP));
            solve(startIndex);
        }

        if (index == -1) {
            history.pop();
            HistoryPoint hp = history.peek();
            stacks = hp.getStack();
            solve(hp.getIndex() + 1);
        }
    }

    public static void main(String[] args) {
        List<Integer> stacks = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            stacks.add(1);
        }
        DFSSolver t = new DFSSolver(stacks);
        t.solve(0);
    }


}
