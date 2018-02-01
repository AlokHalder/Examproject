package com.tpcs.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author TPCS_PL Chandan 109
 */
public class QuestionInsertVO implements Serializable {

    private int questionId = 0;
    private int maxQuestionId = 0;
    private int minQuestionId = 0;
    private String optionOne = null;
    private String optionTwo = null;
    private String optionThree = null;
    private String optionFour = null;
    private String questionDesc = null;
    private String correctOption = null;
    private String exameeID = null;
    private String givenOption = null;
    private String questionPrefTech = null;
    private boolean answerFlag = false;
    private int falseResultCount = 0;
    private int trueResultCount = 0;
    private int totalCount = 0;
    private String message = null;
    private Map<Integer, QuestionInsertVO> allAnswer = new HashMap<Integer, QuestionInsertVO>();

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
     * @return the optionOne
     */
    public String getOptionOne() {
        return optionOne;
    }

    /**
     * @param optionOne the optionOne to set
     */
    public void setOptionOne(String optionOne) {
        this.optionOne = optionOne;
    }

    /**
     * @return the optionTwo
     */
    public String getOptionTwo() {
        return optionTwo;
    }

    /**
     * @param optionTwo the optionTwo to set
     */
    public void setOptionTwo(String optionTwo) {
        this.optionTwo = optionTwo;
    }

    /**
     * @return the optionThree
     */
    public String getOptionThree() {
        return optionThree;
    }

    /**
     * @param optionThree the optionThree to set
     */
    public void setOptionThree(String optionThree) {
        this.optionThree = optionThree;
    }

    /**
     * @return the optionFour
     */
    public String getOptionFour() {
        return optionFour;
    }

    /**
     * @param optionFour the optionFour to set
     */
    public void setOptionFour(String optionFour) {
        this.optionFour = optionFour;
    }

    /**
     * @return the questionDesc
     */
    public String getQuestionDesc() {
        return questionDesc;
    }

    /**
     * @param questionDesc the questionDesc to set
     */
    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    /**
     * @return the correctOption
     */
    public String getCorrectOption() {
        return correctOption;
    }

    /**
     * @param correctOption the correctOption to set
     */
    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }

    public void setExameeID(String exameeID) {
        this.exameeID = exameeID;
    }

    /**
     * @return the exameeID
     */
    public String getExameeID() {
        return exameeID;
    }

    /**
     * @return the givenOption
     */
    public String getGivenOption() {
        return givenOption;
    }

    /**
     * @param givenOption the givenOption to set
     */
    public void setGivenOption(String givenOption) {
        this.givenOption = givenOption;
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
     * @return the answerFlag
     */
    public boolean isAnswerFlag() {
        return answerFlag;
    }

    /**
     * @param answerFlag the answerFlag to set
     */
    public void setAnswerFlag(boolean answerFlag) {
        this.answerFlag = answerFlag;
    }

    /**
     * @return the falseResultCount
     */
    public int getFalseResultCount() {
        return falseResultCount;
    }

    /**
     * @param falseResultCount the falseResultCount to set
     */
    public void setFalseResultCount(int falseResultCount) {
        this.falseResultCount = falseResultCount;
    }

    /**
     * @return the trueResultCount
     */
    public int getTrueResultCount() {
        return trueResultCount;
    }

    /**
     * @param trueResultCount the trueResultCount to set
     */
    public void setTrueResultCount(int trueResultCount) {
        this.trueResultCount = trueResultCount;
    }

    /**
     * @return the totalCount
     */
    public int getTotalCount() {
        return totalCount;
    }

    /**
     * @param totalCount the totalCount to set
     */
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    /**
     * @return the maxQuestionId
     */
    public int getMaxQuestionId() {
        return maxQuestionId;
    }

    /**
     * @param maxQuestionId the maxQuestionId to set
     */
    public void setMaxQuestionId(int maxQuestionId) {
        this.maxQuestionId = maxQuestionId;
    }

    /**
     * @return the minQuestionId
     */
    public int getMinQuestionId() {
        return minQuestionId;
    }

    /**
     * @param minQuestionId the minQuestionId to set
     */
    public void setMinQuestionId(int minQuestionId) {
        this.minQuestionId = minQuestionId;
    }

    /**
     * @return the allAnswer
     */
    public Map<Integer, QuestionInsertVO> getAllAnswer() {
        return allAnswer;
    }

    /**
     * @param allAnswer the allAnswer to set
     */
    public void setAllAnswer(Map<Integer, QuestionInsertVO> allAnswer) {
        this.allAnswer = allAnswer;
    }

    /**
     * @return the questionPrefTech
     */
    public String getQuestionPrefTech() {
        return questionPrefTech;
    }

    /**
     * @param questionPrefTech the questionPrefTech to set
     */
    public void setQuestionPrefTech(String questionPrefTech) {
        this.questionPrefTech = questionPrefTech;
    }
}
