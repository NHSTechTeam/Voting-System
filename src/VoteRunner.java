/**
 * 
 * @author James
 *
 */
public class VoteRunner {

	public static void main(String[] args) {
		Vote test = new Vote();
		test.collectVotes();
		test.decideWinner();
		test.printWinner();
	}
}