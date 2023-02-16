<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: GHAR
  Date: 26/12/2019
  Time: 07:44 am
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>searchPage</title>
    <link href="style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<form action="search" method="get">
    <input type="text" name="searchKey" id="search">
    <input type="hidden" name="page" value="1">
    <input type="hidden" name="firstVisit" value="1">
    <input type="submit" value="submit">
</form>

<div class="container">
    <div class="row">
        <div class="col-md-5">

        </div>
        <div class="col-md-7">

        </div>
    </div>
</div>

<c:forEach items="${productsArrayList}" var="products">
    <p><img src="${products.getImageUrl()}" alt=""></p>
    <h2>${products.getName()}</h2>
    <p>${products.getPrice()}</p>
    <p>${products.getLink()}</p>
</c:forEach>
<c:forEach var="i" begin="1" end="${pagesRequired}">
    <a href="${pageContext.request.contextPath}/search?searchKey=${searchKey}&page=${i}&firstVisit=0">${i}</a>
</c:forEach>
</body>

</html>
