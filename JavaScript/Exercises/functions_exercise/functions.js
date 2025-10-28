function greetUser(name) {
  console.log(`Hello, ${name}!`);
}
greetUser("Henok");

function squareNumber(n) {
  return n * n;
}
console.log(`Square of 4 is ${squareNumber(4)}`);
console.log(`Square of 7 is ${squareNumber(7)}`);

function addNumbers(a, b) {
  return a + b;
}
console.log("addNumbers(10, 5) =", addNumbers(10, 5));
console.log("addNumbers(3, 8) =", addNumbers(3, 8));

let colors = ["red", "blue", "green", "yellow", "purple", "orange"];
function getRandomColor() {
  let randomIndex = Math.floor(Math.random() * colors.length);
  return colors[randomIndex];
}
console.log("Random Color:", getRandomColor());
console.log("Random Color:", getRandomColor());
console.log("Random Color:", getRandomColor());

let fortunes = [
 "You will have a great day!",
 "A surprise is waiting for you.",
 "Something exciting is coming soon.",
 "Be cautious with your decisions today.",
 "Happiness is around the corner."
]; 
function tellFortune() {
  let randomIndex = Math.floor(Math.random() * fortunes.length);
  return fortunes[randomIndex];
}
console.log("Your Fortune:", tellFortune());
