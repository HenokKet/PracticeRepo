let cat = {
    name: "Fluffy",
    breed: "Bombay Cat",
    age: 3,
    meow: function() {
        return `${this.name} sys "Meow!"`;
    }
};

let owner = {
    name: "John Doe"
}

let ownerAddress = {
    address: "1313 Mockingbird Lane",
    city: "Chicago",
    state: "IL",
    postalCode: 11111
}



let pName = document.getElementById("name");
pName.innerText = pName.innerText + " " + cat.name;

let pBreed = document.getElementById("breed");
pBreed.innerText = pBreed.innerText + " " + cat.breed;

let pStatement = document.getElementById("statement");
pStatement.innerText = cat.meow();

cat.age = 4;

let pAge = document.getElementById("age");
pAge.innerText = pAge.innerText + " " + cat.age;

cat.color = "Gray";

let pColor = document.getElementById("color");
pColor.innerText = pColor.innerText + " " + cat.color;

//Just Don't
//delete cat.name;

cat.owner = owner;
cat.owner.address = ownerAddress

let pOwner = document.getElementById("ownerName");
pOwner.innerText = pOwner.innerText + " " + cat.owner.name;
console.log(cat);

console.log(cat.owner.address.state);

//Comparing Objects
let cup1 = { color: "blue", vol:12}
let cup2 = { color: "blue", vol:12}
let cup3 = cup2;

cup2.color= "red";



console.log(cup1 === cup2);
console.log(cup2 === cup3);

console.log(cup3.color);


//Convert Objects to JSON (Stringify)
let catJSON = JSON.stringify(cat);
console.log(catJSON);

let json = '{"name":"Fluffy","breed":"Tabby","age":4,"color":"Gray","owner":{"name":"John Doe","address":{"address":"1313 Mockingbird Lane","city":"Chicago","state":"IL","postalCode":11111}}}';
let cat2 = JSON.parse(json);
console.log(cat2.breed);