const book = {
 title: "JavaScript Basics",
 author: {
 name: "John Doe",
 social: {
 twitter: "@johndoe"
 }
 }
};
// Use optional chaining to access the Twitter handle
console.log(book.author?.social?.twitter);
// Try accessing a non-existent property using optional
console.log(book.publisher?.name); 

//1. undefined
//2. it prevents runtime errors when accessing nested properties that may not exist
//3. It would return an error instead of undefined