package com.tpcs.impl;

import com.tpcs.impl.constant.ExamineeApplicationConstant;
import com.tpcs.vo.ExamResultVO;
import com.tpcs.vo.ExamineeVO;
import com.tpcs.vo.QuestionInsertVO;
import com.tpcs.vo.ResultVO;

/**
 *
 * @author TPCS_PL Chandan 109
 */
public interface ExaminationImpl extends ExamineeApplicationConstant {

    ResultVO userRegistration(ExamineeVO examineeVO);

    ResultVO getQuestionPrepare(QuestionInsertVO questionInsertVO);

    ResultVO getQuestionPaper();

    void storeAnswer(QuestionInsertVO questionInsertVO);

    ExamResultVO showResultOfExam(String examineeId);

    ExamineeVO retriveExmieeId();

    ExamineeVO retriveExmieeDetails(String examineeID);

    ResultVO adminCheck(ExamineeVO examineeVO);

    ResultVO checkExaminee(ExamineeVO examineeVO);

    ResultVO totalQuestion();

    ResultVO setExamineeLog(ExamineeVO examineeVO);

    ResultVO releaseExamineeLog(ExamineeVO examineeVO);
}
