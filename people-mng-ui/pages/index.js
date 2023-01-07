import Head from 'next/head'
import Navbar from '../components/Navbar'
import AddUser from '../components/AddUser'



export default function Home() {
  return (
    <div>
      <Head>
        <title>People managment</title>
      </Head>
      <Navbar />

      <main>
        <AddUser />
      </main>
    </div>
  )
}
