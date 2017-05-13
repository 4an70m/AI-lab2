package kpi.labs.ai.lab1;

import java.util.*;

/**
 * Created by 4an70m on 10.05.2017.
 */
public class DFSSolverV2 {

    private static final int STEP = 4;
    private static final int STACK_SIZE = 3;

    static class State {
        private final List<Integer> curentState;

        public State(List<Integer> curentState) {
            this.curentState = new ArrayList<>(curentState);
        }

        @Override
        public String toString() {
            return "State{" +
                    "curentState=" + curentState +
                    "}\n";
        }

        public List<State> getNewState() {
            List<State> result = new ArrayList<>();
            for (int i = 0; i < curentState.size(); i++) {
                result.addAll(prepareState(i));
            }
            return result;
        }
        
        private List<State> prepareState(int index) {
            List<State> result = new ArrayList<>();
            result.addAll(moveLeft(index));
            result.addAll(moveRight(index));
            return result;
        }

        private boolean canMoveLeft(int index) {
            if (index >= curentState.size() || index < 0 || index - STEP < 0 || curentState.get(index) != 1) {
                return false;
            }

            int coins = curentState.get(index - STEP) + 1;
            if (coins > STACK_SIZE) {
                return false;
            }
            return true;
        }

        private List<State> moveLeft(int index) {
            List<State> result = new ArrayList<>();
            if (!canMoveLeft(index)) {
                return result;
            }
            List<Integer> newStack = new ArrayList<>(this.curentState);
            int coins = newStack.get(index - STEP);
            newStack.set(index - STEP, coins + 1);
            newStack.remove(index);
            result.add(new State(newStack));
            return result;
        }

        private boolean canMoveRight(int index) {
            if (index >= curentState.size() || index < 0 || index + STEP >= curentState.size() || curentState.get(index) != 1) {
                return false;
            }
            int coins = curentState.get(index + STEP) + 1;
            if (coins > STACK_SIZE) {
                return false;
            }
            return true;
        }

        private List<State> moveRight(int index) {
            List<State> result = new ArrayList<>();
            if (!canMoveRight(index)) {
                return result;
            }
            List<Integer> newStack = new ArrayList<>(this.curentState);
            int coins = newStack.get(index + STEP);
            newStack.set(index + STEP, coins + 1);
            newStack.remove(index);
            result.add(new State(newStack));
            return result;
        }

        public List<Integer> getCurentState() {
            return new ArrayList<>(this.curentState);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            State state = (State) o;

            return curentState.equals(state.curentState);
        }

        @Override
        public int hashCode() {
            return curentState.hashCode();
        }
    }

    public void solveDFS(State state) {
        for (State newState : state.getNewState()) {
            System.out.println(newState);
            if (isSolved(newState)) {
                System.out.println("Solved");
                System.exit(0);
            }
            solveDFS(newState);
        }
    }

    public void solveBFS(State state) {
        Queue<State> agenda = new LinkedList<>();
        agenda.add(state);

        while (!agenda.isEmpty()) {
            State curState = (State) agenda.remove();
            Set<State> st = new HashSet<>(agenda);
            for (State newState : curState.getNewState()) {
                //System.out.println(newState);
                if (isSolved(state)) {
                    System.out.println("Solved");
                    return;
                }
                st.addAll(newState.getNewState());
            }
            agenda = new LinkedList<>(st);
        }
    }

    public boolean isSolved(State state) {
        List<Integer> curState = state.getCurentState();
        if(curState.size() != 5) {
            return false;
        }
        for (Integer i : curState) {
            if(i != 3) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<Integer> curentStack = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            curentStack.add(1);
        }

        State st = new State(curentStack);
        DFSSolverV2 dv2 = new DFSSolverV2();
        //dv2.solveDFS(st);
        dv2.solveBFS(st);
    }

}
