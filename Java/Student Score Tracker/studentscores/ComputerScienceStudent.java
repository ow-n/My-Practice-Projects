package studentscores;

public class ComputerScienceStudent extends Student {

	private static final long serialVersionUID = 1L;
	private int computerScienceScores[] = new int[5];

    public ComputerScienceStudent(int SID, int[] computerScienceScores) {
        super(SID);
        this.computerScienceScores = computerScienceScores;
    }

    public int[] getComputerScienceScores() {
        return computerScienceScores;
    }
    
    public int getScoreIndex(int index) {
        return computerScienceScores[index];
    }

    public void setComputerScienceScores(int[] computerScienceScores) {
        this.computerScienceScores = computerScienceScores;
    }
}