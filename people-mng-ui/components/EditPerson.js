import { Dialog, Transition } from "@headlessui/react";
import { React, useState, useEffect, Fragment } from "react";

const EditPerson = ({personId, setResponsePerson}) => {

    const USER_BASE_URL = "http://localhost:8888/people";
    const [isOpen, setIsOpen] = useState(false);
    const [person, setPerson] = useState({
            id: '',
            first_name: '',
            last_name: '',
            password: '',
            phone: '',
            email: '',
            location: '',
    });

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await fetch(USER_BASE_URL +'/' + personId, {
                    method: "GET",
                    headers: {
                        "Content-Type": "application/json",
                    },
                })
                const _person = await response.json();
                setPerson(_person);
                setIsOpen(true)
            } catch (error) {
                console.log(error)
            }
        };    
            if(personId) {
                fetchData()
            }
    }, [personId] )
    

    function closeModal() {
        setIsOpen(false);
    }

    function openModal() {
        setIsOpen(true);
    }

    const handleChange = (event) => {
        const value = event.target.value;
        setPerson({ ...person, [event.target.name]: value});
    }
    const reset = (e) => {
        e.preventDefault()
        setIsOpen(false)
    };

        const updatePerson = async (e) => {
            e.preventDefault();
            const response = await fetch(USER_BASE_URL +'/'+ personId, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(person)
            })
            if  (!response.ok) {
                throw new Error("error")
            }
            const _person = await response.json();
            setResponsePerson(_person);
            reset(e)
        } 



  return (
    <>
    <Transition appear show = {isOpen} as = {Fragment}>
    <Dialog as="div" className="fixed inset-0 z-10 overflow-auto"
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
                <Dialog.Title as='h3' className="text-lg font-medium leading-6 text-gray-900">
                    Update Person</Dialog.Title>
                <div className='flex max-w-md max-auto'>
                    <div className='py-2'>
                        <div className='h-14 my-4'>
                            <label className='block text-gray-600 text-sm font-normal'>
                                First name
                            </label>
                            <input 
                            type="text" 
                            name="first_name" 
                            value={person.first_name}
                            onChange={(e) => handleChange(e)}
                            className='h-10 w-96 border mt-2 px-2 py-2'></input>
                        </div>
                        <div className='h-14 my-4'>
                            <label className='block text-gray-600 text-sm font-normal'>
                                Last name
                            </label>
                            <input type="text" 
                            name="last_name" 
                            value={person.last_name}
                            onChange={(e) => handleChange(e)}
                            className='h-10 w-96 border mt-2 px-2 py-2'></input>
                        </div>
                        <div className='h-14 my-4'>
                            <label className='block text-gray-600 text-sm font-normal'>
                                Password
                            </label>
                            <input type="text" 
                            name="password" 
                            value={person.password}
                            onChange={(e) => handleChange(e)}
                            className='h-10 w-96 border mt-2 px-2 py-2'></input>
                        </div>
                        <div className='h-14 my-4'>
                            <label className='block text-gray-600 text-sm font-normal'>
                                Phone 
                            </label>
                            <input type="text" 
                            name="phone" 
                            value={person.phone}
                            onChange={(e) => handleChange(e)}
                            className='h-10 w-96 border mt-2 px-2 py-2'></input>
                        </div>
                        <div className='h-14 my-4'>
                            <label className='block text-gray-600 text-sm font-normal'>
                                Email
                            </label>
                            <input type="text" 
                            name="email" 
                            value={person.email}
                            onChange={(e) => handleChange(e)}
                            className='h-10 w-96 border mt-2 px-2 py-2'></input>
                        </div>
                        <div className='h-14 my-4'>
                            <label className='block text-gray-600 text-sm font-normal'>
                                Location
                            </label>
                            <input type="text" 
                            name="location" 
                            value={person.location}
                            onChange={(e) => handleChange(e)}
                            className='h-10 w-96 border mt-2 px-2 py-2'></input>
                        </div>
                        <div className='h-14 my-4 space-x-4 pt-4'>
                            <button
                             onClick={updatePerson} className='rounded text-white font-semibold bg-green-400 hover:bg-green-600 py-2 px-6'>
                             Update
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
</>
  )
}

export default EditPerson