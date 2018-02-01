<%@page import="com.tpcs.vo.ResultVO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.tpcs.vo.ExamineeVO"%>
<% if (session.getAttribute("adminID") == null && session.getAttribute("adminPassword") == null) {
%>
<jsp:forward page="admin-login.jsp"/>
<% }%>
<jsp:include page="/exam-layout/header.jsp"/>
<%
    ExamineeVO examineeDE = (ExamineeVO) request.getAttribute("examineeDE");
    ExamineeVO examineeVO = (ExamineeVO) request.getAttribute("examineeId");
    ResultVO totalQuestion = (ResultVO) request.getAttribute("totalQuestion");
    List list = examineeVO.getListOfexamineeId();
%>
<div id="page">
    <div id="content">
        <div class="post">
            <h2 class="title"><a href="#">Examinee Details </a></h2>
            <table border="0" align="center">
                <tbody>
                    <tr>
                        <td colspan="1%">Name</td>
                        <td><% if (null != examineeDE.getExamineeName()) {%><%=examineeDE.getExamineeName()%> <%}%></td> 
                    </tr>
                    <tr>
                        <td colspan="1%">Mobile Number</td> 
                        <td><% if (null != examineeDE.getExamineeMobile()) {%><%=examineeDE.getExamineeMobile()%><%}%></td>
                    </tr>
                    <tr>
                        <td colspan="1%">Email Address</td>
                        <td><% if (null != examineeDE.getExamineeEmailId()) {%><%=examineeDE.getExamineeEmailId()%><%}%></td>
                    </tr>
                    <tr>
                        <td colspan="1%">Address</td><td>
                            <% if (null != examineeDE.getExamieeAddress()) {%> <%=examineeDE.getExamieeAddress()%><%}%></td>
                    </tr>
                    <tr>
                        <td colspan="1%">Degree</td>
                        <td><% if (null != examineeDE.getExamieeDegree()) {%><%=examineeDE.getExamieeDegree()%><%}%></td>
                    </tr>
                    <tr>
                        <td colspan="1%" >Experience</td>
                        <td><% if (null != examineeDE.getExamieeExp()) {%><%=examineeDE.getExamieeExp()%><%}%></td> 
                    </tr>
                    <tr>
                        <td colspan="1%" >Technology</td>
                        <td><% if (null != examineeDE.getExamineeTech()) {%><%=examineeDE.getExamineeTech()%><%}%></td> 
                    </tr>
                    <tr>
                        <td colspan="1%" >Date of registration</td>
                        <td><% if (null != examineeDE.getExamineeRegDate()) {%><%=examineeDE.getExamineeRegDate()%><%}%></td> 
                    </tr>
                    <tr bgcolor="#FFCC33">
                        <th align="center" colspan="100%">Examination Result</th>
                    </tr>
                    <tr >
                        <td colspan="2%">Total Question</td> 
                        <td><%= totalQuestion.getQuestionId()%> </td>
                    </tr>
                    <tr>
                        <td colspan="2%">Attended Question</td>
                        <td ><%=examineeDE.getExamResultVO().getTotalCount()%></td>
                    </tr>
                    <tr>
                        <td colspan="2%">Write Answer</td>
                        <td> <%=examineeDE.getExamResultVO().getTrueResultCount()%></td>
                    </tr>
                    <tr>
                        <td colspan="2%">Wrong Answer</td>
                        <td><%=examineeDE.getExamResultVO().getFalseResultCount()%></td>
                    </tr>
                    <tr>
                        <td colspan="2%">Grade</td><td>Grade</td> 
                    </tr>
                    <tr>
                        <%session.setAttribute("examineeId", examineeDE.getExamineeId());%>
                        <td colspan="2%">Print Result</td>
                        <td>
                            <form name="print" action="ExamineeApplicationServlet" method="post" >
                                <input type="submit" name="action" value="ExamDetail"/>
                            </form> 
                        </td>
                    </tr>                
                </tbody>
            </table>
            <p class="meta"><span class="date"><%= new java.util.Date()%> </span><span class="posted">Posted by <a href="#">Chandan</a></span></p>
            <div style="clear: both;">&nbsp;</div>
        </div>
        <div style="clear: both;">&nbsp;</div>
    </div>
    <!-- end #content -->
    <div id="sidebar">
        <ul>
            <li>
                <h2>Blogroll</h2>
                <ul>
                    <li><form name="submitForm" method="post" action="ExamineeApplicationServlet">
                            <input type="hidden" name="action" value="adminLogOut">
                            <a href="javascript:document.submitForm.submit()">Logout</a>
                        </form>
                    </li>
                    <li><a href="questionInsert.jsp">Prepare New Question Paper</a></li>
                    <li><form name="examinee" action="ExamineeApplicationServlet" method="post">
                            <input type="hidden" name="action" value="Details">
                            Select Examinee ID:<select name="examineeId" onchange="submitData()">
                                <option selected value="">Select ID</option>
                                <%
                                    for (Object examineeId : list) {
                                        Integer Id = (Integer) examineeId;
                                %>
                                <option value="<%=Id%>"><%=Id%></option>          
                                <%}%>
                            </select>
                        </form>
                    </li>
                    <li><a href="#">Send Email</a></li>
                    <li><a href="#">Request For entry </a></li>
                    <li><a href="#">Submit comment</a></li>
                </ul>
            </li>
        </ul>
    </div> 
    <jsp:include page="/exam-layout/footer.jsp"/>