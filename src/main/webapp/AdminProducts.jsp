<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Товары</title>
    <link rel="stylesheet" href="styles/AdminProducts.css">
    <link rel="stylesheet" href="styles/AdminTopOnPages.css">
    <script>
        function showAddProductWindow() {
            document.getElementsByClassName("add-product-window-container")[0].style.display = "flex";

        }

        function closeAddProductWindow() {
            document.getElementsByClassName("add-product-window-container")[0].style.display = "none";
        }

        function showEditProductWindow(id) {
            document.getElementsByClassName("edit-product-window-container")[0].style.display = "flex";
            document.getElementById("edit-product-id").value = id;
            document.getElementById("edit-product-title").value = document.getElementById("product-title-" + id).innerHTML;
            document.getElementById("edit-product-price").value = document.getElementById("product-price-" + id).innerHTML;
            document.getElementById("edit-product-description").value = document.getElementById("product-description-" + id).innerHTML;
        }

        function closeEditProductWindow() {
            document.getElementsByClassName("edit-product-window-container")[0].style.display = "none";
        }

        function showDeleteProductWindow(id) {
            document.getElementsByClassName("delete-product-window-container")[0].style.display = "flex";
            document.getElementById("delete-product-id").value = id;
        }

        function closeDeleteProductWindow() {
            document.getElementsByClassName("delete-product-window-container")[0].style.display = "none";
        }
    </script>
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
<div class="products-header">
    <t class="products-count">${products.size()}</t>
    <button class="add-product-button" onclick="showAddProductWindow()">Добавить товар</button>
</div>
<div class="main-content">
    <c:forEach var="product" items="${products}">
        <div class="each-product">
            <div class="product">
                <t class="product-title" id="product-title-${product.id}">${product.title}</t>
                <t class="product-price" id="product-price-${product.id}">${product.price}</t>
                <t class="product-description" id="product-description-${product.id}">${product.description}</t>
                <div class="product-buttons">
                    <button class="product-edit-button" onclick="showEditProductWindow(${product.id})">
                        Редактировать
                    </button>
                    <button class="product-delete-button" onclick="showDeleteProductWindow(${product.id})">
                        Удалить
                    </button>
                </div>
            </div>
        </div>
    </c:forEach>
    <div class="add-product-window-container" id="add-product-window-container">
        <div class="add-product-window">
            <form class="add-product-window-content" action="AdminProducts" method="post">
                <input name="postType" value="add" type="hidden">
                <input id="add-product-title" name="title" type="text" placeholder="Наименование товара">
                <input id="add-product-price" name="price" type="number" placeholder="Цена товара">
                <input id="add-product-description" name="description" type="text" placeholder="Описание товара">
                <div class="add-product-window-buttons">
                    <button class="add-product-window-button" type="button" onclick="closeAddProductWindow()">Отмена
                    </button>
                    <button class="add-product-window-button" type="submit" style="margin-left: 60%">Добавить</button>
                </div>
            </form>
        </div>
    </div>
    <div class="edit-product-window-container" id="edit-product-window-container">
        <div class="edit-product-window">
            <form class="add-product-window-content" action="AdminProducts" method="post">
                <input name="postType" value="edit" type="hidden">
                <input id="edit-product-id" name="id" type="hidden">
                <input id="edit-product-title" name="title" type="text" placeholder="Наименование товара">
                <input id="edit-product-price" name="price" type="number" placeholder="Цена товара">
                <input id="edit-product-description" name="description" type="text" placeholder="Описание товара">
                <div class="edit-product-window-buttons">
                    <button class="edit-product-window-button" type="button" onclick="closeEditProductWindow()">Отмена
                    </button>
                    <button class="edit-product-window-button" type="submit" style="margin-left: 60%">Сохранить</button>
                </div>
            </form>
        </div>
    </div>
    <div class="delete-product-window-container" id="delete-product-window-container">
        <div class="delete-product-window">
            <form class="delete-product-window-content" action="AdminProducts" method="post">
                <input name="postType" value="delete" type="hidden">
                <input id="delete-product-id" name="id" type="hidden">
                <label id="delete-product-label">Вы действительно хотите удалить товар?</label>
                <div class="delete-product-window-buttons">
                    <button class="delete-product-window-button" type="button" onclick="closeDeleteProductWindow()">
                        Отмена
                    </button>
                    <button class="delete-product-window-button" type="submit" style="margin-left: 20%">Удалить</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>