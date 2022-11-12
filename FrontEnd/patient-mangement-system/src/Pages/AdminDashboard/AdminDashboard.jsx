import { addUser } from 'Api/userApi';
import React, { useState } from 'react'

function AdminDashboard() {

    const [docUserName, setDocUserName] = useState('');
    const [docEmail, setDocEmail] = useState('');
    const [docPwd, setDocPwd] = useState('')

    const submitHandler = async (e) => {
        e.preventDefault();
        try {
            const res = await addUser({
                email: docEmail,
                userName: docUserName,
                password: docPwd,
                role: "doctor"
            });
            console.log(res);
        } catch (error) {
            throw new Error(JSON.parse(error.message));
        }
    }

    return (
        <section>
            <div className='form__container'>
                <form onSubmit={submitHandler}>
                    <input type="text" placeholder='Doctor UserName' value={docUserName} onChange={e => setDocUserName(e.target.value)} />
                    <input type="email" placeholder='Doctor Email' value={docEmail} onChange={e => setDocEmail(e.target.value)} />
                    <input type="password" placeholder='Doctor Password' value={docPwd} onChange={e => setDocPwd(e.target.value)} />
                    <button>Add Doctor</button>
                </form>
            </div>
        </section>
    )
}

export default AdminDashboard