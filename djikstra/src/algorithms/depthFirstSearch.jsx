// Performs either weightedAlgorithm or Breadth First Search algorithm;
// returns *all* nodes in the order
// in which they were visited. Also makes nodes point back to their
// previous node, effectively allowing us to compute the shortest path
// by backtracking from the finish node.
export function unweightedAlgorithm(grid, startNode, endNode, algorithm) {
  const visitedNodesInOrder = [];
  const stack = [];
  startNode.isVisisted = true;
  stack.push(startNode);
  let counter = 0;
  startNode.distance = 0;
  let node = null;
  while (!!stack.length) {
    if (algorithm === "depthFirstSearch") {
      node = stack.pop();
    } else if (algorithm === "breadthFirstSearch") {
      node = stack.shift();
    }
    if (!node.isVisisted) node.isVisisted = true;
    visitedNodesInOrder.push(node);

    if (node.isWall) {
      continue;
    }

    //end node is reached: return all nodes visited in order
    if (node === endNode) {
      return visitedNodesInOrder;
    }

    const unvisitedNeighbors = getNeighbors(node, grid);

    //push all unvisited neighbors onto stack, and set link to previous node
    for (const neighbor of unvisitedNeighbors) {
      if (!neighbor.isVisisted && !neighbor.isWall) {
        neighbor.previousNode = node;
        neighbor.distance = node.distance + 1;
        stack.push(neighbor);
      }
    }
  }
}

function getNeighbors(node, grid) {
  const neighbors = [];
  const { col, row } = node;
  if (row > 0) neighbors.push(grid[row - 1][col]);
  if (row < grid.length - 1) neighbors.push(grid[row + 1][col]);
  if (col > 0) neighbors.push(grid[row][col - 1]);
  if (col < grid[0].length - 1) neighbors.push(grid[row][col + 1]);
  return neighbors;
}

function sortNodesByDistance(unvisitedNodes) {
  unvisitedNodes.sort((nodeA, nodeB) => nodeA.distance - nodeB.distance);
}
