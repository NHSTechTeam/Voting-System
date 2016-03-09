package voteCalculations;

import java.util.ArrayList;

/**
 * 
 * @author James
 *
 */
public class Voter {
	//variables
	private ArrayList<Candidate> choice = new ArrayList<Candidate>();
	private int[] pref;
	
	
	public Voter(int[] num, ArrayList<Candidate> list){
		pref = num;
		setCandidate(list);
	}
	public void setCandidate(ArrayList<Candidate> list){
		for (int i = 0; i < pref.length; i ++){
			System.out.println(list.get(pref[i]).getName());
			choice.add(list.get(pref[i]));
		}
	}
	public void removeLoser(Candidate lost){
		choice.remove(choice.indexOf(lost));
	}
	public ArrayList<Candidate> getChoice(){
		return choice;
	}
}
