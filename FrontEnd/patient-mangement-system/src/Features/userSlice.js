import { createSlice } from '@reduxjs/toolkit';

export const userSlice = createSlice({
    name: 'user',
    initialState: {
        user: null
    },
    reducers: {
        loginReducer: (state, action) => {
            console.log(state);
            state.user = action.payload;
        },
        logoutReducer: (state) => {
            state.user = null;
        }
    },
});

export const { loginReducer, logoutReducer } = userSlice.actions;

export const selectUser = (state) => state.user.user;

export default userSlice.reducer;