<%--
  Created by IntelliJ IDEA.
  User: GHAR
  Date: 22/12/2019
  Time: 08:31 pm
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Scrapper</title>
      <link href="style0.css" rel="stylesheet">
  </head>
  <body>
  <div class="main">
  <form class="searchBar" action="search" method="get">
      <button class="logo" type="button">scrappy</button>
      <input type="text" class="textbar" placeholder="Keep shopping..." name="searchKey" id="search">
      <input type="hidden" name="page" value="1">
      <input type="hidden" name="firstVisit" value="1">
      <input type="submit" class="button" value="submit">
  </form>
  </div>
  </body>
</html>
