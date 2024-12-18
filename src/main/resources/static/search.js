async function searchBooks() {
    const query = document.getElementById('bookFilter').value;
    const select = document.getElementById('book');

    if (query.length < 2) { // Начинаем поиск, только если введено >=2 символов
        select.innerHTML = ''; // Очищаем список
        return;
    }

    const response = await fetch(`/api/books/search?query=${query}`);
    const books = await response.json();

    select.innerHTML = ''; // Очищаем список перед обновлением
    books.forEach(book => {
        const option = document.createElement('option');
        option.value = book.id;
        option.textContent = book.title;
        select.appendChild(option);
    });
}

async function searchClients() {
    const query = document.getElementById('clientFilter').value;
    const select = document.getElementById('client');

    if (query.length < 2) { // Поиск начинается, только если введено >=2 символов
        select.innerHTML = ''; // Очищаем список
        return;
    }

    const response = await fetch(`/api/clients/search?q=${query}`);
    const clients = await response.json();

    select.innerHTML = ''; // Очищаем список перед обновлением
    clients.forEach(client => {
        const option = document.createElement('option');
        option.value = client.id;
        option.textContent = client.fullName;
        select.appendChild(option);
    });
}