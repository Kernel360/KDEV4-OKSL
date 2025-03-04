import { useRef, useEffect } from 'react'

export default function TextField() {
  const inputRef = useRef<HTMLInputElement | null>(null)

  // () => {} - (콜백) return 요소가 render 되었을 때 실행
  // [] - 의존성 배열 -> 반응형 데이터의 값이 바뀔때 마다 콜백 함수가 실행됨
  useEffect(() => {
    // null이 아닐 시 .focus() 호출
    inputRef.current?.focus()
  }, [])
  return <input ref={inputRef} />
}

// const inputEl = document.querySelector('input')
// inputRef.current

// Component Life-Cycle
