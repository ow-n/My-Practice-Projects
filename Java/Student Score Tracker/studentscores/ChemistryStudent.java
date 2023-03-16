package studentscores;

public class ChemistryStudent extends Student {

	private static final long serialVersionUID = 1L;
	private int chemistryScores[] = new int[5];

    public ChemistryStudent(int SID, int[] chemistryScores) {
        super(SID);
        this.chemistryScores = chemistryScores;
    }

    public int[] getChemistryScores() {
        return chemistryScores;
    }
    
    public int getScoreIndex(int index) {
        return chemistryScores[index];
    }

    public void setChemistryScores(int[] chemistryScores) {
        this.chemistryScores = chemistryScores;
    }
}