let rndNum = Math.floor(Math.random() * 50) + 1;

console.log(rndNum);
if (rndNum % 2 == 0) {
    console.log("Even");
} else {
    console.log("Odd");
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

let userInput = prompt("Enter a number:");
userInput = parseInt(userInput);
for (let i = 1; i <=userInput; i++){
    console.log(i);
}
