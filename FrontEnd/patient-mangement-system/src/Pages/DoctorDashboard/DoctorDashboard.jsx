import { getDoctorInfoByUserId } from 'Api/doctorInfoApi';
import { getPatientProblemByDoctorId } from 'Api/patientProblemApi';
import PatientCard from 'Components/PatientCard/PatientCard';
import { selectUser } from 'Features/userSlice';
import React, { useEffect, useState } from 'react'
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';

function DoctorDashboard() {

    const [patientProblemList, setPatientProblemList] = useState([]);

    const [docProfile, setDocProfile] = useState(null);
    const [errMsg, setErrMsg] = useState('');

    const user = useSelector(selectUser);
    const navigate = useNavigate();

    useEffect(() => {
        getPatientProblemByDoctorId(user.id)
            .then((res) => {
                setPatientProblemList(res);
                getDoctorInfoByUserId(user.id)
                    .then((res) => {
                        setDocProfile(res);
                        setErrMsg('');
                    })
                    .catch((error) => {
                        setErrMsg('Doctor Profile Not Found Please Add a Profile !!');
                    })
            })
            .catch((error) => {
                const err = JSON.parse(error.message);
                throw new Error(err.data);
            });
    }, []);


    const handleProfileEdit = (e) => {
        e.preventDefault();
        navigate(`/editDoctorProfile/${user.id}`, {
            state: docProfile
        });
    }

    const noList = <p>No list Present</p>

    return (
        <section>
            <h1>Doctor Dashboard</h1>
            <div>
                <p className={errMsg ? "error" : "hidden"}
                >{errMsg}</p>
                <button onClick={handleProfileEdit}>Add/Edit Profile</button>
            </div>
            <div className={errMsg ? "hidden" : ""}>
                {
                    (patientProblemList.length >= 1)
                        ? patientProblemList.map((patientProblem, i) => (
                            <>
                                <PatientCard patientProblem={patientProblem} key={i} />
                            </>
                        ))
                        : noList
                }
            </div>
            {/* <h1>Hello</h1> */}
        </section>
    )
}

export default DoctorDashboard