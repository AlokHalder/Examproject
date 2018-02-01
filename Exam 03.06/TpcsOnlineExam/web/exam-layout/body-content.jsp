<%-- 
    Document   : body-content
    Created on : Jan 22, 2013, 2:21:45 PM
    Author     : TPCS
--%>
<% String message = (String) request.getAttribute("message");%>
<div id="page">
    <div id="content">
        <div class="post">
            <h3 class="title"><a href="#">Welcome to Online Aptitude Test </a></h3>
            <p class="meta"><span class="date"><%= new java.util.Date()%></span>
            <div style="clear: both;">&nbsp;</div>
            <div class="entry">
                <p>This is <strong>TPCS Online Aptitude Test </strong>, a free, fully standard-compliant for core JAVA and basic of C Programming Language for <a href="http://www.tpcsglobal.com">TPCS</a> new employee associates.     
                </p>
                <p>If You are new examinee you have to <a href="javascript:document.Register.submit()" title="Register"><strong>Register</strong></a> first and collect your Registration ID for future enquires.</p>
                <p>At first to attend the Aptitude Test please read our <a href="#"><strong>Rules And Regulation</strong></a> 
                    then follow the <a href="#"><strong>Process</strong></a> accordingly.</p>
                <form action="ExamineeApplicationServlet" method="post" name="Register">
                    <p class="links"><a href="#" class="more" title="Read More">Read More</a>
                        <input type="hidden" value="RegisterNewExmee" name="action"/>
                        <a href="javascript:document.Register.submit()" class="comments" title="Register">Register</a> 
                    </p>
                </form>

            </div>
        </div>
        <div style="clear: both;">&nbsp;</div>
    </div>
    <!-- end #content -->
    <div id="sidebar">
        <ul>
            <li>
                <h2>Examinee Login</h2>
      
                   
                        <div>
                            <% if (null != message) {%><%=message%><%}%>

                            <form action="ExamineeApplicationServlet" method="post">
                                <fieldset>
                                    <legend>Examinee Login </legend>
                                    <table border="0">
                                        <tr>
                                            <td>Your Name:</td>
                                            <td><input type="text" required="" name="examineeName"/></td>
                                        </tr>
                                        <tr>
                                            <td>Registration ID:</td>
                                            <td><input type="text" required="" name="examineeId"/></td>
                                        </tr>
                                        <tr>
                                            <td>Email:</td>
                                            <td><input type="text" required="" name="examineeMail"/></td>
                                        </tr>
                                        <tr>
                                            <td><input type="hidden"  name="action" value="Ready"/></td>
                                            <td><input type="submit" value="Submit" style="margin-right: 10px"/> <input type="reset" name="reset" value="Reset"/></td>
                                        </tr>
                                         <tr>
                                             <td colspan="2" style="float: right"><a href="javascript:document.Register.submit()" title="New Examinee">New Examinee</a></td>
                                          
                                        </tr>
                                        
                                    </table>

                                </fieldset>

                            </form>
                        </div>                      
                    
                
            </li>
        </ul>
    </div>
