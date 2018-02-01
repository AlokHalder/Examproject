<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.tpcs.vo.QuestionInsertVO"%>
<% if (session.getAttribute("examineeId") == null && session.getAttribute("examineeName") == null) {%>
<jsp:forward page="examinee-home.jsp"/>
<% } else {
    QuestionInsertVO questionInsertVO = (QuestionInsertVO) request.getAttribute("questionData");
    int maxQuestionId = (Integer) session.getAttribute("maxQuestionId");
    int minQuestionId = (Integer) session.getAttribute("minQuestionId");
    ArrayList<Integer> allQuestionsId = (ArrayList<Integer>) session.getAttribute("allQuestionId");
    Map<Integer, QuestionInsertVO> answerSheet = (Map<Integer, QuestionInsertVO>) session.getAttribute("answerSheet");
%>
<jsp:include page="/exam-layout/header.jsp"/>
<%
    String mins = request.getParameter("mins");
    if (mins == null) {
        mins = "60";
    }

    String secs = request.getParameter("secs");
    if (secs == null) {
        secs = "1";
    }
%>  
<script> 
    var mins = <%=mins%>; // write mins to javascript  
    var secs = <%=secs%>;
    // write secs to javascript  
    function timer()        
    {  
        // tic tac  
        if( --secs == -1 )  
        {  
            secs = 59;  
            --mins;  
        }  
        // leading zero? formatting  
        if( secs < 10 ) secs = "0" + secs;               
        if( mins < 10 ) mins = "0" + parseInt( mins, 10 );  
  
        // display  
        document.forma.mins.value = mins;   
        document.forma.secs.value = secs;  
  
        // continue?  
        if( secs == 0 && mins == 0 ) // time over  
        {  
            // document.forma.submit();
            document.getElementById('qsection').style.visibility="hidden"
            document.getElementById('result').style.visibility="visible"
        }  
        else // call timer() recursively every 1000 ms == 1 sec  
        {  
            window.setTimeout( "timer()", 1000 );
        }  
    } 
</script>
</head>
<center>
    <h4>
        <font style="color: black; font-size: large;"><%= session.getAttribute("examineeName")%></font>
    </h4>    
</center>
<div id="page">
    <div align="center" class="containerr1" id="header-wrapperr1">
        <form name="helpQuestionPagination">
            <input type="hidden" name="action" value="questionPagination"/>
        </form>
        <form name="forma" method="post" action="ExamineeApplicationServlet">
            <div id="qsection">
                <table width="100%" cellspacing="5" cellpadding="0" border="0">
                    <tr>
                        <td align="right">
                    <font style="font-family:vardana;color: #800000;">If You want <u>To End Your Exam</u> Click</font>
                    <input type="submit" name="action" value="StopExamination" onclick="return confirmStopExam();"/>
                    </td>
                    <td align="right">
                        <h3>Time remaining: <input type="text" name="mins" size="1" style="border:0px solid black;text-align:right;">:<input type="text" name="secs" size="1" style="border:0px solid black;"></h3> </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <fieldset>
                                <legend><b style="font-size: x-large; color: red;">*</b>Not Attend <b style="font-size: x-large; color: green;">*</b>Attend<b style="font-size: x-large; color: blue;">*</b>Current Question No</legend>
                                <%
                                    for (Integer questionId : allQuestionsId) {%>
                                <a href="#"  onclick="paginationQuestion(<%= questionId%>)"><% if (questionId == questionInsertVO.getQuestionId()) {%> <b style="font-size: x-large; color: blue;"> <%= questionId%></b> 
                                    <%} else {
                                        QuestionInsertVO answer = answerSheet.get(questionId);
                                        if (null != answer) {
                                            if (null != answer.getGivenOption()) {
                                    %>
                                    <b style="font-size: large; color: green;"><%= questionId%></b>
                                    <%  } else {%> 
                                    <font style="color: red;"><%= questionId%></font>
                                    <%
                                        }
                                    } else {
                                    %>
                                    <font style="color: red;"><%= questionId%></font>
                                    <%
                                            }
                                        }
                                    %>
                                </a>
                                <%
                                    }
                                %> 
                            </fieldset>
                        </td>
                    </tr>
                </table>         
                <fieldset>
                    <legend><b><%=questionInsertVO.getQuestionId()%>&nbsp;of&nbsp;<%= maxQuestionId%> </b>&nbsp;</legend>
                    <table border="0" align="center" bgcolor="white" width="650px">
                        <tr>
                            <td style="float:left" colspan="2">
                                <input type="hidden" name="questionId" value="<%=questionInsertVO.getQuestionId()%>"/>
                                <input type="hidden" name="examineeId" value="<%=session.getAttribute("examineeId")%>"/>  
                                <pre style="float:left;"><%=questionInsertVO.getQuestionDesc()%> </pre>
                            </td>
                        </tr>
                        <tr>
                            <td style="float:left" colspan="2">
                                A:<input type="radio"  name="givenOption" id="givenOption" value="A" <%if (null != questionInsertVO.getGivenOption() && questionInsertVO.getGivenOption().equals("A")) {%>checked <%}%> />
                                &nbsp;&nbsp;&nbsp;
                                <%=questionInsertVO.getOptionOne()%>
                            </td>
                        </tr>
                        <tr>
                            <td style="float:left" colspan="2">
                                B:<input type="radio"  name="givenOption" id="givenOption" value="B" <%if (null != questionInsertVO.getGivenOption() && questionInsertVO.getGivenOption().equals("B")) {%>checked <%}%>/>
                                &nbsp;&nbsp;&nbsp;
                                <%=questionInsertVO.getOptionTwo()%> 
                            </td>

                        </tr>
                        <tr>
                            <td style="float:left" colspan="2">
                                C:<input type="radio"  name="givenOption" id="givenOption" value="C" <%if (null != questionInsertVO.getGivenOption() && questionInsertVO.getGivenOption().equals("C")) {%>checked <%}%> />
                                &nbsp;&nbsp;&nbsp;
                                <%=questionInsertVO.getOptionThree()%> 
                            </td>
                        </tr>
                        <tr>
                            <td style="float:left" colspan="2">
                                D:<input type="radio"  name="givenOption" id="givenOption" value="D" <%if (null != questionInsertVO.getGivenOption() && questionInsertVO.getGivenOption().equals("D")) {%>checked <%}%>/>
                                &nbsp;&nbsp;&nbsp;
                                <%=questionInsertVO.getOptionFour()%>
                            </td>
                        </tr>                
                        <tr>
                            <td colspan="2">
                                <table width="100%">
                                    <tr>
                                        <td><input type="submit" name="action" value="Previous" <% if (questionInsertVO.getQuestionId() == minQuestionId) {%> disabled="disabled"<%}%> /></td> 
                                        <td>&nbsp;</td>
                                        <td><% if (questionInsertVO.getQuestionId() == maxQuestionId) {%><input type="submit" name="action" value="Submit Answer Sheet" onclick="return confirmStopExam();"/> <%}%></td>
                                        <td><input type="submit" name="action" value="Next" <% if (questionInsertVO.getQuestionId() == maxQuestionId) {%> disabled="disabled"<%}%>/></td>                             
                                    </tr>
                                </table>                   
                            </td>                
                        </tr>         
                    </table> 
                </fieldset>
            </div>
            <div id="result" style="visibility: hidden">  
                <table  align="center">
                    <tr>
                        <td>  
                            <input type="submit" name="action" value="ExamResults" />  
                        </td>
                    </tr>
                </table>
            </div>             
        </form>
    </div>
    <script>  
        <!--  
        timer(); // call timer() after page is loaded  
        //-->  
    </script>
    <% }%>
    <jsp:include page="exam-layout/footer.jsp"/>
