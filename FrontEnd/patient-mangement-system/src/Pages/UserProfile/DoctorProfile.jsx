import { addDoctorProfile } from 'Api/doctorInfoApi';
import React, { useState } from 'react'
import { useLocation, useNavigate, useParams } from 'react-router-dom';

function DoctorProfile() {

    const { userId } = useParams();
    const profile = useLocation().state;
    const navigate = useNavigate();

    const [name, setName] = useState('');
    const [education, setEducation] = useState('');
    const [specialization, setSpecialization] = useState('');
    const [phNo, setPhNo] = useState(0);

    const submitHandler = async (e) => {
        e.preventDefault();
        try {
            if (profile == null) {
                const res = await addDoctorProfile({
                    education: education,
                    name: name,
                    phoneNo: phNo,
                    specialization: specialization,
                    userId: userId
                });
                console.log(res);
            }
            else {
                console.log("update it");
            }
            navigate("/dashboard");
        } catch (error) {

        }
    }

    return (
        <section>
            <div className='form__container'>
                <form onSubmit={submitHandler}>
                    <input type="text" placeholder='Name' value={name} onChange={e => setName(e.target.value)} />
                    <input type="text" placeholder='Education' value={education} onChange={e => setEducation(e.target.value)} />
                    <input type="text" placeholder='Specialization' value={specialization} onChange={e => setSpecialization(e.target.value)} />
                    <input type="numeric" placeholder='Phone No' value={phNo} onChange={e => setPhNo(e.target.value)} />
                    <button>Update / Add Profile</button>
                </form>
            </div>
        </section>
    )
}

export default DoctorProfile