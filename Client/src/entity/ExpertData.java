package entity;

public class ExpertData {
    private int Z[][];
    private int F1;
    private int F2;
    private int F3;
    private int F4;
    private int expertId;
    private int analysisId;

    public int getAnalysisId() {
        return analysisId;
    }

    public void setAnalysisId(int analysisId) {
        this.analysisId = analysisId;
    }

    public int[][] getZ() {
        return Z;
    }

    public void setZ(int[][] z) {
        Z = z;
    }

    public int getF1() {
        return F1;
    }

    public void setF1(int f1) {
        F1 = f1;
    }

    public int getF2() {
        return F2;
    }

    public void setF2(int f2) {
        F2 = f2;
    }

    public int getF3() {
        return F3;
    }

    public void setF3(int f3) {
        F3 = f3;
    }

    public int getF4() {
        return F4;
    }

    public void setF4(int f4) {
        F4 = f4;
    }

    public int getExpertId() {
        return expertId;
    }

    public void setExpertId(int expertId) {
        this.expertId = expertId;
    }
}
