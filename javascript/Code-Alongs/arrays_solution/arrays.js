let nums = [5, 10, 15, 20];

let numArray = new Array(5, 10, 15, 20);

console.log(nums);
console.log(numArray);

let colors = ["red", "green", "blue"];
console.log("Colors Array:", colors);

let mixed = ['green', 14, "Blue", 73, false];
console.log(mixed);

console.log(`First Element: ${mixed[0]}`);
console.log(`Last Element: ${mixed[4]}`);

let index = mixed.indexOf(73);
console.log(index);

let numbers = Array.from(mixed);
numbers[0] = 1;
numbers[2] = 2;
numbers[4] = 3;

numbers.unshift(12);
numbers.push(100);
console.log(numbers);

let last = numbers.pop();
console.log(`Removed: ${last}`);
console.log(numbers);

let removed = numbers.splice(1,3);
console.log(`Removed ELements: `, removed);
console.log(numbers);

let newNumbers = [3,11,16];
let combinedNumbers = numbers.concat(newNumbers);
console.log(combinedNumbers);

for (let i = 0; i< combinedNumbers.length; i++){
    console.log(`Index: ${i}, value ${combinedNumbers[i]}`);
}

combinedNumbers.forEach((num, index)=> {
    console.log(`Index: ${index}, Value: ${num}`);
});

