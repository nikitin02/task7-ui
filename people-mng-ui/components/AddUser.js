import { Dialog, Transition } from "@headlessui/react";
import React, { Fragment, useState } from "react";
import PersonList from './PersonList';


const AddUser = () => {
    const USER_BASE_URL = "http://localhost:8888/people";
    const [isOpen, setIsOpen] = useState(false);
    const [user, setUser] = useState({
            id: '',
            first_name: '',
            last_name: '',
            password: '',
            phone: '',
            email: '',
            location: '',
    });

    const [responseUser, setResponseUser] = useState({
        id: '',
        first_name: '',
        last_name: '',
        password: '',
        phone: '',
        email: '',
        location: '',
});
function closeModal() {
    setIsOpen(false);
  }

  function openModal() {
    setIsOpen(true);
  }

  const handleChange = (event) => {
    const value = event.target.value;
    setUser({ ...user, [event.target.name]: value });
  };

  const saveUser = async (e) => {
    e.preventDefault();
    const response = await fetch(USER_BASE_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(user),
    });
    if (!response.ok) {
      throw new Error("Something went wrong");
    }
    const _user = await response.json();
    setResponseUser(_user);
    reset(e);
  };

    const reset = (e) => {
        e.preventDefault()
        setUser({
            id: '',
            first_name: '',
            last_name: '',
            password: '',
            phone: '',
            email: '',
            location: ''
        })
        setIsOpen(false)
    };



  return (
    <>
    <div className="container mx-auto my-8">
        <div className="h-12 px-8">
            <button onClick={openModal}
             className="rounded bg-teal-700 text-white px-6 py-2 font-semibold">
                Add Person
                 </button>
        </div>
    </div>
    <Transition appear show = {isOpen} as = {Fragment}>
        <Dialog as="div" 
        className="fixed inset-0 z-10 overflow-auto"
        onClose={closeModal}>
            <div className="min-h-screen px-4 text-center">
                <Transition.Child
                  enter="ease-out duration-300"
                  enterFrom="opacity-0 scale-95"
                  enterTo="opacity-100 scale-100"
                  leave="ease-in duration-200"
                  leaveFrom="opacity-100 scale-100"
                  leaveTo="opacity-0 scale-95">
                    <div className='inline-block w-full max-w-md p-6 my-8 overflow-hidden text-left align-middle transition-all transform bg-white shadow-xl rounded-md'>
                    <Dialog.Title 
                    as='h3' 
                    className="text-lg font-medium leading-6
                     text-gray-900">
                        Add new Person
                        </Dialog.Title>
                    <div className='flex max-w-md max-auto'>
                        <div className='py-2'>
                            <div className='h-14 my-4'>
                                <label className='block text-gray-600 text-sm font-normal'>
                                    First name
                                </label>
                                <input 
                                type="text" 
                                name="first_name" 
                                value={user.first_name}
                                onChange={(e) => handleChange(e)}
                                className='h-10 w-96 border mt-2 px-2 py-2'></input>
                            </div>
                            <div className='h-14 my-4'>
                                <label className='block text-gray-600 text-sm font-normal'>
                                    Last name
                                </label>
                                <input type="text" 
                                name="last_name" 
                                value={user.last_name}
                                onChange={(e) => handleChange(e)}
                                className='h-10 w-96 border mt-2 px-2 py-2'></input>
                            </div>
                            <div className='h-14 my-4'>
                                <label className='block text-gray-600 text-sm font-normal'>
                                    Password
                                </label>
                                <input type="text" 
                                name="password" 
                                value={user.password}
                                onChange={(e) => handleChange(e)}
                                className='h-10 w-96 border mt-2 px-2 py-2'></input>
                            </div>
                            <div className='h-14 my-4'>
                                <label className='block text-gray-600 text-sm font-normal'>
                                    Phone 
                                </label>
                                <input type="text" 
                                name="phone" 
                                value={user.phone}
                                onChange={(e) => handleChange(e)}
                                className='h-10 w-96 border mt-2 px-2 py-2'></input>
                            </div>
                            <div className='h-14 my-4'>
                                <label className='block text-gray-600 text-sm font-normal'>
                                    Email
                                </label>
                                <input type="text" 
                                name="email" 
                                value={user.email}
                                onChange={(e) => handleChange(e)}
                                className='h-10 w-96 border mt-2 px-2 py-2'></input>
                            </div>
                            <div className='h-14 my-4'>
                                <label className='block text-gray-600 text-sm font-normal'>
                                    Location
                                </label>
                                <input type="text" 
                                name="location" 
                                value={user.location}
                                onChange={(e) => handleChange(e)}
                                className='h-10 w-96 border mt-2 px-2 py-2'></input>
                            </div>
                            <div className='h-14 my-4 space-x-4 pt-4'>
                                <button
                                 onClick={saveUser} className='rounded text-white font-semibold bg-green-400 hover:bg-green-600 py-2 px-6'>
                                 Save
                                 </button>
                                 <button
                                onClick={reset} className='rounded text-white font-semibold bg-red-400 hover:bg-red-600 py-2 px-6'>
                                    Close
                                </button>
                            </div>
                        
                        </div>
                    </div>
                    </div> 
                </Transition.Child>
            </div>
        </Dialog>
    </Transition>
    <PersonList person={responseUser}/>
    </>);
};

export default AddUser;