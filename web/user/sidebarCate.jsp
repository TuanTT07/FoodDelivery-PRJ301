<%-- 
    Document   : layoutCate
    Created on : Oct 30, 2025, 12:57:57 PM
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul id="categoryList">
    <li>
        <a href="${pageContext.request.contextPath}/MainController?idCate=SC001&action=searchStoreByCate" data-id="1"   class="${selectedCateId == 'SC001' ? 'active' : ''}">
            <div class="cate__bg">
                <img class="cate__img" src="${pageContext.request.contextPath}/assets/img/hamburger_icon.svg" alt="Đồ ăn"/>
            </div>
            <p>Đồ ăn</p>
        </a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/MainController?idCate=SC002&action=searchStoreByCate" data-id="2"   class="${selectedCateId == 'SC002' ? 'active' : ''}">
            <div class="cate__bg">
                <img  class="cate__img" src="${pageContext.request.contextPath}/assets/img/coffee_icon.svg" alt="Đồ uống"/>
            </div>
            <p>Đồ uống</p>
        </a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/MainController?idCate=SC003&action=searchStoreByCate" data-id="3"   class="${selectedCateId == 'SC003' ? 'active' : ''}"> 
            <div class="cate__bg">
                <img  class="cate__img" src="${pageContext.request.contextPath}/assets/img/sandwiches.svg" alt="Đồ chay"/>
            </div>
            <p>Đồ chay</p>
        </a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/MainController?idCate=SC004&action=searchStoreByCate" data-id="4"   class="${selectedCateId == 'SC004' ? 'active' : ''}">
            <div class="cate__bg">
                <img  class="cate__img" src="${pageContext.request.contextPath}/assets/img/wings_icon.svg" alt="Ăn vặt"/>
            </div>
            <p>Ăn vặt</p>
        </a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/MainController?idCate=SC005&action=searchStoreByCate" data-id="5"   class="${selectedCateId == 'SC005' ? 'active' : ''}">
            <div class="cate__bg">
                <img  class="cate__img" src="${pageContext.request.contextPath}/assets/img/pizza_icon.svg" alt="Đồ ngọt"/>
            </div>
            <p>Đồ ngọt</p>
        </a>
    </li>
</ul>
