import { useState } from 'react'

export default function List() {
  // const [fruits, setFruits] = useState(['Apple', 'Banana', 'Cherry'])
  const [fruits, setFruits] = useState([
    { name: '사과', key: 'Apple' },
    { name: '바나나', key: 'Banana' },
    { name: '체리', key: 'Cherry' }
  ])

  return (
    <ul>
      {fruits.map(fruit => {
        return <li key={fruit.key}>{fruit.name}</li>
      })}
    </ul>
  )
}
