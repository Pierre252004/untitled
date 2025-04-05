<%--
  Created by IntelliJ IDEA.
  User: pierr
  Date: 04-Apr-25
  Time: 5:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" session="true" %>
<%
  session.invalidate(); // clear session
  response.sendRedirect("login.html");
%>
