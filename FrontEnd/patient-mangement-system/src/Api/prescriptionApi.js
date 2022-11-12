import axios from 'axios';

const URL = 'http://localhost:9090/prescription';

export const addPrescription = async (prescription) => {
    try {
        const res = await axios.post(`${URL}/`, prescription);
        return res.data;
    } catch (error) {
        throw new Error(JSON.stringify(error.response));
    }
}