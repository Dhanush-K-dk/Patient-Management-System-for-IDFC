import axios from 'axios';

const URL = 'http://localhost:1100/user';

export const checkUserNamePresent = async (userName) => {

    try {
        const res = await axios.get(`${URL}/checkUserName/?userName=${userName}`);
        return res.data;
    }
    catch (err) {
        throw new Error(err.message);
    }
}

export const checkEmail = async (email) => {
    try {
        const res = await axios.get(`${URL}/checkEmail/?email=${email}`);
        return res.data;
    }
    catch (err) {
        return null;
    }
}

export const addUser = async (user) => {
    try {
        const res = await axios.post(`${URL}/`, user);
        return res.data;
    }
    catch (err) {
        throw new Error(err.message);
    }
}

export const login = async (credentials) => {
    try {
        const res = await axios.post(`${URL}/login`, credentials);
        return res.data;
    } catch (error) {
        throw new Error(JSON.stringify(error.response));
        // throw new Error(error);
    }
}

