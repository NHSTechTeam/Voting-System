package voteCalculations;

import java.util.ArrayList;

/**
 * @author James
 */
class Voter {
    // variables
    private final ArrayList<Candidate> choice = new ArrayList<>();
    private final int[] pref;

    public Voter(int[] num, ArrayList<Candidate> list) {
        pref = num;
        setCandidate(list);
    }

    private void setCandidate(ArrayList<Candidate> list) {
        for (int aPref : pref) {
            System.out.println(list.get(aPref).getName());
            choice.add(list.get(aPref));
        }
    }

    public void removeLoser(Candidate lost) {
        choice.remove(choice.indexOf(lost));
    }

    public ArrayList<Candidate> getChoice() {
        return choice;
    }
}
