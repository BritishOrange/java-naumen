const productsContainer = document.querySelector('#products-container');

// Запускаем getProducts
getProducts();

// Асинхронная функция получения данных из файла products.json
async function getProducts() {
	// Получаем данные из products.json
    const response = [
		{
			"id": 1,
			"title": "MacBook Air",
			"category": "Ноутбуки",
			"weight": 2070,
			"price": 160000,
			"imgSrc": "laptop2.jpg"
		},
		{
			"id": 2,
			"title": "Apple Watch 4",
			"category": "Часы",
			"weight": 205,
			"price": 25000,
			"imgSrc": "watch1.jpg"
		},
		{
			"id": 3,
			"title": "MacBook Air",
			"category": "Ноутбуки",
			"weight": 2070,
			"price": 160000,
			"imgSrc": "laptop2.jpg"
		},
		{
			"id": 4,
			"title": "Apple Watch 4",
			"category": "Часы",
			"weight": 205,
			"price": 25000,
			"imgSrc": "watch1.jpg"
		}
	];
    // Парсим данные из JSON формата в JS
    const productsArray = response;
    // Запускаем ф-ю рендера (отображения товаров)
	renderProducts(productsArray);
}

function renderProducts(productsArray) {
    productsArray.forEach(function (item) {
        const productHTML = `<div class="col-md-6">
						<div class="card mb-4" data-id="${item.id}">
							<img class="product-img" src="./catalog/img/roll/${item.imgSrc}" alt="">
							<div class="card-body text-center">
								<h4 class="item-title">${item.title}</h4>
								<p><small data-items-in-box class="text-muted">${item.category}</small></p>

								<div class="details-wrapper">

									<!-- Счетчик -->
									<div class="items counter-wrapper">
										<div class="items__control" data-action="minus">-</div>
										<div class="items__current" data-counter>1</div>
										<div class="items__control" data-action="plus">+</div>
									</div>
									<!-- // Счетчик -->

									<div class="price">
										<div class="price__weight">${item.weight}г.</div>
										<div class="price__currency">${item.price} ₽</div>
									</div>
								</div>

								<button data-cart type="button" class="btn btn-block btn-outline-warning">
									+ в корзину
								</button>

							</div>
						</div>
					</div>`;
        productsContainer.insertAdjacentHTML('beforeend', productHTML);
    });
}