<%-- 
    Document   : examinee-back
    Created on : Jan 27, 2013, 4:50:47 PM
    Author     : TPCS
--%>

<jsp:include page="exam-layout/index-header.jsp"/>
<div id="page">
    <fieldset>
        <legend><b> <%if (null != session.getAttribute("examineeName")) {%> <%=session.getAttribute("examineeName")%> Registration Id : <%=session.getAttribute("examineeId")%><%} else {%> Sorry <%}%></b></legend>
      <h1> <%if (null != session.getAttribute("examineeName")) {%> You already attained in examination <%}else{%>Page Not Found<%}%></h1>
        <p>
            Please contact <a href="http://www.tpcsglobal.com/contact-us.do" target="_blank">TPCS Global</a>
        </p>

    </fieldset>
    <jsp:include page="exam-layout/footer.jsp"/>
