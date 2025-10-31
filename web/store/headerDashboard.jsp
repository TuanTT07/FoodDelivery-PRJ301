
<div class="store__header">
    <form action="SearchController" method="post" class="store__search-form">
        <input type="text" name="keyword" placeholder="Search..." class="store__search-input">
        <button type="submit" class="store__search-button">
            <img src="${pageContext.request.contextPath}/assets/img/search.svg" alt="search" class="store__search-icon"/> 
        </button>
    </form>

    <div class="store__user-info">
        <p class="store__user-greeting">Hello, <span class="store__user-name">${sessionScope.OwnerStoreName}</span></p>
        <img src="${pageContext.request.contextPath}/assets/img/avatar.svg" alt="Avatar" class="store__avatar"/>
    </div>
</div>