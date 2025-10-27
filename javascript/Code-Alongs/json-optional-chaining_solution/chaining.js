const person = {
    name: "Rocco",
    age: 13,
    contact: {
        email: "rocco@gmail.com",
        phone: "111-222-3456",
        twitter: "roccoDM"
    }
};


const person2 = {
    name: "Preston",
    age: 12
};



if (person.contact.twitter) {
    console.log(person.contact.twitter);
} {
    console.log("No Twitter Handle");
}

if (person2.contact && person2.contact.twitter) {
    console.log(person2.contact.twitter);
} else if (person2.contact) {
    console.log("No twitter data.");
} else {
    console.log("No Contact Info.");
}

console.log(person.contact.twitter ? 
                person.contact.twitter : 
                "No Twitter Account");

console.log(person2.contact && person2.contact.twitter ? 
                person2.contact.twitter : 
                "No Twitter Account");

console.log(person.contact.twitter);

//Object Arrays

const users = [
    {name: "Curly", profile: {website: "curly@stooges.com"}},
    {name: "Larry"},
    {name: "Curly", profile: {website: "moe@stooges.com"}}
];

users.forEach(user => {
    console.log(user.profile?.website || "No Website Avaialable");
});

