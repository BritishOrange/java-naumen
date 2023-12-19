const productsList = document.getElementById("products-list");
const searchBar = document.getElementById("search-bar");
const parser = new DOMParser();

let lastChangeTimestamp = 0;
let curCategoryId = '';
let lowerPrice = 0;
let higherPrice = 0;
let compareState = 0;
let searchText = '';

function updateLastUpdateTimestamp() {
  lastChangeTimestamp = new Date().getTime();
}

function setCategoryId(id) {
  curCategoryId = id;
  debouncedMakeAjaxRequest();
}

function setCompareState(state) {
  compareState = state;
}

function setSearchText() {
  searchText = searchBar.value;
  debouncedMakeAjaxRequest();
}

// Wait for a certain amount of time (e.g., 500ms) before making the AJAX request
function debounce(func, wait) {
  let timeout;

  return function () {
    const context = this, args = arguments;
    clearTimeout(timeout);
    timeout = setTimeout(function () {
      func.apply(context, args);
    }, wait);
  };
}

function makeAjaxRequest() {
  setTimeout(() => $.ajax({
    type: 'GET',
    url: '/api/v1/products/find-products',
    data: {
      lowerPrice: lowerPrice,
      higherPrice: higherPrice,
      categoryId: curCategoryId,
      compareState: compareState,
      searchText: searchText
    },
    success: function (response) {
      showProducts(response);
    },
    error: function (xhr, status, error) {
    }
  }), 1);
}

// Debounce the makeAjaxRequest function with a 500ms wait time
const debouncedMakeAjaxRequest = debounce(makeAjaxRequest, 1000);

function showProducts(products) {
  productsList.replaceChildren([]);
  products.forEach(product => {
    let element = parser.parseFromString(
      `
          <div class="col-md-6 col-lg-4">
            <div class="card text-center card-product">
              <div class="card-product__img">
                  <img class="card-img" src="/img/products/${product.photoUrl}" alt="">
                  <ul class="card-product__imgOverlay">
                      <li><a href="/product/${product.id}"><button><i class="ti-search"></i></button></a></li>
                      <li><button><i class="ti-shopping-cart"></i></button></li>
                  </ul>
              </div>
              <div class="card-body">
                  <p>${product.category.title}</p>
                  <h4 class="card-product__title">${product.title}</h4>
                  <p class="card-product__price"><span>${product.price}</span> ₽</p>
              </div>
            </div>
          </div>
        `, 'text/html'
    ).body.firstChild
    productsList.appendChild(element);
  })
};

$(function () {
  "use strict";

  //------- Parallax -------//
  skrollr.init({
    forceHeight: false
  });

  //------- Active Nice Select --------//
  $('select').niceSelect();

  $('select').on('change', function() {
    setCompareState($(this).val());
    debouncedMakeAjaxRequest();
  });

  //------- hero carousel -------//
  $(".hero-carousel").owlCarousel({
    items: 3,
    margin: 10,
    autoplay: false,
    autoplayTimeout: 5000,
    loop: true,
    nav: false,
    dots: false,
    responsive: {
      0: {
        items: 1
      },
      600: {
        items: 2
      },
      810: {
        items: 3
      }
    }
  });

  //------- Best Seller Carousel -------//
  if ($('.owl-carousel').length > 0) {
    $('#bestSellerCarousel').owlCarousel({
      loop: true,
      margin: 30,
      nav: true,
      navText: ["<i class='ti-arrow-left'></i>", "<i class='ti-arrow-right'></i>"],
      dots: false,
      responsive: {
        0: {
          items: 1
        },
        600: {
          items: 2
        },
        900: {
          items: 3
        },
        1130: {
          items: 4
        }
      }
    })
  }

  //------- single product area carousel -------//
  $(".s_Product_carousel").owlCarousel({
    items: 1,
    autoplay: false,
    autoplayTimeout: 5000,
    loop: true,
    nav: false,
    dots: false
  });

  //------- mailchimp --------//  
  function mailChimp() {
    $('#mc_embed_signup').find('form').ajaxChimp();
  }
  mailChimp();

  //------- fixed navbar --------//  
  $(window).scroll(function () {
    var sticky = $('.header_area'),
      scroll = $(window).scrollTop();

    if (scroll >= 100) sticky.addClass('fixed');
    else sticky.removeClass('fixed');
  });

  //------- Price Range slider -------//
  if (document.getElementById("price-range")) {

    var nonLinearSlider = document.getElementById('price-range');

    noUiSlider.create(nonLinearSlider, {
      connect: true,
      behaviour: 'tap',
      start: [500, 4000],
      range: {
        // Starting at 500, step the value by 500,
        // until 4000 is reached. From there, step by 1000.
        'min': [0],
        '10%': [500, 500],
        '50%': [4000, 1000],
        'max': [450000]
      }
    });

    let nodes = [
      document.getElementById('lower-value'), // 0
      document.getElementById('upper-value')  // 1
    ];

    // Display the slider value and how far the handle moved
    // from the left edge of the slider.
    nonLinearSlider.noUiSlider.on('update', function (values, handle, unencoded, isTap, positions) {
      nodes[handle].innerHTML = values[handle];
      [lowerPrice, higherPrice] = values;
      updateLastUpdateTimestamp();
      debouncedMakeAjaxRequest();
    });
  }
});

