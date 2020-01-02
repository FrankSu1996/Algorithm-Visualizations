import React, { Component } from "react";
import "./Node.css";

class Node extends Component {
  state = {};

  render() {
    const { isFinish, isStart, isVisited } = this.props;
    const extraClassName = isFinish
      ? "node-finish"
      : isStart
      ? "node-start"
      : isVisited
      ? "node-visited"
      : "";
    return <div className={`Node ${extraClassName}`}></div>;
  }
}

export default Node;

export const DEFAULT_NODE = {
  row: 0,
  col: 0
};
