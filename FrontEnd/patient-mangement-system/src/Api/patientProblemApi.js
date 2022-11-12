import axios from 'axios';

const URL = 'http://localhost:9040/patientProblem';

export const addPatientProblem = async (data) => {
    try {
        const res = await axios.post(`${URL}/`, data);
        return res.data;
    } catch (error) {
        throw new Error(JSON.stringify(error.response));
    }
}

export const getPatientProblemByDoctorId = async (docId) => {
    try {
        const res = await axios.get(`${URL}/getPatientByDoctorId/?doctorId=${docId}`);
        return res.data
    } catch (error) {
        throw new Error(JSON.stringify(error.response));
    }
}

export const getProblemByProblemId = async (problemId) => {
    try {
        const res = await axios.get(`${URL}/getProblemByProblemId/${problemId}`);
        return res.data;
    } catch (error) {
        throw new Error(JSON.stringify(error.response));
    }
}