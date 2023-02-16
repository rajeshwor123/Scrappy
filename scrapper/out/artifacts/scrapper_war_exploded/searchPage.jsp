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
    <title>Scrapper</title>
    <link href="style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>



<body>
<div class="wrapper">
    <div class="wrapper1">

        <div class="navBar">
            <form action="search" class="searchBar" method="get">
                <button type="button">scrappy</button>
                <input type="text" class="textbar" name="searchKey" id="search">
                <input type="hidden" name="page" value="1">
                <input type="hidden" name="firstVisit" value="1">
                <input type="submit" class="button" value="submit">
            </form>
        </div>

        <div class="container">
            <div class="row">
                <c:forEach items="${productsArrayList}" var="products">
                    <div class="col-md-3 col-sm-6">
                        <div class = "product-grid">
                            <div class="product-image">
                                <a href="${products.getLink()}">
                                    <img src="${products.getImageUrl()}" alt="">
                                </a>
                            </div>
                            <div class="product-content">
                                <h class="title"><a href="${products.getLink()}">${products.getName()}</a></h>
                                <div class="price">
                                    ${products.getPrice()}
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
<%--
<c:forEach items="${productsArrayList}" var="products">
    <p><img src="${products.getImageUrl()}" alt=""></p>
    <p>${products.getName()}</p>
    <p>${products.getPrice()}</p>
    <p>${products.getLink()}</p>
</c:forEach>
--%>
        <div class="bottomNav">
            <c:forEach var="i" begin="1" end="${pagesRequired}">
                    <a href="${pageContext.request.contextPath}/search?searchKey=${searchKey}&page=${i}&firstVisit=0">${i}</a>
            </c:forEach>
            <a href="index.jsp" >Home</a>
        </div>
    </div>
</div>
</body>

</html>
