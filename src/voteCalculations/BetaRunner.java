package voteCalculations;

import java.util.ArrayList;

public class BetaRunner {

    public static void main(String[] args) {

        Manager manage = new Manager();
        ArrayList<Candidate> list = new ArrayList<Candidate>();
        list.add(new Candidate("Bob"));
        list.add(new Candidate("jim"));
        list.add(new Candidate("Frank"));
        list.add(new Candidate("Lary"));
        list.add(new Candidate("Susan"));
        list.add(new Candidate("Jimbo"));

        manage.setCandidates(list);
        int[] n1 = new int[6];
        n1[1] = 3;
        n1[2] = 2;
        n1[3] = 0;
        n1[4] = 4;
        n1[0] = 1;
        n1[5] = 5;
        int[] n2 = new int[6];
        n2[1] = 1;
        n2[2] = 2;
        n2[3] = 0;
        n2[4] = 4;
        n2[0] = 3;
        n2[5] = 5;
        int[] n3 = new int[6];
        n3[1] = 1;
        n3[2] = 2;
        n3[3] = 0;
        n3[4] = 4;
        n3[0] = 3;
        n3[5] = 5;
        int[] n4 = new int[6];
        n4[1] = 4;
        n4[2] = 3;
        n4[3] = 0;
        n4[4] = 1;
        n4[0] = 2;
        n4[5] = 5;
        System.out.println(manage.getCandidate().get(2));
        System.out.println(n1[1]);
        manage.addVoter(n2);
        manage.addVoter(n3);
        manage.addVoter(n4);
        manage.addVoter(n1);
        manage.addVoter(n1);
        manage.addVoter(n1);
        System.out.println("The winner is " + manage.calculateWinner().getName());

    }

}
