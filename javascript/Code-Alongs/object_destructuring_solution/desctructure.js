const me = {
    name : "Anthony",
    age: 50,
    stats: {
        mileTime: "20:00",
        maxBench: 100
    },
    isLying: true
};


function initializeForm() {
    const name = document.getElementById("name");
    const age = document.getElementById("age");
    const mile = document.getElementById("mile");
    const bench = document.getElementById("bench");
    const lying = document.getElementById("lying");

    name.value = me.name;
    age.value = me.age;
    mile.value = me.stats.mileTime;
    bench.value = me.stats.maxBench;
    lying.checked = me.isLying;

}

function updateMe() {
    const name = document.getElementById("name");
    const age = document.getElementById("age");
    const mile = document.getElementById("mile");
    const bench = document.getElementById("bench");
    const lying = document.getElementById("lying");

    me.name = name.value;
    me.age = age.value;
    me.stats.mileTime = mile.value;
    me.stats.maxBench = bench.value;
    me.isLying = lying.checked;

    console.log(me);
}


document.addEventListener('DOMContentLoaded', function() {
    initializeForm();
}); 

document.getElementById("formSubmit").addEventListener('click', function(event) {
    event.preventDefault();
    updateMe();
});

// console.log(me.name, me.age, me.stats.mileTime);

//  const { name, age } = me;

//  console.log(name, age )

// //const {stats: {mileTime, maxBench}} = me;

// //console.log(mileTime, maxBench);

// let { mileTime, maxBench} = me.stats;

// maxBench = 120;
// console.log(mileTime, maxBench);

// console.log(me.stats.maxBench);

// const {name: handle, age: yearsOnEarth } = me;

// console.log(handle, yearsOnEarth);

