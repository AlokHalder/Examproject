<%-- 
    Document   : header
    Created on : Jan 21, 2013, 3:25:08 PM
    Author     : TPCS
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <meta http-equiv="content-type" content="text/html; charset=utf-8" />
        <title>TPCS Online Exam</title>
        <script type="text/javascript" src="jss/check.js"></script>
    </head>
    <link href='css/comic.ttf' rel='stylesheet' type='text/css'>
        <link href='css/comicbd.ttf' rel='stylesheet' type='text/css'>
            <link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
            <body>
                <div id="wrapper">
                    <div id="header-wrapper" class="container">
                        <div id="header" class="container">
                            <div id="logo">
                                <a href="http://www.tpcsglobal.com" target="_blank"><img src="css/images/tpcs-logo_n.png" width="331" height="53" alt="TPCS Global" style="padding-top: 25px;" title="TPCS Global"/>  
                                </a>
                            </div>
                            <div id="menu">
                                <ul>
                                    <li><form action="ExamineeApplicationServlet" method="post" name="home">
                                            <input type="hidden" value="home" name="action"/>
                                            <a href="javascript:document.home.submit()">Home</a>
                                        </form>
                                    </li>
                                    <li><form action="ExamineeApplicationServlet" method="post" name="login">
                                            <input type="hidden" value="adminLogin" name="action"/>
                                            <a href="javascript:document.login.submit()">Admin</a>
                                        </form></li>
                                    <li><a href="http://www.tpcsglobal.com/company-profile.do" target="_blank">About</a></li>
                                    <li><a href="http://www.tpcsglobal.com/contact-us.do" target="_blank">Contact</a></li>
                                </ul>
                            </div>
                        </div>
                        <img src="css/images/img03.png" width="1000" height="40" alt="" />
                    </div>


