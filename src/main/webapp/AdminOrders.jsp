<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Заказы</title>
    <link rel="stylesheet" href="styles/AdminOrders.css">
    <link rel="stylesheet" href="styles/AdminTopOnPages.css">
    <script>
        function showAddOrderWindow() {
            document.getElementsByClassName("add-order-window-container")[0].style.display = "flex";

        }

        function closeAddOrderWindow() {
            document.getElementsByClassName("add-order-window-container")[0].style.display = "none";
        }

        function showEditOrderWindow(id) {
            document.getElementsByClassName("edit-order-window-container")[0].style.display = "flex";
            document.getElementById("edit-order-id").value = id;
            document.getElementById("edit-order-lastname").value = document.getElementById("order-customer-" + id).innerHTML.split(" ")[0];
            document.getElementById("edit-order-firstname").value = document.getElementById("order-customer-" + id).innerHTML.split(" ")[1];
            document.getElementById("edit-order-patronymic").value = (document.getElementById("order-customer-" + id).innerHTML.split(" ")[2] === undefined ? "" : document.getElementById("order-customer-" + id).innerHTML.split(" ")[2]);
            let formattedDate = document.getElementById("order-registration-date-" + id).innerHTML;
            let parts = formattedDate.split('/');
            let formattedDateForInput = parts[2] + '-' + ('0' + parts[1]).slice(-2) + '-' + ('0' + parts[0]).slice(-2);
            document.getElementById("edit-order-registration-date").value = formattedDateForInput;
            document.getElementById("edit-order-shipping-cost").value = document.getElementById("order-shipping-cost-" + id).innerHTML;
            document.getElementById("edit-order-cost").value = document.getElementById("order-cost-" + id).innerHTML;
            const paymentMethod = document.getElementById("order-payment-method-" + id).innerHTML.trim();
            if (paymentMethod === "Наличными при получении") {
                document.getElementById("edit-order-payment-method").value = "cash";
            } else if (paymentMethod === "Картой при получении") {
                document.getElementById("edit-order-payment-method").value = "card";
            } else if (paymentMethod === "Онлайн-оплата") {
                document.getElementById("edit-order-payment-method").value = "online";
            }
            const status = document.getElementById("order-status-" + id).innerHTML.trim();
            if (status === "Обрабатывается") {
                document.getElementById("edit-order-status").value = "processing";
            } else if (status === "Доставляется") {
                document.getElementById("edit-order-status").value = "shipping";
            } else if (status === "Вручено") {
                document.getElementById("edit-order-status").value = "delivered";
            } else if (status === "Отменен") {
                document.getElementById("edit-order-status").value = "canceled";
            }
        }

        function closeEditOrderWindow() {
            document.getElementsByClassName("edit-order-window-container")[0].style.display = "none";
        }

        function showDeleteOrderWindow(id) {
            document.getElementsByClassName("delete-order-window-container")[0].style.display = "flex";
            document.getElementById("delete-order-id").value = id;
        }

        function closeDeleteOrderWindow() {
            document.getElementsByClassName("delete-order-window-container")[0].style.display = "none";
        }

        function showInfoOrderWindow(id) {
            document.getElementsByClassName("info-order-window-container")[0].style.display = "flex";
            var productsContainer = document.getElementsByClassName("order-products-");
            productsContainer.style.display = "none";
        }

        function closeInfoOrderWindow(event) {
            if (!event.target.contains(document.getElementById('info-order-window-container'))) return;
            document.getElementsByClassName("info-order-window-container")[0].style.display = "none";
        }

        document.addEventListener('click', closeInfoOrderWindow);

        let productIndex = 1;

        function addProductInput() {
            const container = document.getElementById('order-products-container');
            const newInput = document.createElement('div');
            newInput.className = 'order-product-input';
            newInput.innerHTML = `
        <select name="product[` + productIndex + `].id" class="product-select" required>
            <option value="" disabled selected>Выберите товар</option>
            <c:forEach var="product" items="${products}">
                <option value="${product.id}">${product.title}, цена: ${product.price}</option>
            </c:forEach>
        </select>
        <input type="number" name="product[` + productIndex + `].quantity" placeholder="Количество" min="1" required>
        <button type="button" class="remove-product-button" onclick="removeProductInput(this)">Удалить</button>
    `;
            container.appendChild(newInput);

            document.getElementById('product-index').value = productIndex;
            productIndex++;
        }

        function removeProductInput(button) {
            button.parentElement.remove();
        }
    </script>
