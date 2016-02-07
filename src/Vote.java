import javax.swing.JOptionPane;

/**
 * 
 * @author James
 *
 */
public class Vote {
	//initialize variables
	private int candidates, voters, winner, most = 0;
	int[][] voter;
	int[] finalVote;
	int [][] vote;
	
	//default constructor
	public Vote(){
		setCandidates();
		setVoters();
		
	}
	
	//constructor with values
	public Vote(int v, int c){
		candidates = c;
		voters = v;
		
		//set arrays
		voter = new int[voters][candidates];
		vote = new int[candidates][candidates];
		finalVote = new int[candidates];
	}
	
	//set voters and voter array
	public void setVoters(){
		voters = Integer.parseInt(JOptionPane.showInputDialog(null,
				"How many people are voting?","Voters",
				JOptionPane.QUESTION_MESSAGE));
		voter = new int[voters][candidates];
	}
	
	//set candidates and vote arrays
	public void setCandidates(){
		candidates = Integer.parseInt(JOptionPane.showInputDialog(null,
				"How many people are running?","Voters",
				JOptionPane.QUESTION_MESSAGE));
		finalVote = new int[candidates];
		vote = new int[candidates][candidates];
	}
	
	//collect votes from users
	public void collectVotes(){
		String choice;
		int cand = 0, start = 0, end = 1;;	
		for (int i = 0; i < voters; i++){
			choice = (JOptionPane.showInputDialog(null,
					"Please list the candidates you want in order of preference."
					+ "\nPlease list them in the following format 0,1,2,3,4\nPlease include all "
					+ "candidates in your choice.","Voters",
					JOptionPane.QUESTION_MESSAGE));
			
			//check preference order
			for (int e = 0; e < candidates; e++){
			
			//check the integer at intervals to determine what candidate is next	
			if (end < choice.length()){
				cand = Integer.parseInt(choice.substring(start, end));
			} else {
				cand = Integer.parseInt(choice.substring(start));
			}
			
			//move location in string
			start += 2;
			end += 2;
			
			//set location of candidate for each voter
			voter[i][cand] = e + 1;
			}
			
			//reset location to the beginning of the string
			start = 0;
			end = 1;
		}
	}
	
	//decide the winner
	public void decideWinner(){
		
		//create loop to account for each voter
		for (int i = 0; i < voters;i++){
			//create loop for each position (ie. first choice, second...)
			for (int k = 0; k < candidates; k++){
				//create loop for each candidate
			for (int e = 0; e < candidates; e++){
				//check if the voter placed each cnadidate in current slot
				if (voter[i][e] == k+1){
					vote[e][k] ++;
				}
				
			}
			
			}
		}
		//test a three candidate system
		System.out.println(vote[0][0]);
		System.out.println(vote[0][1]);
		System.out.println(vote[0][2]);
		System.out.println();
		System.out.println(vote[1][0]);
		System.out.println(vote[1][1]);
		System.out.println(vote[1][2]);
		System.out.println();
		System.out.println(vote[2][0]);
		System.out.println(vote[2][1]);
		System.out.println(vote[2][2]);
		
		//calculate the final vote for each candidate  
		for (int i = 0; i < candidates; i ++){
			for (int e = 0; e < candidates; e ++){
				if ((candidates - (e+1)) == 0){
					finalVote[i] += vote[i][e] * -1;
				}
				finalVote[i] += vote[i][e] * (candidates - (e + 1));
				
			}
		}
		
		//determine which candidate has the most votes
		for (int i = 0; i < candidates; i ++){
			if (finalVote[i] > most){
				most = finalVote[i];
				winner = i;
			}
		}
		
		//test a three candidate system
		System.out.println(finalVote[0]);
		System.out.println(finalVote[1]);
		System.out.println(finalVote[2]);
	}
	
	//output winner
	public void printWinner(){
		JOptionPane.showMessageDialog(null,
				"The winner is candidate " + winner,"Voters",
				JOptionPane.INFORMATION_MESSAGE);
	}
	//lukes shinanagins
	public void printWinner(String meme) {
		for (int i = 0; i < 1000; i++)
			System.out.print(meme);
	}
}
