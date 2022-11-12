import { selectUser } from 'Features/userSlice'
import AdminDashboard from 'Pages/AdminDashboard/AdminDashboard';
import DoctorDashboard from 'Pages/DoctorDashboard/DoctorDashboard';
import PatientDashboard from 'Pages/PatientDashboard/PatientDashboard';
import React from 'react'
import { useSelector } from 'react-redux'

const ConditionalDashboard = ({ role }) => {
    if (role === 'admin') {
        return <AdminDashboard />
    }
    else if (role === 'doctor') {
        return <DoctorDashboard />
    }
    else {
        return <PatientDashboard />
    }
}


function Dashboard() {

    const user = useSelector(selectUser);

    return (
        <>
            <ConditionalDashboard role={user.role} />
        </>
    )
}

export default Dashboard