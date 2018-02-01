package com.tpcs.service;

import com.tpcs.dbconn.CreateConnection;
import com.tpcs.impl.ExaminationImpl;
import com.tpcs.vo.ExamResultVO;
import com.tpcs.vo.ExamineeVO;
import com.tpcs.vo.QuestionInsertVO;
import com.tpcs.vo.ResultVO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author TPCS_PL Chandan 109
 */
public class ExamineeApplicationServices implements ExaminationImpl {

    private ResourceBundle resourceBundle = ResourceBundle.getBundle(PROP_DEST);
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private String preferTech = null;

    public ExamineeApplicationServices(String technology) {

        if (technology.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_JAVA))) {
            preferTech = resourceBundle.getString(TECHNOLOGY_JAVA);
        } else if (technology.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_NET))) {
            preferTech = resourceBundle.getString(TECHNOLOGY_NET);
        } else if (technology.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_PHP))) {
            preferTech = resourceBundle.getString(TECHNOLOGY_PHP);
        }
    }

    @Override
    public ResultVO userRegistration(ExamineeVO examineeVO) {
        ResultVO resultVO = new ResultVO();
        String examineeName = examineeVO.getExamineeName();
        String examineeMobile = examineeVO.getExamineeMobile();
        String examineeEmailId = examineeVO.getExamineeEmailId();
        if (null != examineeName && null != examineeEmailId && null != examineeMobile) {
            try {
                conn = CreateConnection.getConnection();
                pstmt = conn.prepareStatement(resourceBundle.getString(QUERY17));
                pstmt.setString(1, examineeName);
                pstmt.setString(2, examineeMobile);
                pstmt.setString(3, examineeEmailId);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    resultVO.setMessage(resourceBundle.getString(MESSAGE));
                    CreateConnection.connectionRelise(rs, pstmt);
                } else {
                    int examineeId = 0;
                    pstmt = conn.prepareStatement(resourceBundle.getString(QUERY_A));
                    rs = pstmt.executeQuery();
                    if (rs.next()) {
                        examineeId = (1 + rs.getInt(1));
                    }
                    CreateConnection.connectionRelise(rs, pstmt);
                    pstmt = conn.prepareStatement(resourceBundle.getString(QUERY));
                    pstmt.setInt(1, examineeId);
                    pstmt.setString(2, examineeName);
                    pstmt.setString(3, examineeMobile);
                    pstmt.setString(4, examineeEmailId);
                    pstmt.setString(5, examineeVO.getExamieeAddress());
                    pstmt.setString(6, examineeVO.getExamieeDegree());
                    pstmt.setString(7, examineeVO.getExamieeExp());
                    pstmt.setString(8, examineeVO.getExamineeTech());
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        resultVO.setResultFlag(true);
                        pstmt = conn.prepareStatement(resourceBundle.getString(QUERY1));
                        pstmt.setString(1, examineeEmailId);
                        rs = pstmt.executeQuery();
                        if (rs.next()) {
                            resultVO.setExamineeId(rs.getString(1));
                        }
                    } else {
                        resultVO.setResultFlag(false);
                    }
                }
            } catch (SQLException e) {
            } finally {
                CreateConnection.connectionRelise(rs, conn, pstmt);
            }
        } else {
            resultVO.setMessage(resourceBundle.getString(MESSAGE1));
        }
        return resultVO;
    }

    @Override
    public ResultVO checkExaminee(ExamineeVO examineeVO) {
        ResultVO resultVO = new ResultVO();
        try {
            conn = CreateConnection.getConnection();
            pstmt = conn.prepareStatement(resourceBundle.getString(QUERY16));
            pstmt.setString(1, examineeVO.getExamineeName());
            pstmt.setString(2, examineeVO.getExamineeId());
            pstmt.setString(3, examineeVO.getExamineeEmailId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                resultVO.setResultFlag(true);
            } else {
                resultVO.setResultFlag(false);
                resultVO.setMessage(resourceBundle.getString(MESSAGE1));
            }
        } catch (SQLException e) {
            resultVO.setMessage(resourceBundle.getString(MESSAGE1));
        } finally {
            CreateConnection.connectionRelise(rs, conn, pstmt);
        }
        return resultVO;
    }

    @Override
    public ResultVO getQuestionPrepare(QuestionInsertVO questionInsertVO) {
        ResultVO resultVO = new ResultVO();
        if (null != questionInsertVO) {
            try {
                conn = CreateConnection.getConnection();
                int questionId = 0;
                if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_JAVA))) {
                    pstmt = conn.prepareStatement(resourceBundle.getString(QUERY6));
                } else if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_NET))) {
                    pstmt = conn.prepareStatement(resourceBundle.getString(QUERY6A));
                } else if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_PHP))) {
                    pstmt = conn.prepareStatement(resourceBundle.getString(QUERY6B));
                }
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    questionId = (1 + rs.getInt(1));
                }
                CreateConnection.connectionRelise(rs, pstmt);
                if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_JAVA))) {
                    pstmt = conn.prepareStatement(resourceBundle.getString(QUERY2));
                } else if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_NET))) {
                    pstmt = conn.prepareStatement(resourceBundle.getString(QUERY2A));
                } else if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_PHP))) {
                    pstmt = conn.prepareStatement(resourceBundle.getString(QUERY2B));
                }
                pstmt.setInt(1, questionId);
                pstmt.setString(2, questionInsertVO.getOptionOne());
                pstmt.setString(3, questionInsertVO.getOptionTwo());
                pstmt.setString(4, questionInsertVO.getOptionThree());
                pstmt.setString(5, questionInsertVO.getOptionFour());
                pstmt.setString(6, questionInsertVO.getQuestionDesc());
                pstmt.setString(7, questionInsertVO.getCorrectOption());
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    resultVO.setMessage(resourceBundle.getString(MESSAGE2));
                } else {
                    resultVO.setMessage(resourceBundle.getString(MESSAGE2A));
                }
            } catch (SQLException e) {
                resultVO.setMessage(resourceBundle.getString(MESSAGE2A));
            } finally {
                CreateConnection.connectionRelise(rs, conn, pstmt);
            }
        }
        return resultVO;
    }

    @Override
    public ResultVO getQuestionPaper() {
        ResultVO resultVo = new ResultVO();
        Map<Integer, QuestionInsertVO> allQuestion = new HashMap<Integer, QuestionInsertVO>();
        List<Integer> listOfQuestionId = new ArrayList<Integer>();
        try {
            conn = CreateConnection.getConnection();
            if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_JAVA))) {
                pstmt = conn.prepareStatement(resourceBundle.getString(QUERY5B));
            } else if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_NET))) {
                pstmt = conn.prepareStatement(resourceBundle.getString(QUERY5B1));
            } else if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_PHP))) {
                pstmt = conn.prepareStatement(resourceBundle.getString(QUERY5B2));
            }
            rs = pstmt.executeQuery();
            if (rs.next()) {
                resultVo.setMaxQuestionID(rs.getInt(1));
                resultVo.setMinQuestionID(rs.getInt(2));
            }
            CreateConnection.connectionRelise(rs, pstmt);
            if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_JAVA))) {
                pstmt = conn.prepareStatement(resourceBundle.getString(QUERY5A));
            } else if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_NET))) {
                pstmt = conn.prepareStatement(resourceBundle.getString(QUERY5A1));
            } else if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_PHP))) {
                pstmt = conn.prepareStatement(resourceBundle.getString(QUERY5A2));
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                QuestionInsertVO questionInsertVO = new QuestionInsertVO();
                int questionId = rs.getInt(1);
                questionInsertVO.setQuestionId(questionId);
                questionInsertVO.setQuestionDesc(rs.getString(6));
                questionInsertVO.setOptionOne(rs.getString(2));
                questionInsertVO.setOptionTwo(rs.getString(3));
                questionInsertVO.setOptionThree(rs.getString(4));
                questionInsertVO.setOptionFour(rs.getString(5));
                allQuestion.put(new Integer(rs.getInt(1)), questionInsertVO);
                listOfQuestionId.add(questionId);
            }
            resultVo.setListOfQuestionId(listOfQuestionId);
            resultVo.setAllQuestion(allQuestion);
        } catch (SQLException e) {
        } finally {
            CreateConnection.connectionRelise(rs, conn, pstmt);
        }
        return resultVo;
    }

    @Override
    public void storeAnswer(QuestionInsertVO questionInsertVO) {
        if (null != questionInsertVO) {
            String exameeId = questionInsertVO.getExameeID();
            int maxQuestionId = questionInsertVO.getMaxQuestionId();
            int minQuestionId = questionInsertVO.getMinQuestionId();
            Map<Integer, QuestionInsertVO> examineeAnswerSheet = questionInsertVO.getAllAnswer();
            try {
                conn = CreateConnection.getConnection();
                pstmt = conn.prepareStatement(resourceBundle.getString(QUERY4));
                for (int attenQuestionId = minQuestionId; attenQuestionId <= maxQuestionId; attenQuestionId++) {
                    QuestionInsertVO answer = (QuestionInsertVO) examineeAnswerSheet.get(attenQuestionId);
                    if (null != answer) {
                        if (null != answer.getGivenOption()) {
                            pstmt.setString(1, exameeId);
                            pstmt.setInt(2, attenQuestionId);
                            pstmt.setString(3, answer.getGivenOption());
                            pstmt.addBatch();
                        }
                    } else {
                        continue;
                    }
                }
                int rowsAffected[] = pstmt.executeBatch();
                if (rowsAffected.length > 0) {
                }
            } catch (SQLException e) {
            } finally {
                CreateConnection.connectionRelise(rs, conn, pstmt);
            }
        }
    }

    @Override
    public ExamResultVO showResultOfExam(String examineeId) {
        ExamResultVO examResultVO = new ExamResultVO();
        List<QuestionInsertVO> listOfResults = new ArrayList<QuestionInsertVO>();
        int falseResultCount = 0;
        int trueResultCount = 0;
        int totalCount = 0;
        try {
            conn = CreateConnection.getConnection();
            if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_JAVA))) {
                pstmt = conn.prepareStatement(resourceBundle.getString(QUERY11));
            } else if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_NET))) {
                pstmt = conn.prepareStatement(resourceBundle.getString(QUERY11A));
            } else if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_PHP))) {
                pstmt = conn.prepareStatement(resourceBundle.getString(QUERY11B));
            }
            pstmt.setString(1, examineeId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                QuestionInsertVO questionInsertVO = new QuestionInsertVO();
                questionInsertVO.setQuestionId(rs.getInt(1));
                questionInsertVO.setCorrectOption(rs.getString(2));
                questionInsertVO.setGivenOption(rs.getString(3));
                if (rs.getString(2).equals(rs.getString(3))) {
                    trueResultCount += 1;
                    questionInsertVO.setAnswerFlag(true);
                } else {
                    falseResultCount += 1;
                    questionInsertVO.setAnswerFlag(false);
                }
                totalCount += 1;
                listOfResults.add(questionInsertVO);
            }
            examResultVO.setListOfResults(listOfResults);
            examResultVO.setTrueResultCount(trueResultCount);
            examResultVO.setFalseResultCount(falseResultCount);
            examResultVO.setTotalCount(totalCount);
        } catch (SQLException e) {
        } finally {
            CreateConnection.connectionRelise(rs, conn, pstmt);
        }
        return examResultVO;
    }

    @Override
    public ExamineeVO retriveExmieeId() {
        ExamineeVO examineeVO = new ExamineeVO();
        List listOfExamiee = new ArrayList();
        try {
            conn = CreateConnection.getConnection();
            if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_JAVA))) {
                pstmt = conn.prepareStatement(resourceBundle.getString(QUERY12));
                pstmt.setString(1, resourceBundle.getString(TECHNOLOGY_JAVA));
            } else if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_NET))) {
                pstmt = conn.prepareStatement(resourceBundle.getString(QUERY12));
                pstmt.setString(1, resourceBundle.getString(TECHNOLOGY_NET));
            } else if (preferTech.equalsIgnoreCase(resourceBundle.getString(TECHNOLOGY_PHP))) {
                pstmt = conn.prepareStatement(resourceBundle.getString(QUERY12));
                pstmt.setString(1, resourceBundle.getString(TECHNOLOGY_PHP));
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                listOfExamiee.add(rs.getInt(1));
            }
            examineeVO.setListOfexamineeId(listOfExamiee);
        } catch (SQLException e) {
        } finally {
            CreateConnection.connectionRelise(rs, conn, pstmt);
        }
        return examineeVO;
    }

    @Override
    public ExamineeVO retriveExmieeDetails(String examineeID) {
        ExamineeVO examineeVO = new ExamineeVO();
        try {
            conn = CreateConnection.getConnection();
            pstmt = conn.prepareStatement(resourceBundle.getString(QUERY13));
            pstmt.setString(1, examineeID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                examineeVO.setExamineeId(rs.getString(1));
                examineeVO.setExamineeName(rs.getString(2));
                examineeVO.setExamineeMobile(rs.getString(3));
                examineeVO.setExamineeEmailId(rs.getString(4));
                examineeVO.setExamieeAddress(rs.getString(5));
                examineeVO.setExamieeDegree(rs.getString(6));
                examineeVO.setExamieeExp(rs.getString(7));
                examineeVO.setExamineeTech(rs.getString(8));
                examineeVO.setExamineeRegDate(rs.getString(9));
            }
            examineeVO.setExamResultVO(showResultOfExam(examineeID));
        } catch (SQLException e) {
        } finally {
            CreateConnection.connectionRelise(rs, conn, pstmt);
        }
        return examineeVO;
    }

    @Override
    public ResultVO adminCheck(ExamineeVO examineeVO) {
        ResultVO resultVO = new ResultVO();
        try {
            conn = CreateConnection.getConnection();
            pstmt = conn.prepareStatement(resourceBundle.getString(QUERY14));
            pstmt.setString(1, examineeVO.getExamineeName());
            pstmt.setString(2, examineeVO.getExamineeId());
            rs = pstmt.executeQuery();
            String adminID = null;
            String password = null;
            if (rs.next()) {
                adminID = rs.getString(1);
                password = rs.getString(2);
            }
            if (examineeVO.getExamineeName().equals(adminID) && examineeVO.getExamineeId().equals(password)) {
                resultVO.setResultFlag(true);
            } else {
                resultVO.setMessage(resourceBundle.getString(MESSAGE_LOGIN_FAIL));
                resultVO.setResultFlag(false);
            }
        } catch (SQLException e) {
        } finally {
            CreateConnection.connectionRelise(rs, conn, pstmt);
        }
        return resultVO;
    }

    @Override
    public ResultVO totalQuestion() {
        ResultVO resultVO = new ResultVO();
        try {
            conn = CreateConnection.getConnection();
            pstmt = conn.prepareStatement(resourceBundle.getString(QUERY6));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                resultVO.setQuestionId(rs.getInt(1));
            }
        } catch (SQLException e) {
        } finally {
            CreateConnection.connectionRelise(rs, conn, pstmt);
        }
        return resultVO;
    }

    @Override
    public ResultVO setExamineeLog(ExamineeVO examineeVO) {
        ResultVO resultVO = new ResultVO();
        try {
            conn = CreateConnection.getConnection();
            pstmt = conn.prepareStatement(resourceBundle.getString(QUERY18));
            pstmt.setInt(1, Integer.parseInt(examineeVO.getExamineeId()));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                resultVO.setResultFlag(false);
            } else {
                pstmt = conn.prepareStatement(resourceBundle.getString(QUERY18A));
                pstmt.setInt(1, Integer.parseInt(examineeVO.getExamineeId()));
                pstmt.setString(2, examineeVO.getExamStartTime());
                int executeUpdate = pstmt.executeUpdate();
            }
        } catch (SQLException e) {
        } finally {
            CreateConnection.connectionRelise(rs, conn, pstmt);
        }
        return resultVO;
    }

    @Override
    public ResultVO releaseExamineeLog(ExamineeVO examineeVO) {
        ResultVO resultVO = new ResultVO();
        try {
            conn = CreateConnection.getConnection();
            pstmt = conn.prepareStatement(resourceBundle.getString(QUERY18B));
            SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
            Date startTime = dateFormat.parse(examineeVO.getExamStartTime());
            Date endTime = dateFormat.parse(examineeVO.getExamEndTime());
            long totalExamTime = ((endTime.getTime() - startTime.getTime()) / 60) / 1000;
            pstmt.setString(1, examineeVO.getExamEndTime());
            pstmt.setString(2, Long.toString(totalExamTime));
            pstmt.setInt(3, Integer.parseInt(examineeVO.getExamineeId()));
            pstmt.setString(4, examineeVO.getExamStartTime());
            int executeUpdate = pstmt.executeUpdate();
            if (totalExamTime >= 60) {
                resultVO.setMessage(resourceBundle.getString(MESSAGE1A) + (totalExamTime - 60));
            }
        } catch (SQLException e) {
        } catch (ParseException pe) {
        } finally {
            CreateConnection.connectionRelise(rs, conn, pstmt);
        }
        return resultVO;
    }
}