</head>
<body>
<div class="top">
    <a href="/AdminMain"><img class="top-logo" src="/mediaFiles/logo_vect_circle.PNG" alt="logo"></a>
    <div class="content">
        <a class="top-buttons" href="/AdminCustomers">
            Заказчики
        </a>
        <a class="top-buttons" href="/AdminOrders">
            Заказы
        </a>
        <a class="top-buttons" href="/AdminProducts">
            Товары
        </a>
        <form class="search-form" action="/AdminOrders/searchOrder" method="get">
            <input type="text" name="fullname" id="search-input-product" placeholder="Поиск...">
            <button id="search-button" type="submit"><img id="search-icon" src="/mediaFiles/icon-search.png"></button>
        </form>
        <a href="/" id="top-login-a"><img class="top-buttons" id="top-login" src="/mediaFiles/icon-user.png"></a>
    </div>
</div>
<div class="orders-header">
    <t class="orders-count">${orders.size()}</t>
    <button class="add-order-button" onclick="showAddOrderWindow()">Добавить заказ</button>
</div>
<div class="main-content">
    <div class="orders-container">
        <c:forEach var="order" items="${orders}">
            <div class="order-card">
                <div class="order-header">
                    <h3>Заказ №${order.id}</h3>
                    <p>Заказчик: ${order.customer.fullname}</p>
                    <p>Телефон: ${order.customer.phone_number}</p>
                    <p>Адрес: ${order.customer.address}</p>
                </div>
                <div class="order-products">
                    <h4>Список товаров:</h4>
                    <table>
                        <thead>
                        <tr>
                            <th>Название</th>
                            <th>Цена (₽)</th>
                            <th>Количество</th>
                            <th>Общая стоимость (₽)</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="entry" items="${order.products}">
                            <tr>
                                <td>${entry.key.title}</td>
                                <td>${entry.key.price}</td>
                                <td>${entry.value}</td>
                                <td>${entry.key.price * entry.value}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="order-footer">
                    <p><b>Общая стоимость заказа:</b> ${order.order_cost} ₽</p>
                    <form action="/AdminOrders" method="post" class="delete-form">
                        <input type="hidden" name="delete" value="1">
                        <input type="hidden" name="orderId" value="${order.id}">
                        <button type="submit" class="delete-button">Удалить заказ</button>
                    </form>
                </div>
            </div>
        </c:forEach>
    </div>


    <div class="add-order-window-container">
        <div class="add-order-window">
            <form class="add-order-window-content" action="/AdminOrders" method="post">
                <input type="hidden" id="product-index" name="productIndex" value="">
                <label for="customer-select">Заказчик:</label>
                <select id="customer-select" name="customerId" required>
                    <option value="" disabled selected>Выберите заказчика</option>
                    <c:forEach var="customer" items="${customers}">
                        <option value="${customer.id}">${customer.fullname}</option>
                    </c:forEach>
                </select>

                <label for="order-products-container">Товары:</label>
                <div id="order-products-container">
                    <div class="order-product-input">
                        <select name="product[0].id" class="product-select" required>
                            <option value="" disabled selected>Выберите товар</option>
                            <c:forEach var="product" items="${products}">
                                <option value="${product.id}">${product.title}, цена: ${product.price}</option>
                            </c:forEach>
                        </select>
                        <input type="number" name="product[0].quantity" placeholder="Количество" min="1" required>
                        <button type="button" class="remove-product-button" onclick="removeProductInput(this)">Удалить</button>
                    </div>
                </div>
                <button type="button" id="add-product-button" onclick="addProductInput()">Добавить товар</button>

                <div class="add-order-window-buttons">
<%--                    <button type="button" class="add-order-window-button" onclick="closeAddOrderWindow()">Отмена</button>--%>
                    <a href="AdminOrders"><button type="button" class="add-order-window-button">Отмена</button></a>
                    <button type="submit" class="add-order-window-button">Добавить</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>