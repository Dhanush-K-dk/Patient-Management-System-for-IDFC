import { addPatientProfile } from 'Api/patientInfo';
import React, { useState } from 'react'
import { useLocation, useNavigate, useParams } from 'react-router-dom';

function PatientProfile() {

    const { userId } = useParams();
    const profile = useLocation().state;
    const navigate = useNavigate();

    const [address, setAddress] = useState('');
    const [city, setCity] = useState('');
    const [gender, setGender] = useState('');
    const [name, setName] = useState('');
    const [phNo, setPhNo] = useState(0);

    const submitHandler = async (e) => {
        e.preventDefault();
        try {
            if (profile.PatientProfile == null) {
                const res = await addPatientProfile({
                    address: address,
                    city: city,
                    gender: gender,
                    name: name,
                    phoneNo: phNo,
                    userId: userId
                })
                console.log(res);
            }
            else {
                console.log("update it ");
            }
            navigate("/bookAppointment");
        } catch (error) {

        }
    }

    return (
        <section>
            <div className='form__container'>
                <form onSubmit={submitHandler}>
                    <input type="text" placeholder='Address' value={address} onChange={e => setAddress(e.target.value)} />
                    <input type="text" placeholder='City' value={city} onChange={e => setCity(e.target.value)} />
                    <input type="text" placeholder='Gender' value={gender} onChange={e => setGender(e.target.value)} />
                    <input type="text" placeholder='Name' value={name} onChange={e => setName(e.target.value)} />
                    <input type="numeric" placeholder='Phone No' value={phNo} onChange={e => setPhNo(e.target.value)} />
                    <button>Update / Add Profile</button>
                </form>
            </div>
        </section>
    )
}

export default PatientProfile