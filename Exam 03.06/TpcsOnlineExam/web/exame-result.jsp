<%@page import="java.util.List"%>
<%@page import="com.tpcs.vo.ExamResultVO"%>
<%@page import="com.tpcs.vo.QuestionInsertVO"%>
<% if (session.getAttribute("examineeId") == null) {
%>
<jsp:forward page="index.jsp"/> 
<% } else {%> 
<%! int no = 0;
    char g;
    String colour = null;%>
<%
    ExamResultVO examResultVO = (ExamResultVO) request.getAttribute("examresult");
    no = examResultVO.getTrueResultCount();
    if (50 <= no) {
        g = 'A';
        colour = "green";
    } else if (49 <= no || no >= 30) {
        g = 'B';
        colour = "yellow";
    } else {
        g = 'C';
        colour = "red";
    }
    List<QuestionInsertVO> listOfResults = examResultVO.getListOfResults();
%>
<jsp:include page="/exam-layout/header.jsp"/>
<div id="page">
    <fieldset>
        <legend><b>Examination Score Card </b></legend>
        <table align="center"> 
            <tr>
                <td colspan="4"><%if (null != request.getAttribute("message")) {%><font style="color: red"> <%= request.getAttribute("message")%> minutes extra time </font><%}%> </td>
            </tr>
            <tr bgcolor="#99CC33">
                <td>Question No.</td>
                <td>Correct Option</td>
                <td>Your Answer</td>
                <td>Remarks</td>
            </tr>

            <%
                int i = 0;
                for (QuestionInsertVO question : listOfResults) {
                    i += 1;
                    if (i % 2 == 0) {
            %>
            <tr bgcolor="silver">
                <%} else {%>
            <tr>
                <%}%> 

                <td align="center"> <%=question.getQuestionId()%></td>
                <td align="center"><%=question.getCorrectOption()%></td>
                <td align="center"><%=question.getGivenOption()%></td>
                <td align="center"><%if (question.isAnswerFlag()) {%>
                    <font color="green" style="font-weight: bold">True</font>  
                    <%} else {%>
                    <font color="red" style="font-weight: bold">False</font>  
                    <%}%>
                </td>
            </tr>
            <%
                }
            %>
        </table>
        <table align="center">
            <tr>
                <td colspan="5">
                    <h3>Exam Remarks  </h3>
                </td>
            </tr>
            <tr bgcolor="#99CC33">
                <td>
                    Total Question 
                </td>
                <td>
                    Question Attained 
                </td>
                <td>
                    Right Answer 
                </td>
                <td>
                    Wrong Answer 
                </td>
                <td>
                    Grade
                </td>
            </tr>
            <tr bgcolor="silver">
                <td align="center">
                    <%= session.getAttribute("maxQuestionId")%>
                </td>
                <td align="center">
                    <%=examResultVO.getTotalCount()%>
                </td>
                <td align="center">
                    <%=examResultVO.getTrueResultCount()%>
                </td>
                <td align="center">
                    <%=examResultVO.getFalseResultCount()%>
                </td>
                <td align="center">
                    <font color="<%= colour%>" style="font-weight: bold"><%= g%></font>
                </td>
            </tr>
        </table>
    </fieldset>
    <% }%>
    <jsp:include page="/exam-layout/footer.jsp"/>