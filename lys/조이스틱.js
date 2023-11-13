function solution(name) {
    let totalMoves = 0; 
    const asciiA = 65; 
    const asciiZ = 90; 
    
    const getMove = (targetChar) => {
        const targetAscii = targetChar.charCodeAt();
        return Math.min(targetAscii - asciiA, asciiZ - targetAscii + 1);
    }

    const charMoves = [];
    for (let i = 0; i < name.length; i++) {
        const char = name[i];
        const move = getMove(char);
        charMoves.push(move);
    }
    
    let minTotalMove = charMoves.length - 1;
    for (let index = 0; index < charMoves.length; index++) {
        const move = charMoves[index];
        totalMoves += move;

        let nextIndex = index + 1;
        while (nextIndex < charMoves.length && charMoves[nextIndex] === 0) {
            nextIndex++;
        }

        minTotalMove = Math.min(minTotalMove, (index * 2) + charMoves.length - nextIndex, index + 2 * (charMoves.length - nextIndex));
    }

    return totalMoves + minTotalMove;
}
