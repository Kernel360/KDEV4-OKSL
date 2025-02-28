///// Component /////
export class Component {
  constructor(payload = {}) {
    // tagName = null 일 시 기본값 'div'
    const { tagName = 'div',
       state = {},
       props = {},
      } = payload
    this.el = document.createElement(tagName)
    this.state = state
    this.props = props
    this.render()
  }
  render() {
  }
}