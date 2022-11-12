import Card from 'Components/DoctorCard/Card';
import React, { useEffect, useState } from 'react'
import { getAllDoctorInfo } from 'Api/doctorInfoApi.js'
import s from './PatientDashboard.module.css';

function PatientDashboard() {

    const [docList, setDocList] = useState([]);
    const [specialization, setSpecialization] = useState("all");
    const [listLength, setListLength] = useState(0);

    useEffect(() => {
        getAllDoctorInfo(specialization)
            .then((res) => {
                setDocList(res);
                setListLength(res.length);
            })
            .catch((error) => {
                const err = JSON.parse(error.message);
                throw new Error(err.data);
            });
    }, [specialization]);

    const handelChanges = (event) => {
        setSpecialization(event.target.value);
    }

    return (
        <section>
            <div className={s.specialization__container}>
                <div className='ml-5'>
                    <h1>Results Found : {listLength}</h1>
                </div>
                <div className="flex justify-center p-5">
                    <div className="mb-3 xl:w-96">
                        <select className={s.specialization__select} onChange={handelChanges}>
                            <option value="all" selected>Specialization</option>
                            <option value="Dermatology">Dermatology</option>
                            <option value="Immunology">Immunology</option>
                            <option value="Cardiology">Cardiology</option>
                        </select>
                    </div>
                </div>
            </div>
            <div>
                {
                    docList.map((docInfo, i) => (
                        <>
                            <Card docInfo={docInfo} key={i} />
                        </>
                    ))
                }
            </div>

        </section>
    )
}

export default PatientDashboard