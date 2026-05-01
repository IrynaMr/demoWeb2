function loadOrders() {
    const container = document.getElementById('ordersContainer');
    container.innerHTML = '<p>Завантаження...</p>';

    fetch('/users/2/orders')
        .then(response => response.json())
        .then(orders => {
            container.innerHTML = '';

            if (orders.length === 0) {
                container.innerHTML = '<p>У цього користувача ще немає замовлень.</p>';
                return;
            }           orders.forEach(order => {
                const div = document.createElement('div');
                div.className = 'order-card';

                div.innerHTML = `
                    <h3>${order.title} (ID: ${order.id})</h3>
                    <p>Статус: <span class="status-${order.status}">${order.status}</span></p>
                    <button onclick="deleteOrder(${order.id})" style="background: #dc3545;">Видалити</button>
                `;
                container.appendChild(div);
            });
        })
        .catch(error => {
            container.innerHTML = '<p style="color:red;">Помилка з\'єднання з сервером</p>';
            console.error('Помилка:', error);
        });
}

function deleteOrder(orderId) {
    if(confirm('Ви впевнені, що хочете видалити це замовлення?')) {
        fetch('/orders/' + orderId, { method: 'DELETE' })
            .then(() => {
                alert('Замовлення видалено!');
                loadOrders();
            });
    }
}