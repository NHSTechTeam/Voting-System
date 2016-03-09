package voteCalculations;

import java.util.ArrayList;

public class Manager {
	//variables
	private ArrayList<Candidate> options = new ArrayList<Candidate>();
	private ArrayList<Candidate> possible = new ArrayList<Candidate>();
	private ArrayList<Voter> voter = new ArrayList<Voter>();
	private int indexP, indexO;
	
	public Manager(){
		
	}
	
	public void setCandidates(ArrayList<Candidate> list){
		options = list;
		possible = list;
	}
	
	public ArrayList<Candidate> getCandidate(){
		return options;
	}
	public void addVoter(int[] num){
		voter.add(new Voter(num, options));
	}
	
	public Candidate leastVotes(ArrayList<Candidate> list){
		for (int i = list.size() - 2; i > -1; i --){
			if (list.get(i).getVotes() > list.get(i + 1).getVotes()){
				list.remove(i);
			} else {
				list.remove(i+1);
			}
		}
		return list.get(0);
	}

	public Candidate calculateWinner(){
		int lose;
		boolean tie = false;
		String pTie = "";
		int rep = possible.size() - 1;
		for (int e = 0; e < rep; e ++){
			lose = 0;
		for (int i = 0; i < voter.size(); i ++){
			voter.get(i).getChoice().get(0).addVote();
		}
		
		for (int i = 1; i < possible.size(); i ++){
			System.out.println(possible.get(i).getVotes());
			if (possible.get(i).getVotes() < possible.get(lose).getVotes()){
				lose = i;
			}
			System.out.println(lose);
			System.out.println(possible.get(lose));
		}
		for (int i = 0; i < voter.size(); i ++){
				voter.get(i).removeLoser(possible.get(lose));
		}
		if ((possible.size() == 2) && (possible.get(0).getVotes() == possible.get(1).getVotes())){
		tie = true;
		pTie = "Tie between " + possible.get(0).getName() + " and " + possible.get(1).getName();
		}
		possible.remove(lose);
		System.out.println("Current 0 = " + possible.get(0));
		
		for (int i = 0; i < options.size(); i ++){
			options.get(i).resetVotes();
		}
		
		}
		
		if (tie){
			return new Candidate(pTie);
		}
		return possible.get(0);
		
		
		
	}
	
	
}
