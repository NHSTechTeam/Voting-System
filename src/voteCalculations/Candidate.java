package voteCalculations;

public class Candidate {
    // variables
    private final String name;
    private int votes;

    public Candidate(String word) {
        name = word;
        votes = 0;
    }

    public String getName() {
        return name;
    }

    public void addVote() {
        votes++;
    }

    public int getVotes() {
        return votes;
    }

    public void resetVotes() {
        votes = 0;
    }

    public String toString() {
        return name + votes;
    }
}
