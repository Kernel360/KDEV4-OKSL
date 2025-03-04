import Layout from "./components/Layout"
import Title from "./components/Title"
import Controls from "./components/Controls"
import TodoList from "./components/TodoList"
function App() {
  return <div>
    <Layout>
      <Title />
      Hello World
      <Controls/>
      <TodoList/>
    </Layout>
    {/* <Layout>
      <Title>
        <Controls>
          <TodoList></TodoList>
        </Controls>
      </Title>
    </Layout> */}
  </div>
}

export default App
