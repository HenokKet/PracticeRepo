let fruits = ["apple","banana", "cherry", "date"];
console.log("Fruits Array:", fruits);

console.log(`First Fruit: ${fruits[0]}`);
console.log(`Last Fruit: ${fruits[fruits.length -1]}`);

fruits[1] = "blueberry";
console.log("After Modification:", fruits);

fruits.push("elderberry");
console.log("After Adding to End:", fruits);

fruits.shift("apricot");
console.log("After Adding to Start:", fruits);

fruits.shift();
console.log("After Removing from Start:", fruits);  

fruits.pop();
console.log("After Removing from End:", fruits);

for (let i = 0; i < fruits.length; i++){
    console.log(`Index: ${i}, Fruit: ${fruits[i]}`);
}

for(let i =0; i < fruits.length; i+2){
    console.log(`Every Second Fruit: ${fruits[i]}`);
}

fruits.indexOf("cherry");
console.log("Index of 'cherry':", fruits.indexOf("cherry"));

fruits.splice(2, 1);
console.log("After Removing 'cherry':", fruits);

fruits.concat(["fig", "grape"]);
console.log("After Concatenation:", fruits.concat(["fig", "grape"]));
