greeting();
personalGreeting("Anthony");
let area = areaCircle(4);
console.log(area);
console.log(fullName("Anthony", "Orlando"));

function greeting(){
    alert("Hello World");
}


function personalGreeting(name){
    if (name === null){
        let name = prompt("Enter Your Name Here:");
    }
    
    alert(`Hello ${name}`);
}

function areaCircle(radius){
    return Math.floor(Math.PI * Math.pow(radius, 2));
}


function fullName(firstname, lastname){
    return `${firstname} ${lastname}`;
}
