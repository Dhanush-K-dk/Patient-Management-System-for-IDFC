import { getProblemByProblemId } from 'Api/patientProblemApi';
import { addPrescription } from 'Api/prescriptionApi';
import React, { useEffect, useState } from 'react'
import { useLocation, useNavigate, useParams } from 'react-router-dom'

function Diagnose() {

    const { patientProblemId } = useParams();

    const patientProblem = useLocation().state;

    const navigate = useNavigate();

    const [diagnosisTitle, setDiagnosisTitle] = useState('');
    const [expertComments, setExpertComments] = useState('');
    const [prescriptionDetails, setPrescriptionDetails] = useState('');

    // const [patientProblem, setPatientProblem] = useState({})

    // useEffect(() => {
    // }, [])

    const prescribePatient = async (e) => {
        e.preventDefault();
        try {
            const res = await addPrescription({
                patientId: patientProblem.patientId,
                patientProblemId: patientProblem.id,
                prescDetails: prescriptionDetails,
                diagnosis: {
                    diagnosisTitle: diagnosisTitle,
                    expertComments: expertComments
                }
            })
            setDiagnosisTitle('');
            setExpertComments('');
            setPrescriptionDetails('');
            navigate("/dashboard");
        } catch (error) {
            const err = JSON.parse(error.message);
            throw new Error(err.message);
        }
    }

    return (
        <section className='form__container'>
            <div className='form__div'>
                Patient Personal Detail
            </div>
            <div className='form__div'>
                Patient Problem
                <p>{patientProblem.symptoms}</p>
                <p>{patientProblem.pastMedHist}</p>
            </div>
            <div className='form__div'>
                Appointement Info
            </div>
            <div className='form__div'>
                <div className="flex justify-center">
                    <div className="mb-3 xl:w-96">
                        <label for="digTitle" className='s.appointment__msg__label'
                        >Diagnosis Title</label>
                        <textarea
                            className='appointment__msg__input'
                            id="digTitle"
                            onChange={(e) => setDiagnosisTitle(e.target.value)}
                            value={diagnosisTitle}
                            required
                            rows="3"
                            placeholder="Enter your Diagnosis..."
                        ></textarea>
                    </div>
                </div>
                <div className="form__div flex justify-center">
                    <div className="mb-3 xl:w-96">
                        <label for="expComment" className='appointment__msg__label'
                        >Expert Comments</label>
                        <textarea
                            className='appointment__msg__input'
                            id="expComment"
                            onChange={(e) => setExpertComments(e.target.value)}
                            value={expertComments}
                            required
                            rows="3"
                            placeholder="Comments ..."
                        ></textarea>
                    </div>
                </div>
                <p>Prescription</p>
                <div className="flex justify-center">
                    <div className="mb-3 xl:w-96">
                        <label for="presDetails" className='s.appointment__msg__label'
                        >Prescription</label>
                        <textarea
                            className='appointment__msg__input'
                            id="presDetails"
                            onChange={(e) => setPrescriptionDetails(e.target.value)}
                            value={prescriptionDetails}
                            required
                            rows="3"
                            placeholder="Prescription ..."
                        ></textarea>
                    </div>
                </div>
                <div className="flex justify-center">
                    <button className='card__button' onClick={prescribePatient} disabled={false}>Send Prescription</button>
                </div>
            </div>
        </section>

    )
}

export default Diagnose