import axios from 'axios';

const URL = 'http://localhost:1100/patientInfo';

export const getPatientProfileByUserId = async (userId) => {
    try {
        const res = await axios.get(`${URL}/user/${userId}`);
        return res.data;
    } catch (error) {
        throw new Error(JSON.stringify(error.response));
    }
}

export const addPatientProfile = async (profileData) => {
    try {
        const res = await axios.post(`${URL}/`, profileData);
        return res.data;
    } catch (error) {
        throw new Error(JSON.stringify(error.response));
    }
}

// export const getPatientInfoByProfile