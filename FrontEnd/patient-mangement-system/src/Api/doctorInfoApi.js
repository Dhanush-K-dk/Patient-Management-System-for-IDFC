import axios from 'axios';

const URL = 'http://localhost:1100/doctorInfo';

export const getAllDoctorInfo = async (specialization) => {
    try {
        const res = await axios.get(`${URL}/specialization/?specialization=${specialization}`);
        return res.data;
    } catch (error) {
        console.log(error);
        throw new Error(JSON.stringify(error.response));
    }

}

export const getDoctorInfoByUserId = async (userId) => {
    try {
        const res = await axios.get(`${URL}/user/${userId}`);
        return res.data;
    } catch (error) {
        throw new Error(JSON.stringify(error.response));
    }
}

export const addDoctorProfile = async (docProfileData) => {
    try {
        const res = await axios.post(`${URL}/`, docProfileData);
        return res.data;
    } catch (error) {
        throw new Error(JSON.stringify(error.response));
    }
}