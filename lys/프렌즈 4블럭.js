function solution(m, n, board) {
   let removedBlocks = [];
   const Board = [];
   let index = 0;
   let totalRemoved = 0;

   board.forEach((row) => Board.push(row.split("")));

   while (true) {
      for (let i = 0; i < m - 1; i++) {
         for (let j = 0; j < n; j++) {
            if (
               Board[i][j] !== '0' &&
               Board[i][j] === Board[i][j + 1] &&
               Board[i][j + 1] === Board[i + 1][j] &&
               Board[i + 1][j] === Board[i + 1][j + 1]
            ) {
               removedBlocks.push([i, j], [i, j + 1], [i + 1, j], [i + 1, j + 1]);
            }
         }
      }

      if (removedBlocks.length === 0) break;

      removedBlocks.forEach((block) => (Board[block[0]][block[1]] = '0'));
      removedBlocks = [];

      for (let i = 1; i < m; i++) {
         for (let j = 0; j < n; j++) {
            if (Board[i][j] === '0') {
               Board[i][j] = Board[i - 1][j];

               if (i - 2 >= 0 && Board[i - 2][j] !== '0') {
                  index = i;
                  while (index - 2 >= 0) {
                     Board[index - 1][j] = Board[index - 2][j];
                     Board[index - 2][j] = '0';
                     index--;
                  }
               } else {
                  Board[i - 1][j] = '0';
               }
            }
         }
      }
   }

   for (let i = 0; i < m; i++) {
      for (let j = 0; j < n; j++) {
         if (Board[i][j] === '0') {
            totalRemoved++;
         }
      }
   }
   return totalRemoved;
}
