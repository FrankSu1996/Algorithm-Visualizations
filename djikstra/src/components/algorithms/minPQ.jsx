class minPQ {
  constructor(nodeArray) {
    this.maxSize = nodeArray.length;
    this.pq = [];
    //first value in pq is useless
    this.pq.push("NULL FIRST VALUE");
    //build priority queue
    for (let i = 0; i < this.maxSize; i++) {
      this.pq.push(nodeArray[i]);
    }
    for (let k = Math.floor(this.maxSize / 2); k >= 1; k--) {
      sink(k);
    }
  }

  sink(index) {
    while (2 * index <= this.maxSize) {
      let j = 2 * index;
      if (j < this.maxSize && greater(j, j + 1)) j++;
      if (!greater(index, j)) break;
      exchange(index, j);
      index = j;
    }
  }

  greater(i, j) {
    if (this.pq[i].distance > this.pq[j].distance) return true;
    else return false;
  }

  exchange(i, j) {
    const node = pq[i];
    pq[i] = pq[j];
    pq[j] = node;
  }
}
