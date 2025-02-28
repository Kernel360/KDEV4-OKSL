///// Component /////
export class Component {
  constructor(payload = {}) {
    // tagName = null 일 시 기본값 'div'
    const { tagName = 'div' } = payload
    this.el = document.createElement(tagName)
    this.render()
  }
  render() {
    
  }
}