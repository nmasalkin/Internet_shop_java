<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Заказчики</title>
    <link rel="stylesheet" href="styles/AdminCustomers.css">
    <link rel="stylesheet" href="styles/AdminTopOnPages.css">
    <script>
        function showAddCustomerWindow() {
            document.querySelector(".add-customer-window-container").style.display = "flex";
        }

        function closeAddCustomerWindow() {
            document.querySelector(".add-customer-window-container").style.display = "none";
        }

        function showEditCustomerWindow(id) {
            document.querySelector(".edit-customer-window-container").style.display = "flex";
            document.getElementById("edit-customer-id").value = id;
            document.getElementById("edit-customer-fullname").value = document.getElementById("customer-fullname-" + id).innerHTML;
            document.getElementById("edit-customer-phone_number").value = document.getElementById("customer-phone_number-" + id).innerHTML;
            document.getElementById("edit-customer-address").value = document.getElementById("customer-address-" + id).innerHTML;
        }

        function closeEditCustomerWindow() {
            document.querySelector(".edit-customer-window-container").style.display = "none";
        }

        function showDeleteCustomerWindow(id) {
            document.querySelector(".delete-customer-window-container").style.display = "flex";
            document.getElementById("delete-customer-id").value = id;
        }

        function closeDeleteCustomerWindow() {
            document.querySelector(".delete-customer-window-container").style.display = "none";
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
<div class="customers-header">
    <t class="customers-count">${customers.size()}</t>
    <button class="add-customer-button" onclick="showAddCustomerWindow()">Добавить заказчика</button>
</div>
<div class="main-content">
    <c:forEach var="customer" items="${customers}">
        <div class="each-customer">
            <div class="customer">
                <t class="customer-fullname" id="customer-fullname-${customer.id}">${customer.fullname}</t>
                <t class="customer-phone_number" id="customer-phone_number-${customer.id}">${customer.phone_number}</t>
                <t class="customer-address" id="customer-address-${customer.id}">${customer.address}</t>
                <div class="customer-buttons">
                    <button class="customer-edit-button" onclick="showEditCustomerWindow(${customer.id})">
                        Редактировать
                    </button>
                    <button class="customer-delete-button" onclick="showDeleteCustomerWindow(${customer.id})">
                        Удалить
                    </button>
                </div>
            </div>
        </div>
    </c:forEach>
    <div class="add-customer-window-container" id="add-customer-window-container">
        <div class="add-customer-window">
            <form class="add-customer-window-content" action="AdminCustomers" method="post">
                <input name="postType" value="add" type="hidden">
                <input id="add-customer-fullname" name="fullname" type="text" placeholder="ФИО">
                <input id="add-customer-phone_number" name="phone_number" type="number" placeholder="Телефон">
                <input id="add-customer-address" name="address" type="text" placeholder="Адрес">
                <div class="add-customer-window-buttons">
                    <button class="add-customer-window-button" type="button" onclick="closeAddCustomerWindow()">Отмена
                    </button>
                    <button class="add-customer-window-button" type="submit" style="margin-left: 60%">Добавить</button>
                </div>
            </form>
        </div>
    </div>
    <div class="edit-customer-window-container" id="edit-customer-window-container">
        <div class="edit-customer-window">
            <form class="edit-customer-window-content" action="AdminCustomers" method="post">
                <input name="postType" value="edit" type="hidden">
                <input id="edit-customer-id" name="id" type="hidden">
                <input id="edit-customer-fullname" name="fullname" type="text" placeholder="ФИО">
                <input id="edit-customer-phone_number" name="phone_number" type="number" placeholder="Телефон">
                <input id="edit-customer-address" name="address" type="text" placeholder="Адрес">
                <div class="edit-customer-window-buttons">
                    <button class="edit-customer-window-button" type="button" onclick="closeEditCustomerWindow()">
                        Отмена
                    </button>
                    <button class="edit-customer-window-button" type="submit" style="margin-left: 60%">Сохранить
                    </button>
                </div>
            </form>
        </div>
    </div>
    <div class="delete-customer-window-container" id="delete-customer-window-container">
        <div class="delete-customer-window">
            <form class="delete-customer-window-content" action="AdminCustomers" method="post">
                <input name="postType" value="delete" type="hidden">
                <input id="delete-customer-id" name="id" type="hidden">
                <label id="delete-customer-label">Вы действительно хотите удалить заказчика?</label>
                <div class="delete-customer-window-buttons">
                    <button class="delete-customer-window-button" type="button" onclick="closeDeleteCustomerWindow()">
                        Отмена
                    </button>
                    <button class="delete-customer-window-button" type="submit" style="margin-left: 20%">Удалить
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>