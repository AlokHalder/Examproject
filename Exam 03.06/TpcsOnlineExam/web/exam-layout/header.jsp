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
        <link href="css/style.css" rel="stylesheet" type="text/css" media="screen" />
        <script type="text/javascript" src="jss/check.js"></script>
        <script type="text/javascript">
            window.history.forward();
            function noBack() { window.history.forward(); }
        </script>
    </head>
    <body onload="noBack();"
          onpageshow="if (event.persisted) noBack();" onunload="">
        <div id="wrapper">
            <div id="header-wrapper" class="container">
                <div id="header" class="container">
                    <div id="logo">
                        <a href="http://www.tpcsglobal.com" target="_blank"><img src="css/images/tpcs-logo_n.png" width="331" height="53" alt="TPCS Global" style="padding-top: 25px;"/> 
                        </a>
                    </div>
                    <div id="menu">
                        <ul>
                            <li><form action="ExamineeApplicationServlet" method="post" name="home">
                                    <input type="hidden" value="home" name="action"/>
                                    <a href="javascript:document.home.submit()">Home</a>
                                </form>
                            </li>
                            <li>
                                <a href="#">Administrator</a>
                            </li>
                        </ul>
                    </div>
                </div>
                <img src="css/images/img03.png" width="1000" height="40" alt="" />
            </div>


