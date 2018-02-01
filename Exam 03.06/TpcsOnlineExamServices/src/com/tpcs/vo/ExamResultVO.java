
package com.tpcs.vo;

import java.util.List;

/**
 *
 * @author TPCS_PL Chandan 109
 */
public class ExamResultVO {

    private List<QuestionInsertVO> listOfResults = null;
    private int falseResultCount = 0;
    private int trueResultCount = 0;
    private int totalCount = 0;

    /**
     * @return the listOfResults
     */
    public List<QuestionInsertVO> getListOfResults() {
        return listOfResults;
    }

    /**
     * @param listOfResults the listOfResults to set
     */
    public void setListOfResults(List<QuestionInsertVO> listOfResults) {
        this.listOfResults = listOfResults;
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
}
