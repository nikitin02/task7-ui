import React from 'react'

const Person = ({ person, deletePerson, editPerson}) => {
  return (
    <tr key={person.id}>
    <td className="text-left px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-teal-700">{person.first_name}</div>
    </td>
    <td className="text-left px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-teal-700">{person.last_name}</div>
    </td>
    <td className="text-left px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-teal-700">{person.phone}</div>
    </td>
    <td className="text-left px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-teal-700">{person.email}</div>
    </td>
    <td className="text-left px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-teal-700">{person.location}</div>
    </td>
    <td className="text-right px-6 py-4 whitespace-nowrap">
        <a 
         onClick={(e,id) => editPerson(e, person.id)} 
         className="text-indigo-600 hover:text-indigo-800 hover:cursor-pointer px-4">
            Edit
        </a>
        <a 
            onClick={(e,id) => deletePerson(e, person.id)}
        className="text-indigo-600 hover:text-indigo-800 hover:cursor-pointer ">
            Delete
        </a>
    </td>

</tr>
  )
}

export default Person