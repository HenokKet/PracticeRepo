let ralph = "Hello World!";
let length = ralph.length;
let char = ralph.charAt(0);
let neverDeclaredVariable;

console.log(ralph);
console.log(length);
console.log(char);

console.log(ralph/length);

console.log(neverDeclaredVariable);

let s1 = "Hello";
let s2 = 'Hello';

console.log(s1 + s2);

let firstChar = s1.charAt(0);
let lastChar = s2.charAt(s2.length - 1);
let strLength = s2.length;

console.log(s1, firstChar, lastChar, strLength);

let quoteExample = "He Said, \"Hello World\"";
console.log(quoteExample);
quoteExample = 'He Said, "Hello World"';
console.log(quoteExample);

let position = quoteExample.indexOf("o");
console.log(position);

console.log(ralph.substring(1, 4));

console.log(ralph.toUpperCase());
console.log(ralph.toLowerCase());

let sentenct = "The sky is blue blue";
console.log(sentenct.replace(sentenct.replace("blue", "clear"), "blue", "clear"));


console.log(Math.PI);
console.log(Math.pow(2,4));
console.log(Math.sqrt(16));

let x = 5;
let y = 6;
console.log(x + y);
console.log(x - y);
console.log(x * y);
console.log(x / y);

console.log(Math.round(2.6));
console.log(Math.round(2.4));
console.log(Math.floor(2.9));
console.log(Math.ceil(2.1));
console.log(Math.trunc(2.999999));

console.log(Math.floor(Math.random()  * 10) +1);

let a = 5;
let b = a;
a=10;
console.log(b);

let obj1 = {name: "Alice"};
let obj2 = obj1;
obj1.name="Bob";
console.log(obj2.name);