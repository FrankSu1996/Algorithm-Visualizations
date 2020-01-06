// Performs either DepthFirstSearch or Breadth First Search algorithm;
// returns *all* nodes in the order
// in which they were visited. Also makes nodes point back to their
// previous node, effectively allowing us to compute the shortest path
// by backtracking from the finish node.
export function depthFirstSearch(grid, startNode, endNode, algorithm) {
  const visitedNodesInOrder = [];
  const stack = [];
  stack.push(startNode);
  let counter = 0;
  console.log(algorithm);
  startNode.distance = 0;
  let node = null;

  while (!!stack.length) {
    if (algorithm === "depthFirstSearch") {
      node = stack.pop();
    } else if (algorithm === "breadthFirstSearch") {
      node = stack.shift();
    }

    node.isVisisted = true;
    visitedNodesInOrder.push(node);
    console.log(visitedNodesInOrder[counter].previousNode);
    counter++;
    if (node.isWall) {
      continue;
    }

    // If the closest node is at a distance of infinity,
    // we must be trapped and should therefore stop.
    if (node.distance === Infinity) return visitedNodesInOrder;

    //end node is reached: return all nodes visited in order
    if (node === endNode) {
      console.log(visitedNodesInOrder);
      return visitedNodesInOrder;
    }

    const unvisitedNeighbors = getUnvisitedNeighbors(node, grid);

    //push all unvisited neighbors onto stack, and set link to previous node
    for (const neighbor of unvisitedNeighbors) {
      if (!neighbor.isVisisted) {
        neighbor.previousNode = node;
        stack.push(neighbor);
      }
      updateUnvisitedNeighbors(node, grid);
    }
  }
}

function updateUnvisitedNeighbors(node, grid) {
  const unvisitedNeighbors = getUnvisitedNeighbors(node, grid);
  for (const neighbor of unvisitedNeighbors) {
    neighbor.distance = node.distance + 1;
    neighbor.previousNode = node;
  }
}

function getUnvisitedNeighbors(node, grid) {
  const neighbors = [];
  const { col, row } = node;
  if (row > 0) neighbors.push(grid[row - 1][col]);
  if (row < grid.length - 1) neighbors.push(grid[row + 1][col]);
  if (col > 0) neighbors.push(grid[row][col - 1]);
  if (col < grid[0].length - 1) neighbors.push(grid[row][col + 1]);
  return neighbors.filter(neighbor => !neighbor.isVisited);
}
