import Link from 'next/link'
import React from 'react'

const NavBar = () => {
  return (
    <div className='bg-red-300 p-5 flex items-center justify-between'>
        <div>Logo</div>
        <div className='flex items-center gap-5'>
            <Link href={"/add"}><h1>Add</h1></Link>
            <Link href={"/"}><h1>Charts</h1></Link>
        </div>
        <div>Get Started</div>
    </div>
  )
}

export default NavBar