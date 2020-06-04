package connector;

import entity.AnalysisData;
import entity.DetailData;
import entity.ExpertData;
import entity.UserData;

import java.util.ArrayList;

public class DataClass {
    private DetailData detailObj;
    private UserData userObj;
    private AnalysisData analysisObj;
    private ExpertData expertObj;
    private String operation;
    private boolean result=true;
    private ArrayList<DetailData> detailData=new ArrayList<>();
    private ArrayList <UserData> userData=new ArrayList<>();
    private ArrayList <AnalysisData> analysisData=new ArrayList<>();
    private ArrayList <ExpertData> expertData=new ArrayList<>();

    public ArrayList<AnalysisData> getAnalysisData() {
        return analysisData;
    }

    public void setAnalysisData(ArrayList<AnalysisData> analysisData) {
        this.analysisData = analysisData;
    }

    public ArrayList<ExpertData> getExpertData() {
        return expertData;
    }

    public void setExpertData(ArrayList<ExpertData> expertData) {
        this.expertData = expertData;
    }

    public AnalysisData getAnalysisObj() {
        return analysisObj;
    }

    public void setAnalysisObj(AnalysisData analysisObj) {
        this.analysisObj = analysisObj;
    }

    public ExpertData getExpertObj() {
        return expertObj;
    }

    public void setExpertObj(ExpertData expertObj) {
        this.expertObj = expertObj;
    }

    public ArrayList<UserData> getUserData() {
        return userData;
    }

    public void setUserData(ArrayList<UserData> userData) {
        this.userData = userData;
    }

    public ArrayList<DetailData> getDetailData() {
        return detailData;
    }

    public void setDetailData(ArrayList<DetailData> detailData) {
        this.detailData = detailData;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public UserData getUserObj() {
        return userObj;
    }

    public void setUserObj(UserData userObj) {
        this.userObj = userObj;
    }

    public DetailData getDetailObj() {
        return detailObj;
    }

    public void setDetailObj(DetailData detailObj) {
        this.detailObj = detailObj;
    }

}
