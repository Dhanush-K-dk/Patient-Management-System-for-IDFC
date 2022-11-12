import Appointment from 'Pages/Appointment/Appointment';
import Dashboard from 'Pages/Dashboard/Dashboard';
import Diagnose from 'Pages/Diagnose/Diagnose';
import Login from 'Pages/Login/Login';
import SignUp from 'Pages/SignUp/SignUp';
import DoctorProfile from 'Pages/UserProfile/DoctorProfile';
import PatientProfile from 'Pages/UserProfile/PatientProfile';
import React from 'react';
import { Route, Routes } from 'react-router-dom';


const Router = () => (
    <Routes>
        <Route exact path='/login' element={<Login />} />
        <Route exact path='/signUp' element={<SignUp />} />
        <Route exact path='/dashboard' element={<Dashboard />} />
        <Route exact path='/bookAppointment/:userId' element={<Appointment />} />
        <Route exact path='/diagnose/:patientProblemId' element={<Diagnose />} />
        <Route exact path='/editPatientProfile/:userId' element={<PatientProfile />} />
        <Route exact path='/editDoctorProfile/:userId' element={<DoctorProfile />} />

        {/* <Route element={Page404} /> */}
    </Routes>
);

export default Router;