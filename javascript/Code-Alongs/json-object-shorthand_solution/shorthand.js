const firstName = "Rocco";
const lastName = "Orlando";
const status = "student";

const wrestler1 = {
    firstName: firstName,
    lastName: lastName,
    status: status
};

const wrestler2 = {
    firstName: "Larry",
    lastName: lastName,
    status: status
};

function compareWrestler(wrestler1, wrestler2){
    let returnValue = true;
    if (wrestler1.firstName != wrestler2.firstName){
        returnValue = false;
    }
    if (wrestler1.lastName != wrestler2.lastName){
        returnValue = false;
    }
     if (wrestler1.status != wrestler2.status){
        returnValue = false;
    }
    return returnValue;
}
console.log(wrestler1);
console.log(wrestler2);

console.log(compareWrestler(wrestler1, wrestler2));