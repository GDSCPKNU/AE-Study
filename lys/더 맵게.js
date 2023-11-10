class MinHeap {
    constructor() {
        this.heap = [null]; 
    }

    size() {
        return this.heap.length - 1;
    }

    getMin() {
        return this.heap[1] ? this.heap[1] : null;
    }

    swap(a, b) {
        [this.heap[a], this.heap[b]] = [this.heap[b], this.heap[a]];
    }

    push(value) {
        this.heap.push(value);
        let curIdx = this.heap.length - 1;
        let parIdx = (curIdx / 2) >> 0;

        // 부모 노드가 현재 노드보다 큰 지 확인
        while(curIdx > 1 && this.heap[parIdx] > this.heap[curIdx]) {
            this.swap(parIdx, curIdx);
            curIdx = parIdx;
            parIdx = (curIdx / 2) >> 0;
        }
    }

    pop() {
        const minValue = this.heap[1];

        // 배열의 마지막 원소를 root 위치에 배치
        if (this.heap.length <= 2) {
            this.heap = [null]; // Root 노드 하나만 있는 경우
        } else {
            this.heap[1] = this.heap.pop();
        }

        let currentIndex = 1;
        let leftIndex = currentIndex * 2;
        let rightIndex = leftIndex + 1;

        if (!this.heap[leftIndex]) {
            return minValue; // 자식이 없으면 바로 반환
        }

        if (!this.heap[rightIndex]) { // 왼쪽 자식만 있는 경우
            if (this.heap[leftIndex] < this.heap[currentIndex]) {
                this.swap(leftIndex, currentIndex);
            }
            return minValue;
        }

        // 왼쪽 & 오른쪽 자식이 모두 있는 경우
        while (this.heap[leftIndex] < this.heap[currentIndex] || this.heap[rightIndex] < this.heap[currentIndex]) {
            const minIndex = this.heap[leftIndex] > this.heap[rightIndex] ? rightIndex : leftIndex;
            this.swap(minIndex, currentIndex);
            currentIndex = minIndex;
            leftIndex = currentIndex * 2;
            rightIndex = leftIndex + 1;
        }
        return minValue;
    }
}

function solution(scoville, K) {
    const newScoville = new MinHeap();

    for (let i = 0; i < scoville.length; i++) {
        newScoville.push(scoville[i]);
    }

    let count = 0;
    while (newScoville.getMin() < K) {
        if (newScoville.size() === 1) {
            return -1;
        }
        const newFood = newScoville.pop() + newScoville.pop() * 2;
        newScoville.push(newFood);
        count++;
    }

    return count;
}
