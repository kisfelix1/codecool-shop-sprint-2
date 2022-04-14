export {getProductsFiltered, getCart, sendProductToCart, sendEmailToBackend, editCartContent, checkCorrectLogin, registerUserInDatabase, sendRegisterEmailToBackend};

function sendProductToCart(id){
    const url = `/api/add_cart?itemId=${id}`;
    return getResponse(url);
}

export async function switchDbBtn(){
    await getResponse("/api/switch_db")
}

async function getResponse(url) {
    const response = await fetch(url);
    return response.json();
}

async function PostResponse(url, data) {
    const setupObj = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    }
    const response = await fetch(url, setupObj);
    return response.json();
}

async function sendResponse(url, data) {
    await fetch(url, {
        method: "POST",
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(data)
    })}

async function getProductsFiltered(categoryId, supplierId) {
    return getResponse(`/api/product?categoryId=${categoryId}&supplierId=${supplierId}`);
}

async function editCartContent(url, data){
    await PostResponse(url, data)
}

async function getCart() {
    return await getResponse("/api/review_cart");
}

async function sendEmailToBackend(emailAddress) {
    await sendResponse("/api/sendEmail", emailAddress)
}

async function sendRegisterEmailToBackend(emailAddress) {
    await sendResponse("/api/sendRegisterEmail", emailAddress)
}

async function checkCorrectLogin(url, data) {
    return await PostResponse(url, data);
}

async function registerUserInDatabase(url, data) {
    await PostResponse(url, data)
}
