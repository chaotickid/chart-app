import { Button } from '@/components/ui/button'
import Link from 'next/link'
import React from 'react'

const NavBar = () => {
  return (
    <div className='p-8 flex items-center justify-between shadow-xl rounded-2xl  bg-gradient-to-r from-purple-500 to-pink-500 text-white px-6 py-2  transition duration-300 mt-2'>
        <div>Logo</div>
        <div className='flex items-center gap-5'>
            <Link href={"/add"}><h1 className='cursor-pointer'>Add</h1></Link>
            <Link href={"/"}><h1 className='cursor-pointer'>Charts</h1></Link>
        </div>
        <Button className={"transform hover:scale-110 transition duration-300"}>Get Started</Button>
    </div>
  )
}

export default NavBar