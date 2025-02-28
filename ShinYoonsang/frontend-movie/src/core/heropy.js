

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


// Router //
function routeRender(routes) {

  // URL에 해시가 없으면 해시 추가
  if (!location.hash) {
    // 히스토리 남기지 않고 페이지 이동
    history.replaceState(null, '', '/#/')
  }
  const routerView = document.querySelector('router-view')
  // http://localhost:1234/#/about
  // #about
  const [hash, queryString = ''] = location.hash.split('?')

  // a=123&b=456
  // ['a=123', 'b=456']
  const query = queryString
  .split('&')
  .reduce((acc, cur) => {
    const [key, value] = cur.split('=')
    // {a :'123', b:'456'}
    acc[key] = value
    return acc
  }, {})

  history.replaceState(query, '', '');

  const currentRoute = routes.find(route => new RegExp(`${route.path}/?$`).test(hash))
  routerView.innerHTML = ''
  routerView.append(new currentRoute.Component().el)

  window.scrollTo(0, 0)
}
export function createRouter(routes) {
  return function() {
    window.addEventListener('popstate', () => {
      routeRender(routes)
    })
    routeRender(routes)
  }
}