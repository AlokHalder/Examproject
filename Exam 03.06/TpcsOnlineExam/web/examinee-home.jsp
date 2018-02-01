<% if (session.getAttribute("examineeId") == null && session.getAttribute("examineeName") == null) {
%>
<jsp:forward page="index.jsp"/>
<% }%>
<jsp:include page="/exam-layout/header.jsp"/>
<div id="page">
    <fieldset>
        <legend><font style="font-family:vardana;color: #800000;"><b>Welcome</b></font></legend>
        <h3>
            <%=session.getAttribute("examineeName")%></br>
            <font style="font-family:vardana;color: #800000;">Your Registration id is:&nbsp;</font>
            <font style="font-family:vardana;color: red"><%=session.getAttribute("examineeId")%></font>
        </h3>  
        </br>
        <table align="center" style="padding-top: 50px;">
            <tr>
                <td>
                    <a href="ExamineeApplicationServlet?action=BeginExam" >Start Exam</a>  
                </td>
            </tr>
        </table>
    </fieldset>
    <jsp:include page="/exam-layout/footer.jsp"/>


