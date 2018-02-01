package com.tpcs.exam.controller;

import com.tpcs.exam.constant.TpcsOnlineExamConstant;
import com.tpcs.service.ExamineeApplicationServices;
import com.tpcs.vo.ExamResultVO;
import com.tpcs.vo.ExamineeVO;
import com.tpcs.vo.QuestionInsertVO;
import com.tpcs.vo.ResultVO;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author TPCS_PL Chandan 109
 */
public class ExamineeApplicationServlet extends HttpServlet implements TpcsOnlineExamConstant {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = null;
        ExamineeApplicationServices examineeApplicationServices = null;
        String buttonValue = request.getParameter(ACTION);
        try {
            HttpSession session = request.getSession();
            if (buttonValue.equalsIgnoreCase(REG_PAGE)) {
                rd = getServletContext().getRequestDispatcher(LOCATION_REGISTRATION);
                rd.forward(request, response);
            }
            if (buttonValue.equalsIgnoreCase(SUBMIT_REGISTER_VALUE)) {
                session.setAttribute(STR_VALUE, request.getParameter(PREFER_STREAM));
                examineeApplicationServices = new ExamineeApplicationServices((String) session.getAttribute(STR_VALUE));
                ExamineeVO examineeVO = new ExamineeVO();
                String examineeName = request.getParameter(NAME).trim().toUpperCase();
                examineeVO.setExamineeName(request.getParameter(NAME).trim());
                examineeVO.setExamineeMobile(request.getParameter(MOBILE).trim());
                examineeVO.setExamineeEmailId(request.getParameter(EMAIL).trim());
                examineeVO.setExamieeAddress(request.getParameter(ADDRESS).trim());
                examineeVO.setExamieeDegree(request.getParameter(DEGREE).trim());
                examineeVO.setExamieeExp(request.getParameter(EXPERIENCE).trim());
                examineeVO.setExamineeTech(request.getParameter(PREFER_STREAM).trim());
                synchronized (request) {
                    ResultVO resultVO = examineeApplicationServices.userRegistration(examineeVO);
                    if (true == resultVO.isResultFlag()) {
                        session.setAttribute(EXAMINEE_ID, resultVO.getExamineeId());
                        session.setAttribute(EXAMINEE_NAME, examineeName);
                        rd = getServletContext().getRequestDispatcher(LOCATION_EXAMINEE_HOME);
                    } else {
                        request.setAttribute(MESSAGE, resultVO.getMessage());
                        rd = getServletContext().getRequestDispatcher(LOCATION_REGISTRATION);
                    }
                }
                rd.forward(request, response);
            }
            if (buttonValue.equalsIgnoreCase(LOGIN_ADMIN)) {
                rd = getServletContext().getRequestDispatcher(LOCATION_ADMIN_LOGIN);
                rd.forward(request, response);
            }
            if (buttonValue.equalsIgnoreCase(SUBMIT_ADMIN_PAGE)) {
                session.setAttribute(STR_VALUE, request.getParameter(PREFER_STREAM));
                examineeApplicationServices = new ExamineeApplicationServices((String) session.getAttribute(STR_VALUE));
                String examineeID = request.getParameter(EXAMINEE_ID);
                ExamineeVO examineeVO = new ExamineeVO();
                examineeVO.setExamineeName(request.getParameter(ADMIN_ID));
                examineeVO.setExamineeId(request.getParameter(ADMIN_PASSWORD));
                ResultVO resultVO = examineeApplicationServices.adminCheck(examineeVO);
                if (resultVO.isResultFlag() == true) {
                    session.setAttribute(ADMIN_ID, examineeVO.getExamineeName());
                    session.setAttribute(ADMIN_PASSWORD, examineeVO.getExamineeId());
                    examineeVO = examineeApplicationServices.retriveExmieeId();
                    request.setAttribute(EXAMINEE_ID, examineeVO);
                    ExamineeVO examineeDE = examineeApplicationServices.retriveExmieeDetails(examineeID);
                    request.setAttribute(ALL_QUESTION, examineeApplicationServices.totalQuestion());
                    request.setAttribute(ADMIN_SHOW_EXEMINEE_DETAIL, examineeDE);
                    rd = getServletContext().getRequestDispatcher(LOCATION_ADMIN_HOME);
                } else {
                    request.setAttribute(MESSAGE, LOGIN_MESSAGE);
                    rd = getServletContext().getRequestDispatcher(LOCATION_ADMIN_LOGIN);
                }
                rd.forward(request, response);
            }

            if (buttonValue.equalsIgnoreCase(SUBMIT_QUESTION_PAPER)) {
                examineeApplicationServices = new ExamineeApplicationServices((String) session.getAttribute(STR_VALUE));
                QuestionInsertVO questionInsertVO = new QuestionInsertVO();
                questionInsertVO.setQuestionDesc(request.getParameter(QUESTION_DESC));
                questionInsertVO.setOptionOne(request.getParameter(OPTION_ONE));
                questionInsertVO.setOptionTwo(request.getParameter(OPTION_TWO));
                questionInsertVO.setOptionThree(request.getParameter(OPTION_THREE));
                questionInsertVO.setOptionFour(request.getParameter(OPTION_FOUR));
                questionInsertVO.setCorrectOption(request.getParameter(GIVEN_OPTION));
                ResultVO resultVO = examineeApplicationServices.getQuestionPrepare(questionInsertVO);
                rd = getServletContext().getRequestDispatcher(LOCATION_ADMIN_QUESTION_READY);
                request.setAttribute(MESSAGE, resultVO.getMessage());
                rd.forward(request, response);
            }
            if (buttonValue.equalsIgnoreCase(SUBMIT_BEGIN_EXAM)) {
                examineeApplicationServices = new ExamineeApplicationServices((String) session.getAttribute(STR_VALUE));
                String examineeId = (String) session.getAttribute(EXAMINEE_ID);
                ExamineeVO examineeVO = new ExamineeVO();
                examineeVO.setExamineeId(examineeId);
                Date date = new Date();
                String examStartTime = date.getHours() + COLON + date.getMinutes() + COLON + date.getSeconds();
                session.setAttribute(START_EXAM_TIME, examStartTime);
                examineeVO.setExamStartTime(examStartTime);
                if (true != examineeApplicationServices.setExamineeLog(examineeVO).isResultFlag()) {
                    ResultVO resultVo = examineeApplicationServices.getQuestionPaper();
                    Map<Integer, QuestionInsertVO> allQuestion = resultVo.getAllQuestion();
                    Map<Integer, QuestionInsertVO> answerSheet = new HashMap<Integer, QuestionInsertVO>();
                    session.setAttribute(TOTAL_QUESTION, allQuestion);
                    QuestionInsertVO questionVO = allQuestion.get(resultVo.getMinQuestionID());
                    request.setAttribute(QUESTION_DATA, questionVO);
                    session.setAttribute(ALL_QUESTION_ID, resultVo.getListOfQuestionId());
                    session.setAttribute(MIN_QUESTION_ID, resultVo.getMinQuestionID());
                    session.setAttribute(MAX_QUESTION_ID, resultVo.getMaxQuestionID());
                    int maxAttenQuestionId = 0;
                    session.setAttribute(MAX_ATTEN_QUESTION_ID, maxAttenQuestionId);
                    session.setAttribute(ANSWER_SHEET, answerSheet);
                    rd = getServletContext().getRequestDispatcher(LOCATION_QUESTION_PAPER);
                } else {
                    rd = getServletContext().getRequestDispatcher(LOCATION_EXAMINEE_BACK);
                }
                rd.forward(request, response);
            }
            if (buttonValue.equalsIgnoreCase(SUBMIT_NEXT_VALUE)) {
                int questionID = Integer.parseInt(request.getParameter(QUESTION_ID));
                int maxAttenQuestionId = (Integer) session.getAttribute(MAX_ATTEN_QUESTION_ID);
                if (maxAttenQuestionId < questionID) {
                    maxAttenQuestionId = questionID;
                    session.setAttribute(MAX_ATTEN_QUESTION_ID, maxAttenQuestionId);
                }
                QuestionInsertVO answerGiven = new QuestionInsertVO();
                answerGiven.setExameeID((String) session.getAttribute(EXAMINEE_ID));
                answerGiven.setGivenOption(request.getParameter(GIVEN_OPTION));
                Map<Integer, QuestionInsertVO> answerSheet = (HashMap<Integer, QuestionInsertVO>) session.getAttribute(ANSWER_SHEET);
                answerSheet.put(questionID, answerGiven);
                Map<Integer, QuestionInsertVO> allQuestion = (HashMap<Integer, QuestionInsertVO>) session.getAttribute(TOTAL_QUESTION);
                QuestionInsertVO questionData = allQuestion.get(++questionID);
                QuestionInsertVO answerData = (QuestionInsertVO) answerSheet.get(questionID);
                if (null != answerData) {
                    questionData.setGivenOption(answerData.getGivenOption());
                }
                request.setAttribute(QUESTION_DATA, questionData);
                rd = getServletContext().getRequestDispatcher(LOCATION_QUESTION_PAPER);
                rd.forward(request, response);
            }

            if (buttonValue.equalsIgnoreCase(SUBMIT_PREVIOUS_VALUE)) {
                int questionID = Integer.parseInt(request.getParameter(QUESTION_ID));
                QuestionInsertVO answerGiven = new QuestionInsertVO();
                answerGiven.setExameeID((String) session.getAttribute(EXAMINEE_ID));
                answerGiven.setGivenOption(request.getParameter(GIVEN_OPTION));
                Map<Integer, QuestionInsertVO> answerSheet = (HashMap<Integer, QuestionInsertVO>) session.getAttribute(ANSWER_SHEET);
                answerSheet.put(questionID, answerGiven);
                Map<Integer, QuestionInsertVO> allQuestion = (HashMap<Integer, QuestionInsertVO>) session.getAttribute(TOTAL_QUESTION);
                QuestionInsertVO questionData = allQuestion.get(--questionID);
                QuestionInsertVO answerData = (QuestionInsertVO) answerSheet.get(questionID);
                if (null != answerData) {
                    questionData.setGivenOption(answerData.getGivenOption());
                }
                request.setAttribute(QUESTION_DATA, questionData);
                rd = getServletContext().getRequestDispatcher(LOCATION_QUESTION_PAPER);
                rd.forward(request, response);
            }
            if (buttonValue.equalsIgnoreCase(SUBMIT_QUESTION_PAGINATION)) {
                int questionID = Integer.parseInt(request.getParameter(QUESTION_ID));
                int maxAttenQuestionId = (Integer) session.getAttribute(MAX_ATTEN_QUESTION_ID);
                if (maxAttenQuestionId < questionID) {
                    maxAttenQuestionId = questionID;
                    session.setAttribute(MAX_ATTEN_QUESTION_ID, maxAttenQuestionId);
                }
                QuestionInsertVO answerGiven = new QuestionInsertVO();
                answerGiven.setExameeID((String) session.getAttribute(EXAMINEE_ID));
                answerGiven.setGivenOption(request.getParameter(GIVEN_OPTION));
                Map<Integer, QuestionInsertVO> answerSheet = (HashMap<Integer, QuestionInsertVO>) session.getAttribute(ANSWER_SHEET);
                answerSheet.put(questionID, answerGiven);
                int paginationQuestionId = Integer.parseInt(request.getParameter(QUESTION_NO));
                Map<Integer, QuestionInsertVO> allQuestion = (HashMap<Integer, QuestionInsertVO>) session.getAttribute(TOTAL_QUESTION);
                QuestionInsertVO questionData = allQuestion.get(paginationQuestionId);
                QuestionInsertVO answerData = (QuestionInsertVO) answerSheet.get(paginationQuestionId);
                if (null != answerData) {
                    questionData.setGivenOption(answerData.getGivenOption());
                }
                request.setAttribute(QUESTION_DATA, questionData);
                rd = getServletContext().getRequestDispatcher(LOCATION_QUESTION_PAPER);
                rd.forward(request, response);
            }
            if (buttonValue.equalsIgnoreCase(STOP_EXAM) || buttonValue.equalsIgnoreCase(SUBMIT_SHOW_RESULT_TIME_UP) || buttonValue.equalsIgnoreCase(SUBMIT_SHOW_RESULT)) {
                int questionID = Integer.parseInt(request.getParameter(QUESTION_ID));
                int maxAttenQuestionId = (Integer) session.getAttribute(MAX_ATTEN_QUESTION_ID);
                if (maxAttenQuestionId < questionID) {
                    maxAttenQuestionId = questionID;
                    session.setAttribute(MAX_ATTEN_QUESTION_ID, maxAttenQuestionId);
                }
                String examineeId = (String) session.getAttribute(EXAMINEE_ID);
                QuestionInsertVO answerGiven = new QuestionInsertVO();
                answerGiven.setExameeID(examineeId);
                answerGiven.setGivenOption(request.getParameter(GIVEN_OPTION));
                Map<Integer, QuestionInsertVO> answerSheet = (HashMap<Integer, QuestionInsertVO>) session.getAttribute(ANSWER_SHEET);
                answerSheet.put(questionID, answerGiven);
                QuestionInsertVO aswerSheet = new QuestionInsertVO();
                aswerSheet.setExameeID(examineeId);
                aswerSheet.setAllAnswer(answerSheet);
                aswerSheet.setMaxQuestionId(maxAttenQuestionId);
                aswerSheet.setMinQuestionId((Integer) session.getAttribute(MIN_QUESTION_ID));
                examineeApplicationServices = new ExamineeApplicationServices((String) session.getAttribute(STR_VALUE));
                examineeApplicationServices.storeAnswer(aswerSheet);
                ExamResultVO examResultVO = examineeApplicationServices.showResultOfExam(examineeId);
                request.setAttribute(EXAMINEE_RESULT, examResultVO);
                Date date = new Date();
                String examEndTime = date.getHours() + COLON + date.getMinutes() + COLON + date.getSeconds();
                ExamineeVO examineeVO = new ExamineeVO();
                examineeVO.setExamineeId(examineeId);
                examineeVO.setExamStartTime((String) session.getAttribute(START_EXAM_TIME));
                examineeVO.setExamEndTime(examEndTime);
                rd = getServletContext().getRequestDispatcher(LOCATION_EXAM_RESULT);
                request.setAttribute(MESSAGE, examineeApplicationServices.releaseExamineeLog(examineeVO).getMessage());
                rd.forward(request, response);
                session.removeAttribute(EXAMINEE_NAME);
                session.removeAttribute(EXAMINEE_ID);
                session.invalidate();
            }
            if (buttonValue.equalsIgnoreCase(SUBMIT_SHOW_RESULT_ADMIN)) {
                examineeApplicationServices = new ExamineeApplicationServices((String) session.getAttribute(STR_VALUE));
                String examineeId = (String) request.getSession().getAttribute(EXAMINEE_ID);
                ExamResultVO examResultVO = examineeApplicationServices.showResultOfExam(examineeId);
                request.setAttribute(EXAMINEE_RESULT, examResultVO);
                rd = getServletContext().getRequestDispatcher(LOCATION_ADMIN_SHOW_RESULT);
                rd.forward(request, response);
            }
            if (buttonValue.equalsIgnoreCase(SUBMIT_ADMIN_INQ)) {
                examineeApplicationServices = new ExamineeApplicationServices((String) session.getAttribute(STR_VALUE));
                String examineeID = request.getParameter(EXAMINEE_ID);
                ExamineeVO examineeVO = examineeApplicationServices.retriveExmieeId();
                request.setAttribute(EXAMINEE_ID, examineeVO);
                ExamineeVO examineeDE = examineeApplicationServices.retriveExmieeDetails(examineeID);
                request.setAttribute(ADMIN_SHOW_EXEMINEE_DETAIL, examineeDE);
                request.setAttribute(ALL_QUESTION, examineeApplicationServices.totalQuestion());
                rd = getServletContext().getRequestDispatcher(LOCATION_ADMIN_HOME);
                rd.forward(request, response);
            }
            if (buttonValue.equalsIgnoreCase(PRINT_RESULT)) {
                examineeApplicationServices = new ExamineeApplicationServices((String) session.getAttribute(STR_VALUE));
                String examineeId = (String) request.getSession().getAttribute(EXAMINEE_ID);
                ExamResultVO examResultVO = examineeApplicationServices.showResultOfExam(examineeId);
                request.setAttribute(EXAMINEE_RESULT, examResultVO);
                rd = getServletContext().getRequestDispatcher(LOCATION_ADMIN_RESULT_PRINT);
                rd.forward(request, response);
            }
            if (buttonValue.equalsIgnoreCase(HOME_PAGE)) {
                rd = getServletContext().getRequestDispatcher(LOCATION_INDEX);
                rd.forward(request, response);
            }
            if (buttonValue.equalsIgnoreCase(LOGOUT_ADMIN)) {
                rd = getServletContext().getRequestDispatcher(LOCATION_ADMIN_LOGIN);
                rd.forward(request, response);
                session.removeAttribute(ADMIN_ID);
                session.removeAttribute(ADMIN_PASSWORD);
                session.invalidate();
            }
            if (buttonValue.equalsIgnoreCase(EXM_HOME_PAGE)) {
                examineeApplicationServices = new ExamineeApplicationServices((String) session.getAttribute(STR_VALUE));
                String examineeId = request.getParameter(EXAMINEE_ID);
                String examineeName = request.getParameter(EXAMINEE_NAME);
                ExamineeVO examineeVO = new ExamineeVO();
                examineeVO.setExamineeId(examineeId);
                examineeVO.setExamineeName(examineeName);
                examineeVO.setExamineeEmailId(request.getParameter(EXAMINEE_MAIL));
                ResultVO resultVO = examineeApplicationServices.checkExaminee(examineeVO);
                if (true == resultVO.isResultFlag()) {
                    if (true != examineeApplicationServices.setExamineeLog(examineeVO).isResultFlag()) {
                        rd = getServletContext().getRequestDispatcher(LOCATION_EXAMINEE_BACK);
                    }
                    session.setAttribute(EXAMINEE_ID, request.getParameter(EXAMINEE_ID));
                    session.setAttribute(EXAMINEE_NAME, request.getParameter(EXAMINEE_NAME));
                    rd = getServletContext().getRequestDispatcher(LOCATION_EXAMINEE_HOME);
                } else {
                    request.setAttribute(MESSAGE, resultVO.getMessage());
                    rd = getServletContext().getRequestDispatcher(LOCATION_INDEX);
                }
                rd.forward(request, response);
            }

        } catch (Exception e) {
            rd = getServletContext().getRequestDispatcher(LOCATION_EXAMINEE_BACK);
            rd.forward(request, response);
            HttpSession session = request.getSession();
            session.removeAttribute(EXAMINEE_ID);
            session.removeAttribute(EXAMINEE_NAME);
            session.removeAttribute(QUESTION_DATA);
            session.removeAttribute(TOTAL_QUESTION);
            session.removeAttribute(ANSWER_SHEET);
            session.invalidate();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
