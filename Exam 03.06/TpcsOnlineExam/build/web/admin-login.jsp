<jsp:include page="/exam-layout/header.jsp"/>         
<div id="page">
    <div align="center" class="containerr" id="header-wrapperr">
        <form action="ExamineeApplicationServlet" method="post">
            <fieldset>
                <legend>Administrator Login: </legend>
                <table border="0" cellspacing="5" cellpadding="3">
                    <tr>
                        <td></td>
                        <td>
                            <font color="red"> 
                            <% String message = (String) request.getAttribute("message");
                                if (null != message) {%>
                            <%= message%> 
                            <%}%>
                            </font>
                        </td>
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
                        <td valign="top" height="100%">
                            User Name: 
                        </td>
                        <td>
                            <input type="text" name="adminID" style="width:197px" required=""/>
                        </td>
                    </tr>                    
                    <tr>
                        <td valign="top" height="100%">
                            Password:
                        </td>
                        <td>
                            <input type="password" name="adminPassword" style="width:197px" required=""/>
                        </td>
                    </tr>                   
                    <tr>
                        <td align="right" colspan="100%">
                            <input type="submit" name="action" value="Login"/>                                    
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>                        
    <jsp:include page="/exam-layout/footer.jsp"/>

