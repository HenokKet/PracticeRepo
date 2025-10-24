let rndNum = Math.floor(Math.random() * 20) + 1;

console.log(rndNum);
if (rndNum % 2 == 0) {
    console.log("Even");
} else {
    console.log("Odd");
}

let number = 5;
let string = "5";

console.log(number == string);
console.log(number === string);

let num = Math.floor(Math.random() * 100) +1;

if (num <= 30){
    console.log(`${num} is a small number.`);
} else if (num <= 70){
    console.log(num + " is a medium number");
} else {
    console.log(`${num} is a large number.`);
}


let dayNumber = new Date().getDay();
let day;

switch(dayNumber){
    case 0: day = "Sunday"; 
        break;
    case 1: day = "Monday";
        break;
    case 2: day = "Tuesday"; 
        break;
    case 3: day = "Wednesday";
        break;
    case 4: day = "Thursday"; 
        break;
    case 5: day = "Friday";
        break;
    case 6: day = "Saturday"
        break;
    default: day = "Unknown";
}
 
console.log(dayNumber);
console.log(day);

let roll = 0;

while (roll !== 6){
    roll = Math.floor(Math.random() * 6) + 1;
    console.log(`You rolled a ${roll}`);
}
console.log("You rolled a six");

for (let i = 1; i <=10; i++){
    console.log(i);
}

let word = "JavaScript";

for (let i =0; i < word.length; i++) {
    console.log(`Character at index ${i}: ${word.charAt(i)}`);
}

let str = "CodeAlong";

for (let i = str.length-1; i>=0; i--){
    console.log(str.charAt(i));
}