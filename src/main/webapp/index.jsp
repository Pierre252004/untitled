<%@ page language="java" contentType="text/html; charset=UTF-8" session="true" %>
<%
    String username = (String) session.getAttribute("email"); // or "username", depending on what your LoginServlet sets
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100vh;
            background-color: #f4f4f4;
            margin: 0;
        }
        .navbar {
            width: 100%;
            padding: 10px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            background-color: #007bff;
            color: white;
        }
        .logo {
            font-size: 20px;
            font-weight: bold;
            margin-left: 20px;
        }
        .tabs {
            display: flex;
            gap: 20px;
            margin: auto;
        }
        .tabs a {
            font-family: 'Verdana', sans-serif;
            font-size: 16px;
            font-weight: bold;
            color: white;
            text-decoration: none;
            padding: 8px 12px;
            border-radius: 4px;
        }
        .buttons {
            margin-right: 20px;
        }
        .buttons a {
            color: white;
            text-decoration: none;
            font-size: 16px;
            font-weight: bold;
            margin-left: 15px;
        }
        .dropdown {
            position: relative;
            display: inline-block;
        }
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 200px;
            max-width: 300px;
            box-shadow: 0px 8px 16px rgba(0,0,0,0.2);
            z-index: 1;
            overflow-y: auto;
            max-height: 300px;
        }
        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }
        .dropdown-content a:hover {
            background-color: #f1f1f1;
        }
        .dropdown:hover .dropdown-content {
            display: block;
        }
        .home-container {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);
            text-align: center;
            margin-top: 20px;
        }
    </style>
    <script>
        window.onload = function() {
            const params = new URLSearchParams(window.location.search);
            const username = params.get("username");
            const welcomeMessage = document.getElementById("welcome-message");

            if (username) {
                welcomeMessage.innerText = `Hi, ${username}! Welcome to the home page.`;
            } else {
                welcomeMessage.innerText = "Hi! Welcome to the home page.";
            }
        }
    </script>
</head>
<body>
<div class="navbar">
    <div class="logo">My Website</div>
    <div class="tabs">
        <a href="chain_list.html" class="dropbtn">Hotel Chains</a>
        <a href="hotel_list.html" class="dropbtn">Hotels</a>
        <a href="booking.html" class="dropbtn">Booking</a>
        <a href="available.html" class="dropbtn">Available</a>
    </div>
    <div class="buttons">
        <% if (username == null) { %>
        <a href="login.html">Login</a>
        <a href="signup.html">Register</a>
        <% } else { %>
        <span style="color:white; font-weight:bold;">Welcome, <%= username %></span>
        <a href="logout.jsp">Logout</a>
        <% } %>
    </div>
</div>

<div class="home-container">
    <h2>
        Hi, <%= username != null ? username : "Guest" %>! Welcome to the home page.
    </h2>
</div>
</body>
</html>
<% if (username == null) { %>
<a href="login.html">Login</a>
<% } else { %>
<span>Welcome, <%= username %></span>
<% } %>
