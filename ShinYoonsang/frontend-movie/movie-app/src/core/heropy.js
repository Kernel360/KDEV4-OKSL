///// Component /////
export class Component {
  constructor(payload = {}) {
    // tagName = null 일 시 기본값 'div'
    const { tagName = 'div',
       state = {},
       props = {},
      } = payload
    this.el = document.createElement(tagName) //컴포넌트 최상위 요소
    this.state = state // 컴포넌트 안에서 사용할 데이터
    this.props = props // 컴포넌트가 사용될 때 부모 컴포넌트에서 받는 데이터
    this.render()
  }
  render() { // 컴포넌트 렌더링
  }
}
