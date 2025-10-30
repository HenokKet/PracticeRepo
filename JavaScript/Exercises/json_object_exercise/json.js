let car = {
    make: "Toyota",
    model: "Camry",
    year: 2020, 
    color: "White",
    drive: function() {
        return `The car is driving`;
    }
};

let driver= {
    name: "John Doe",
    licenseNumber: "D1234567",
    age: 30
}

car.color = "Blue";
car.fuelType = "Gasoline";
delete car.year;
car.driver = driver;
console.log(car.driver);
console.log(JSON.stringify(car));  

let carJSON = `{
    "make": "Honda",
    "model": "Civic",
    "year": 2019,
    "color": "Red",
    "fuelType": "electric"
}`;

let parsedCar = JSON.parse(carJSON);
console.log(parsedCar);