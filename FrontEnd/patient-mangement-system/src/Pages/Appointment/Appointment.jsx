import { addAppointment, checkAppointment } from 'Api/appointmentApi';
import React, { useEffect, useState } from 'react'
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import s from './Appointment.module.css'
import { useLocation, useNavigate, useParams } from 'react-router-dom';
import { addPatientProblem } from 'Api/patientProblemApi';
import { useSelector } from 'react-redux';
import { selectUser } from 'Features/userSlice';
import { getPatientProfileByUserId } from 'Api/patientInfo';

function Appointment() {

    const location = useLocation();
    const navigate = useNavigate();
    const userId = useParams();

    const [selectedDate, setSelectedDate] = useState(null);
    const [slot, setSlot] = useState('');
    const [symptoms, setSymptoms] = useState('');
    const [pastMedHist, setPastMedHist] = useState('');

    const [availTime, setAvailTime] = useState({ "1pm": true, "3pm": true, "5pm": true });

    const [patientProfile, setPatientProfile] = useState({});
    const [profileError, setProfileError] = useState('');

    const [user, setUser] = useState(useSelector(selectUser));
    const [docData, setDocData] = useState(location.state);

    const [date, setDate] = useState('');

    const [appointmentAvailable, setAppointmentAvailable] = useState(false);
    const [errMsg, setErrMsg] = useState('');


    useEffect(() => {
        console.log(userId.userId);
        getPatientProfileByUserId(userId.userId)
            .then((res) => {
                setPatientProfile(res);
                setProfileError('');
            })
            .catch((err) => {
                setPatientProfile({})
                setProfileError('Please Set Your profile');
                throw new Error(JSON.stringify(err.message));
            })
    }, []);

    useEffect(() => {
        if (selectedDate !== null) {
            let formattedDate = `${selectedDate.getDate()}/${selectedDate.getMonth() + 1}/${selectedDate.getFullYear()}`;
            setDate(formattedDate);
            // console.log(location.state);
            // TODO: 💥
            checkAppointment(formattedDate, docData.userId)
                .then((res) => {
                    setAppointmentAvailable(true);
                    if (typeof res.data === 'boolean') {
                        setErrMsg('');
                        setAvailTime((prevState) => ({
                            ...prevState,
                            "1pm": true,
                            "3pm": true,
                            "5pm": true
                        }))
                        return;
                    }
                    // console.log(res.data.length);
                    for (const time of res.data) {
                        setAvailTime((prevState) => ({
                            ...prevState,
                            [time]: false
                        }))
                    }

                })
                .catch(err => {
                    setErrMsg('No appointment available');
                    throw new Error(err);
                })
        }
    }, [selectedDate]);



    const handelChanges = (event) => {
        setSlot(event.target.value);
    }

    const handleDateChanges = (date) => {
        // console.log(date);
        // console.log(typeof formattedDate);
        setSelectedDate(date);
    }

    const bookAppointment = async (e) => {
        e.preventDefault();
        // console.log(symptoms);
        // console.log(slot);
        // console.log(selectedDate);

        try {
            if (appointmentAvailable && profileError.length === 0) {
                const problemRes = await addPatientProblem({
                    doctorId: docData.userId,
                    patientId: user.id,
                    pastMedHist: pastMedHist,
                    symptoms: symptoms
                });
                const appointmentResp = await addAppointment({
                    appointmentDate: date,
                    appointmentTime: slot,
                    doctorId: docData.userId,
                    patientId: user.id
                });
                console.log(problemRes);
                console.log(appointmentResp);
                setSelectedDate(null);
                setSlot('');
                setAppointmentAvailable(false);
                navigate("/dashboard");
            }
        } catch (error) {
            console.log(JSON.parse(error.message));

        }

    }

    const handelProfile = (e) => {
        e.preventDefault();
        navigate(`/editPatientProfile/${user.id}`, { state: { patientProfile } });
    }

    return (
        <section className='form__container'>
            <div className='form__div'>
                Doc Info
            </div>
            <div className='form__div'>
                Patient Info
                <button className='ml-10' onClick={handelProfile}>Add/Edit profile</button>
                <div>
                    <div className={profileError ? "hidden" : "flex flex-col text-center p-5"}>
                        <p>{patientProfile.name}</p>
                        <p>{patientProfile.gender}</p>
                        <p>{patientProfile.address}</p>
                        <p>{patientProfile.city}</p>
                        <p>{patientProfile.phoneNo}</p>
                    </div>
                    <p className={profileError ? "error" : "hidden"}
                    >{profileError}</p>
                </div>
            </div>
            <div >
                <div className='form__div flex'>
                    <div>

                        <DatePicker selected={selectedDate} onChange={handleDateChanges} />
                    </div>
                    <div className="mb-3 xl:w-96 ml-10">
                        <select className={s.appointment__select} onChange={handelChanges}>
                            <option value="1pm" disabled={!availTime['1pm']}>1pm</option>
                            <option value="3pm" disabled={!availTime['3pm']}>3pm</option>
                            <option value="5pm" disabled={!availTime['5pm']}>5pm</option>
                        </select>
                    </div>
                </div>
                <div>
                    <p className={errMsg ? "error" : "hidden"}
                    >{errMsg}</p>
                </div>
            </div>
            <div className='form__div'>
                problem
                <div className="flex justify-center">
                    <div className="mb-3 xl:w-96">
                        <label for="symptoms" className={s.appointment__msg__label}
                        >Symptoms</label>
                        <textarea
                            className={s.appointment__msg__input}
                            id="symptoms"
                            onChange={(e) => setSymptoms(e.target.value)}
                            value={symptoms}
                            required
                            rows="3"
                            placeholder="Enter your Symptoms..."
                        ></textarea>
                    </div>
                </div>
                <div className="flex justify-center">
                    <div className="mb-3 xl:w-96">
                        <label for="pastHis" className={s.appointment__msg__label}
                        >Past Medical History</label>
                        <textarea
                            className={s.appointment__msg__input}
                            id="pastHis"
                            onChange={(e) => setPastMedHist(e.target.value)}
                            value={pastMedHist}
                            required
                            rows="3"
                            placeholder="Enter your Past Medical History..."
                        ></textarea>
                    </div>
                </div>
                <div className="flex justify-center">
                    <button className='card__button' onClick={bookAppointment} disabled={!appointmentAvailable && !patientProfile}>Book Appointment</button>
                </div>
            </div>

        </section>

    )
}

export default Appointment