import React from 'react'
import { useNavigate } from 'react-router-dom';

function PatientCard({ patientProblem }) {

    const navigate = useNavigate();

    const diagnosePatient = (e) => {
        e.preventDefault();
        navigate(`/diagnose/${patientProblem.id}`, { state: patientProblem });
    }
    return (
        <section>
            <div className="flex justify-center p-2">
                <div className="card__div p-10">
                    <img className="card__img" src="" alt="patientImg" />
                    <div className="p-6 flex flex-col justify-start ml-10">
                        <h4 className="card__heading">
                            Patient-Id:-{patientProblem.patientId}
                        </h4>
                        <p className="text-gray-700 text-base mb-4">
                            Patient-Symptoms:-{patientProblem.symptoms}
                        </p>
                        <p className="text-gray-700 text-base mb-4">
                            Past-Medic-History:-{patientProblem.pastMedHist}
                        </p>
                        <div className='flex'>
                            <button type="button" className="card__button" onClick={diagnosePatient}>diagnose</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    )
}

export default PatientCard