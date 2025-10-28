let counter = 0;

//Adding the Color Change to the Color Button
const changeColorBtn = document.getElementById('changeColorBtn');
const addItemBtn    = document.getElementById('addItemBtn');


//Adding add List Item to Add List Item Button
changeColorBtn.addEventListener('click', changeBackgroundColor);
addItemBtn.addEventListener('click', addListItem);

//Change Background
function changeBackgroundColor() {
    const bodyElement = document.querySelector("body");
    bodyElement.style.backgroundColor = "green";
}

//Add List Item
function addListItem() {
    counter++;
    const list = document.getElementById('itemList');
    const newItem = document.createElement('li');
    const itemText = document.createTextNode(`List Item: ${counter}`);
    newItem.appendChild(itemText);
    list.appendChild(newItem);
}

