// export default class App {
//   constructor() {
//     this.el = document.createElement('div')
//     this.el.textContent = 'Hello, world!'
//   }
// }

import { Component } from "./core/heropy";

export default class App extends Component {
  // Constructor() super() 일 시 생략 가능
  constructor() {
    super();
  }
  render() {
    this.el.textContent = 'Hello, world!'
  }
}