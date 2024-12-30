<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>MY - админ панель</title>
    <link rel="stylesheet" href="styles/AdminMain.css">
    <link rel="stylesheet" href="styles/AdminTopOnPages.css">
</head>
<body>
<div class="top">
    <a href="AdminMain"><img class="top-logo" src="mediaFiles/logo_vect_circle.PNG" alt="logo"></a>
    <div class="content">
        <a class="top-buttons" href="AdminCustomers">
            Заказчики
        </a>
        <a class="top-buttons" href="AdminOrders">
            Заказы
        </a>
        <a class="top-buttons" href="AdminProducts">
            Товары
        </a>
        <form class="search-form" action="AdminProducts/searchProduct" method="get">
            <input type="text" name="title" id="search-input-product" placeholder="Поиск...">
            <button id="search-button" type="submit"><img id="search-icon" src="mediaFiles/icon-search.png"></button>
        </form>
        <a href="/" id="top-login-a"><img class="top-buttons" id="top-login" src="mediaFiles/icon-user.png"></a>
    </div>
</div>
</body>
</html>
