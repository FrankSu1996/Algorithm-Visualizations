import React, { Component } from "react";
import Node from "./Node/Node";
import "./PathfindingVisualizer.css";
import { djikstra } from "../algorithms/djikstra";

const START_NODE_ROW = 10;
const START_NODE_COL = 15;
const FINISH_NODE_ROW = 10;
const FINISH_NODE_COL = 35;
const ANIMATE_DELAY_IN_MS = 1000;

class PathfindingVisualizer extends Component {
  state = {
    grid: []
  };

  //set up grid for graph
  componentDidMount() {
    const grid = getInitialGrid();
    this.setState({ grid });
  }

  animateDjikstra(visitedNodesInOrder) {
    console.log(visitedNodesInOrder.length);
    for (let i = 0; i < visitedNodesInOrder.length; i++) {
      setTimeout(() => {
        console.log(i);
      }, 500);
    }
  }

  visualizeDjikstra() {
    const { grid } = this.state;
    const startNode = grid[START_NODE_ROW][START_NODE_COL];
    const endNode = grid[FINISH_NODE_ROW][FINISH_NODE_COL];
    const visitedNodesInOrder = djikstra(grid, startNode, endNode);
    this.animateDjikstra(visitedNodesInOrder);
  }

  render() {
    const { grid } = this.state;
    console.log(grid);

    return (
      <React.Fragment>
        <button onClick={() => this.visualizeDjikstra()}>
          Visualize Djikstra's Algorithm
        </button>
        <div className="Grid">
          {grid.map((row, rowIdx) => {
            return (
              <div key={rowIdx}>
                {row.map((node, nodeIdx) => {
                  const { isStart, isFinish, isVisited } = node;
                  return (
                    <Node
                      key={nodeIdx}
                      isStart={isStart}
                      isFinish={isFinish}
                      isVisited={isVisited}
                    ></Node>
                  );
                })}
              </div>
            );
          })}
        </div>
      </React.Fragment>
    );
  }
}

const getInitialGrid = () => {
  const grid = [];
  for (let row = 0; row < 20; row++) {
    const currentRow = [];
    for (let col = 0; col < 50; col++) {
      currentRow.push(createNode(col, row));
    }
    grid.push(currentRow);
  }
  return grid;
};

const createNode = (col, row) => {
  return {
    col,
    row,
    isStart: row === START_NODE_ROW && col === START_NODE_COL,
    isFinish: row === FINISH_NODE_ROW && col === FINISH_NODE_COL,
    distance: Infinity,
    isVisited: false,
    isWall: false,
    previousNode: null
  };
};

export default PathfindingVisualizer;
