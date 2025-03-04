import { useState } from 'react'
import List from '@/components/List.tsx'
import TextField from './components/TextField'

// <> </> Fragment (묶는 용도로 사용 > 렌더링은 되지 않음)
// 하나의 컴포넌트에서는 단일 요소만 리턴해야함
// <div>나 다른 태그로 감쌀 필요가 없을 경우 Fragment로 묶음
// count => console에서 변경은 되지만 화면에 변경이 안됨 -> 반응성이 없음 -> useState 사용
export default function App() {
  // let count = 0

  // const countState = useState(0);
  // const count = countState[0];
  // const setCount = countState[1];

  // 배열 구조 할당
  const [count, setCount] = useState(0)

  return (
    <>
      <h1
        className={`text-4xl font-bold ${count > 7 ? 'active' : ''}`}
        // style={{
        // 문자열 안됨. 객체타입
        // backgroundColor: 'red'
        // }}
        onClick={() => {
          // count += 1

          // 화면의 값과 console의 값이 다를 수 있음
          // setCount(count + 1)
          // console.log(count)

          const _count = count + 1
          setCount(_count)
          console.log(_count)
        }}>
        App.tsx({count})
      </h1>
      {/* 조건부 렌더링 */}
      {count > 0 && <button onClick={() => setCount(0)}>초기화</button>}
      <List />
      <TextField />
    </>
  )
}
