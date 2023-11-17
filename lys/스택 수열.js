
//const input = fs.readFileSync("/dev/stdin").toString().trim().split('\n').map(Number);
const fs = require('fs');
const input = fs.readFileSync('lys/input.txt', 'utf-8').trim().split('\n').map(Number);
const n = input.shift();
const answerStack = [];
const stack = [];
let num = 1;

for (let i = 0; i < n; i++) {
    const targetNum = input[i];

    while (num <= targetNum) {
        stack.push(num);
        num++;
        answerStack.push("+");
    }

    const poppedNum = stack.pop();
    answerStack.push("-");

    if (poppedNum !== targetNum) {
        answerStack.length = 0;
        answerStack.push("NO");
        break;
    }
}

console.log(answerStack.join("\n"));
