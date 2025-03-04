import { Component } from '../core/heropy'
import Headline from '../components/Headline'

export default class Home extends Component {
  render() {
    const headline = new Headline().el;
    this.el.classList.add('container') // 'container 이름의 div 생성
    this.el.append(headline)
  }
}