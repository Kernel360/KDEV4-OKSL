// import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './main.css'
import App from './App'

createRoot(document.getElementById('root')!).render(
  <>
     {/* 최상위 컴포넌트 */}
    <App />

  </>
)
