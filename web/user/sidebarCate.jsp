<%-- 
    Document   : layoutCate
    Created on : Oct 30, 2025, 12:57:57 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<ul id="categoryList">
    <li>
        <a href="#" data-id="1">
            <div class="cate__bg">
                <img class="cate__img" src="${pageContext.request.contextPath}/assets/img/hamburger_icon.svg" alt="hamburger"/>
            </div>
            <p>Đồ ăn</p>
        </a>
    </li>
    <li>
        <a href="url" data-id="2">
            <div class="cate__bg">
                <img  class="cate__img" src="${pageContext.request.contextPath}/assets/img/coffee_icon.svg" alt="pizza"/>
            </div>
            <p>Đồ uống</p>
        </a>
    </li>
    <li>
        <a href="url" data-id="3"> 
            <div class="cate__bg">
                <img  class="cate__img" src="${pageContext.request.contextPath}/assets/img/sandwiches.svg" alt="sandwiches"/>
            </div>
            <p>Đồ chay</p>
        </a>
    </li>
    <li>
        <a href="url" data-id="4">
            <div class="cate__bg">
                <img  class="cate__img" src="${pageContext.request.contextPath}/assets/img/wings_icon.svg" alt="wings_icon"/>
            </div>
            <p>Ăn vặt</p>
        </a>
    </li>
    <li>
        <a href="url" data-id="5">
            <div class="cate__bg">
                <img  class="cate__img" src="${pageContext.request.contextPath}/assets/img/pizza_icon.svg" alt="coffee_icon"/>
            </div>
            <p>Đồ ngọt</p>
        </a>
    </li>
</ul>
