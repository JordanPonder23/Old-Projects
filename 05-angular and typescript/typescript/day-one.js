/*What is node.js?
    -Node.js is an open source server environment
    -Javascript on a server
    -Node is free and runs on various platforms
        (windows, linux, unix, mac, etc)
    
What is NPM?
    -Node Package Manager
    -NPM is a platform and command line interface
    -it can install and uninstall packages and their
        dependent packages.

What is typescript?
    -typescript is a superset of javascript
        (developed by Microsoft). This means that anything
        you can do in javascript you can do in typescript; typescript
        adds additional features and syntax.
    -typescript was developed for larger apps. The syntax is closer
        to higher level languages like java or c#.
    -TS is strongly typed, easier to debug, it also adds access
        modifiers and encapsulation. You can even see compilation
        errors.

    to transpile the TS into JS we use
        "tsc *ourfile*.ts -w"
*/
console.log('Welcome to typescript!');
/*What are the datatypes in typescript?
    object, number, string, boolean, undefined, null, void,
    function, array, enum, any, tuple, symbol
*/
//look what happens when you try to change the typing
let s1 = "hello";
//s1= 5;
//let s2;   //notice how this is of type any
let s2;
s2 = "hello darkness my old friend";
//s2= 15;  //when we have type "string" there will be an issue here
//console.log(s1);
//numbers
let n1;
let n2 = 5 + 9;
//boolean
let b1;
//any
let a1;
a1 = 'string';
a1 = 17;
a1 = false;
//void
let v1 = null;
let nul = undefined;
let un1 = null;
//arrays
let arry1;
arry1 = ['one', 'two', 'three']; //try to change one element to a number
let arry2;
arry2 = [2, 17, 19, 900];
/////WHAT IS AN ENUM????????????//////////
//An enumeration is a collection of constants. Representative values.
/*THIS is how we could create an "enum" type of effect in JS
 const OFFENSE= 0;
const DEFENSE= 1;
const SUPPORT= 2;
const OTHER= 3;

let myVar= DEFENSE;
if(myVar == SUPPORT)    {}
else if(myVar==DEFENSE)     { console.log("enum!"); } */
//LOOK AT ME! IM MR MEESEEKS!
var abilityType;
(function (abilityType) {
    abilityType[abilityType["OFFENSE"] = 0] = "OFFENSE";
    abilityType[abilityType["ARMOR"] = 9] = "ARMOR";
    abilityType[abilityType["DEFENSE"] = 1] = "DEFENSE";
    abilityType[abilityType["SUPPORT"] = 2] = "SUPPORT";
    abilityType[abilityType["OTHER"] = 3] = "OTHER";
})(abilityType || (abilityType = {}));
;
let myVar = abilityType.DEFENSE;
//if(myVar == abilityType.DEFENSE)    { console.log('sugoi!'); }
//tuple
//fixed size and datatype array
let tul;
tul = ["hola", true, false];
//tul= ["true", 77, false];
/////FUNCTIONS!!!!!!!
/* THIS IS THE OLD WAY, it still applies
function myFunc(first, second){
    console.log("stuff");
    return "otherStuff";
}
myFunc(5,7); */
function myFunc(first, second) {
    return 'JackJack';
}
function myOtherFunc() {
    //return 5;
    return;
}
/* function myFunc3(obj: Criminal){
    //our logic
    console.log(obj.minions);
}

let daCriminal: Criminal = {name: 'Al Capone', record: true,
                                minions: 12}
myFunc3(daCriminal); */
//////CLASSES
class SuperVillain {
    constructor(name, ability, bounty) {
        this.name = name;
        this.ability = ability;
        this.bounty = bounty;
    }
    useAbility() {
        console.log(this.ability);
    }
}
//let pepper = new SuperVillain();
let pepper = new SuperVillain('Pepper', 'drowsy fist', 111000);
//pepper.ability= 'nightmare plane';
//pepper.useAbility();
//////
class SpecialClass extends SuperVillain {
    constructor(title, name, ability, bounty) {
        super(name, ability, bounty);
        this.title = title;
    }
    useAbility() {
        console.log('in the special classification');
        super.useAbility();
    }
}
let LaKeyera = new SpecialClass('Underboss', 'Lala', 'super petty', 1);
;
;
//multiple implements?
class Pet {
    /* private _name: string;
    private age: number;
    private breed: string; */
    /*question marks make the parameter optional:
        each parameter to the right of the question mark must
        ALSO be optional.

        giving an access modifier in the constructor's parameters
            declares the field FOR YOU and then sets the field
            equal to the argument

        we use an underscore before the name of a field that has
            the get and set keyword counterparts (this is convention)
    */
    constructor(_name, age, breed) {
        this._name = _name;
        this.age = age;
        this.breed = breed;
        /* this._name=_name;
        this.age=age;
        this.breed=breed; */
    }
    //we can't overload the constructor? 
    get name() {
        console.log('getter');
        return this._name;
    }
    set name(_name) {
        console.log('setter');
        this._name = _name;
    }
}
/* let pupper = new Pet('Rocket', 12, 'French Bulldog'); */
let pupper = new Pet('Rocket', 12);
//console.log(pupper.name); //variable is private, so this is an issue
//     we'd need a public getter and setter to access (encapsulation)
/* this is for a normal getter and setter
pupper.setName('Teddi');
console.log(pupper.getName()); */
//pupper.name = "Teddi";
//console.log(pupper.name);
///////////arrow notation
let exampleFunc = (vari) => console.log('vari is working');
exampleFunc("stuff");
///////
/* class Quiz{
    qNum: number;
    points: number;
} */
function printQuiz(q1) {
    console.log(q1.points, q1.qNum);
}
let q1 = { qNum: 25, points: 100 };
printQuiz(q1);
/*we can still use a Quiz from another file
    IF we import that class
    'import {Quiz} from "./quiz";'


    tsc -t es2015 filename.ts -w
    OR
    tsc -t es6 filename.ts -w
*/
/*
    I WOULD LIKE YOU TO:
        Create a parent class (eg Animal)
            -in a different typescript file than THIS file
        Create a child class that extends that class (eg monkey)
            -in a different typescript file than parent and THIS file
            -should have a constructor with optional parameters
            -should have at least two sets of "get" methods and
                "set" methods
            -you should have at least 3-4 fields in this class
                that are declared FROM the constructor itself
            -you'll need to import the parent class from the other
                file.
            
*/
