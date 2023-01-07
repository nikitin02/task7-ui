import { React, useEffect, useState } from "react";
import EditPerson from "./EditPerson";
import Person from "./Person"

const PersonList = ({person}) => {
    const USER_BASE_URL = "http://localhost:8888/people";
    const [people, setPeople] = useState(null);
    const [loading, setLoading] = useState(true);
    const [personId, setPersonId] = useState(null);
    const [responsePerson, setResponsePerson] = useState(null);

    useEffect(() => {
      const fetchData = async () => {
        setLoading(true);
        try { 
            const response = await fetch(USER_BASE_URL, {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                },
            });
            const people = await response.json();
            setPeople(people);
        } catch (error) {
            console.log(error);
        }
        setLoading(false);
      };
      fetchData();
    }, [person, responsePerson]);
    
    const deletePerson = (e, id) => {
        e.preventDefault();
        fetch(USER_BASE_URL + '/' + id, {
                method: "DELETE",
        }).then((res) => {
            if(people) {
                setPeople((prevElement) => {
                    return prevElement.filter((person)=> person.id !==id);
                })
            }
        })
    }

    const editPerson = (e, id) => {
        e.preventDefault();
        setPersonId(id);
    }
 
  return (
    <>
    <div className="container mx-auto my-8 px-8">
        <div className="flex shadow border-b">
            <table className="min-w-full" >
             <thead className="bg-gray-100">
                <tr>
                    <th className="text-left font-medium text-teal-700 uppercase tracking-wide py-3 px-6">
                        First Name</th>
                    <th className="text-left font-medium text-teal-700 uppercase tracking-wide py-3 px-6">
                        Last Name</th>
                    <th className="text-left font-medium text-teal-700 uppercase tracking-wide py-3 px-6">
                        Phone</th>
                    <th className="text-left font-medium text-teal-700 uppercase tracking-wide py-3 px-6">
                        Email</th>
                    <th className="text-left font-medium text-teal-700 uppercase tracking-wide py-3 px-6">
                        Location</th>
                    <th className="text-right font-medium text-teal-700 uppercase tracking-wide py-3 px-6">
                        Actions</th>
                </tr>
               </thead>
               {!loading && (
               <tbody className="bg-white">
                    {people?.map((person) => (
                      <Person person={person} 
                      key={person.id} 
                      deletePerson={deletePerson} 
                      editPerson={editPerson}/>
                    ))}
               </tbody>
               )}
            </table>
        </div>
    </div>
    <EditPerson personId={personId} setResponsePerson={setResponsePerson}/>
    </>
  )
}

export default PersonList