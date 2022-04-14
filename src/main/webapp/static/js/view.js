export {addEventListener, addEventListenerToAll, showProducts, showCart, editCartProductAmount, editTotalCartPrice};
import {editCart} from "./controller.js";

function addEventListener(selector, func) {
    document.querySelector(selector).addEventListener('click', func);
}

function loadContent(selector, content) {
    document.querySelector(selector).innerHTML = content;
}

function showProducts(products) {
    let productContent = products.map(product => {
        return buildCard(product);
    }).join('');
    loadContent('#products', productContent);
}

function buildCard(product) {
    return `<div class="col col-sm-12 col-md-6 col-lg-4">
                <div class="card">
                    <img class="" src="${product.imagePath}" alt="http://placehold.it/400x250/000/fff"/>
                    <div class="card-header">
                        <h4 class="card-title">${product.name}</h4>
                        <p class="card-text">${product.description}</p>
                    </div>
                    <div class="card-body">
                        <div class="card-text">
                            <p class="lead">${product.defaultPrice} ${product.defaultCurrency}</p>
                        </div>
                        <div class="card-text">
                        <button data-btn-id="${product.id}" class="btn btn-success cart-btn">Add to cart</button>
                        </div>
                    </div>
                </div>
            </div>`;
}

function showCart(cart) {
    let cartContent = cart.map(prod => {
        return buildCartElement(prod);
    }).join('');
    loadContent("#cart-body", cartContent);
    setCartTotalPrice(cart)
    setupCartProductAmountEdit()
}

function setCartTotalPrice(cart){
    const totalPrice = cart.map(e => {
        return Number(e.defaultPrice) * Number(e.amount)
    }).reduce((a, b) => a + b, 0)
    document.querySelector("#total-cart-price").textContent = String(Math.round(totalPrice)) + "$"
    document.querySelector("#total-cart-price").dataset.total = String(totalPrice)
}

function setupCartProductAmountEdit(){
    document.querySelectorAll(".edit").forEach(e => e.addEventListener("click", editCart))
}


function editCartProductAmount(value, quantity, id, defaultCurrency, defaultPrice){
    document.querySelector(`#amountId${id}`).textContent = String(Math.round(quantity + value))
    document.querySelector(`#product-total${id}`).textContent = `${Math.round(defaultPrice * (quantity + value))} ${defaultCurrency}`
}

function editTotalCartPrice(value, total, defaultPrice, defaultCurrency){
    if (value === 1){
        document.querySelector("#total-cart-price").textContent = Math.round((total + defaultPrice)) + "$"
        document.querySelector("#total-cart-price").dataset.total = String(total + defaultPrice)
    } else if (value === -1){
        document.querySelector("#total-cart-price").textContent = Math.round(total - defaultPrice) + "$"
        document.querySelector("#total-cart-price").dataset.total = String(total - defaultPrice)
    }
}

function buildCartElement(product) {
    return `<tr>
            <td class="w-25">
                <img src="${product.imagePath}"  alt="${product.name} + '.jpg'" class="img-fluid img-thumbnail" >
            </td>
            <td id="product-name">${product.name}</td>
            <td>${product.defaultPrice} ${product.defaultCurrency}</td>
            <td class="qty"><p id=${"amountId" + product.id} type="text" class="amount form-control" >${product.amount}</p></td>
            <td id=${"product-total" + product.id} data-default-price=${product.defaultPrice} data-default-currency=${product.defaultCurrency}>${product.defaultPrice * product.amount} ${product.defaultCurrency}</td>
            <td data-product-id=${product.id}>
                <h2 data-value="1" class="edit">+</h2>
                <h2 data-value="-1" class="edit"> -</h2>
            </td>
        </tr>`
}

function addEventListenerToAll(selector, func) {
    const elements = document.querySelectorAll(selector)
    for (let i = 0; i < elements.length; i++) {
        elements[i].addEventListener('click', func)
    }
}


