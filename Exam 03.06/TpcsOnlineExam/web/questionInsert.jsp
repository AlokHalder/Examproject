
<% if (session.getAttribute("adminID") == null && session.getAttribute("adminPassword") == null) {
%>
<jsp:forward page="admin-login.jsp"/>
<% }%>
<jsp:include page="/exam-layout/header.jsp"/>
<div id="page">
    <div id="content">
        <% String message = (String) request.getAttribute("message");
            if (null != message) {%>
        <%=message%>
        <%}%>
        <form method="post" action="ExamineeApplicationServlet" onSubmit="return checkQuestion();">

            <table border="0" align="center">
                <tbody>
                    <tr>
                        <td>Please Enter The Questions In The Text Area Given Below
                            <textarea name="questionDesc" id="q" rows="5" cols="50" ></textarea>
                        </td>
                    </tr>
                    <tr>
                        <td>

                            Option A : &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="optionOne" maxlength="50" id="4">
                        </td>
                    </tr>
                    <tr>
                        <td>                   
                            Option B : &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="optionTwo" maxlength="50" id="5">
                        </td>
                    </tr>
                    <tr>
                        <td>

                            Option C :&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;<input type="text" name="optionThree" maxlength="50" id="6">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Option D : &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="optionFour" maxlength="50" id="7">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Correct Option :<input type="text" name="givenOption" maxlength="1" id="one">
                        </td>
                    </tr>
                    <tr>
                        <td align="left">
                            <input type="submit" name="action" value="QuestionSubmit">
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
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
                    <li><a href="#">Send Email</a></li>
                    <li><a href="#">Request For entry </a></li>
                    <li><a href="#">Submit comment</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <jsp:include page="/exam-layout/footer.jsp"/>