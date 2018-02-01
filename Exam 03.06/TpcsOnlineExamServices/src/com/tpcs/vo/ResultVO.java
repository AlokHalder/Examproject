package com.tpcs.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TPCS_PL Chandan 109
 */
public class ResultVO {

    private String examineeId;
    private boolean resultFlag;
    private String message;
    private int questionId;
    private int maxQuestionID = 0;
    private int minQuestionID = 0;
    
    private Map<Integer, QuestionInsertVO> allQuestion = new HashMap<Integer, QuestionInsertVO>();
    private List<Integer> listOfQuestionId = new ArrayList<Integer>();

    /**
     * @return the resultFlag
     */
    public boolean isResultFlag() {
        return resultFlag;
    }

    /**
     * @param resultFlag the resultFlag to set
     */
    public void setResultFlag(boolean resultFlag) {
        this.resultFlag = resultFlag;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the examineeId
     */
    public String getExamineeId() {
        return examineeId;
    }

    /**
     * @param examineeId the examineeId to set
     */
    public void setExamineeId(String examineeId) {
        this.examineeId = examineeId;
    }

    /**
     * @return the questionId
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * @param questionId the questionId to set
     */
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    /**
     * @return the allQuestion
     */
    public Map<Integer, QuestionInsertVO> getAllQuestion() {
        return allQuestion;
    }

    /**
     * @param allQuestion the allQuestion to set
     */
    public void setAllQuestion(Map<Integer, QuestionInsertVO> allQuestion) {
        this.allQuestion = allQuestion;
    }

    /**
     * @return the maxQuestionID
     */
    public int getMaxQuestionID() {
        return maxQuestionID;
    }

    /**
     * @param maxQuestionID the maxQuestionID to set
     */
    public void setMaxQuestionID(int maxQuestionID) {
        this.maxQuestionID = maxQuestionID;
    }

    /**
     * @return the minQuestionID
     */
    public int getMinQuestionID() {
        return minQuestionID;
    }

    /**
     * @param minQuestionID the minQuestionID to set
     */
    public void setMinQuestionID(int minQuestionID) {
        this.minQuestionID = minQuestionID;
    }

    /**
     * @return the listOfQuestionId
     */
    public List<Integer> getListOfQuestionId() {
        return listOfQuestionId;
    }

    /**
     * @param listOfQuestionId the listOfQuestionId to set
     */
    public void setListOfQuestionId(List<Integer> listOfQuestionId) {
        this.listOfQuestionId = listOfQuestionId;
    }
}
