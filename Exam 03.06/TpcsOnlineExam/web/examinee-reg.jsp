<jsp:include page="/exam-layout/index-header.jsp"/>
<% String message = (String) request.getAttribute("message");%>
<center><h3>TPCS ONLINE EXAMINATION </h3></center>
<div id="page">
    <div align="center" class="containerr1" id="header-wrapperr1">
        <form method="post" action="ExamineeApplicationServlet" >
            <fieldset>
                <legend> New Examinee Registration Form</legend>
                <table border="0" cellspacing="5" cellpadding="3">
                    <tbody>
                        <tr>
                            <td></td>
                            <td><%if (null != message) {%> <font style="color: red"><%= message%> </font> <% }%></td>
                        </tr>
                        <tr>
                            <td>
                                Technology :
                            </td>
                            <td>
                                <select name="stream" id="technology" style="width:200px" required>
                                    <%@include file="st-page/technology.jspf" %>
                                </select>
                            </td>    
                        </tr>
                        <tr>
                            <td>
                                Name:
                            </td>
                            <td>
                                <input type="text" name="name" id="name" value="" maxlength="50" style="width:197px" required/>
                            </td>    
                        </tr>

                        <tr>
                            <td>
                                Mobile:
                            </td>
                            <td>
                                <input type="text" name="mobile" id="mobile" value="" maxlength="10" style="width:197px" required/>
                            </td>    
                        </tr>
                        <tr>
                            <td>
                                Address:
                            </td>
                            <td>
                                <textarea name="examieeAddress" id="examieeAddress" maxlength="80" rows="3" cols="15" style="width:197px" required></textarea>
                            </td>    
                        </tr>
                        <tr>
                            <td>
                                Highest Degree:
                            </td>
                            <td>
                                <select name="examieeDegree" id="examieeDegree" style="width:201px" required>
                                    <%@include file="st-page/degree.jspf" %>
                                </select>
                            </td>    
                        </tr>
                        <tr>
                            <td>
                                Experience:
                            </td>
                            <td>
                                <select name="examieeExp"  id="examieeExp" style="width:201px" required>
                                    <%@include file="st-page/experience.jspf" %>
                                </select>
                            </td>    
                        </tr>
                        <tr>
                            <td>
                                Email:
                            </td>
                            <td>
                                <input type="email" name="email" id="email" value="" style="width:197px" required/>
                            </td>    
                        </tr>
                        <tr>
                            <td colspan="2" align="right">
							<input type="reset" value="Reset"/>
                            <input type="submit" name="action" value="Register" onclick="return checkRegistration()"/>
							</td>
                        </tr>
                    </tbody>
                </table>
            </fieldset>

        </form>
    </div>
    <jsp:include page="/exam-layout/footer.jsp"/>
