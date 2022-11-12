import { faCheck, faInfoCircle, faTimes } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import React, { useEffect, useRef, useState } from 'react'
import { login } from 'Api/userApi.js';
import { Link } from 'react-router-dom';
import { useDispatch } from 'react-redux';
import { loginReducer } from 'Features/userSlice.js';
import { useNavigate } from 'react-router-dom';

const EMAIL_REGEX = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;

function Login() {

    const navigate = useNavigate();

    const userRef = useRef();
    const errRef = useRef();

    const [email, setEmail] = useState('');
    const [validEmail, setValidEmail] = useState(false);
    const [emailFocus, setEmailFocus] = useState(false);

    const [pwd, setPwd] = useState('');
    const [validPwd, setValidPwd] = useState(false);
    const [pwdFocus, setPwdFocus] = useState(false);

    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState(false);

    useEffect(() => {
        userRef.current.focus();
    }, [])

    useEffect(() => {
        setValidPwd(PWD_REGEX.test(pwd));
    }, [pwd])

    useEffect(() => {
        setValidEmail(EMAIL_REGEX.test(email));
    }, [email])

    useEffect(() => {
        setErrMsg('');
    }, [email, pwd])

    const dispatch = useDispatch();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const res = await login({
                email: email,
                password: pwd
            });
            // console.log(res);
            setSuccess(true);
            dispatch(loginReducer(res));
            navigate("/dashboard");
        } catch (error) {
            const err = JSON.parse(error.message);
            if (err.status === 404) {
                setErrMsg("Invalid EmailId or Password");
            }
            else if (err) {
                setErrMsg("No Server Response");
            }
            else {
                setErrMsg("Login Failed");
            }
            errRef.current.focus();
            throw new Error(err.data);
        }
    }

    return (
        <section className='form__container'>
            <p ref={errRef} className={errMsg ? "error" : "hidden"}
            >{errMsg}</p>
            <h1 className='text-center text-4xl font-semibold'>Login</h1>
            <form onSubmit={handleSubmit}>
                <div className='form__div'>
                    <label htmlFor="userEmail">
                        Email-Id:
                        <FontAwesomeIcon icon={faCheck} className={validEmail ? "valid" : "hidden"} />
                        <FontAwesomeIcon icon={faTimes} className={validEmail || !email ? "hidden" : "invalid"} />
                    </label>
                    <input
                        className='form__input'
                        type="email"
                        id="userEmail"
                        ref={userRef}
                        autoComplete="off"
                        onChange={(e) => setEmail(e.target.value)}
                        value={email}
                        required
                        onFocus={() => setEmailFocus(true)}
                        onBlur={() => setEmailFocus(false)}
                    />
                    <p id="emailNote" className={emailFocus && email && !validEmail ? "instructions" : "hidden"}>
                        <FontAwesomeIcon icon={faInfoCircle} />
                        &nbsp; Invalid EmailID !!!
                    </p>
                </div>


                <div className='form__div'>
                    <label htmlFor="password">
                        Password:
                        <FontAwesomeIcon icon={faCheck} className={validPwd ? "valid" : "hidden"} />
                        <FontAwesomeIcon icon={faTimes} className={validPwd || !pwd ? "hidden" : "invalid"} />
                    </label>
                    <input
                        className='form__input'
                        type="password"
                        id="password"
                        onChange={(e) => setPwd(e.target.value)}
                        value={pwd}
                        required
                        onFocus={() => setPwdFocus(true)}
                        onBlur={() => setPwdFocus(false)}
                    />
                    <p id="pwdnote" className={pwdFocus && !validPwd ? 'instructions' : "hidden"}>
                        <FontAwesomeIcon icon={faInfoCircle} />
                        &nbsp;8 to 24 characters.<br />
                        Must include uppercase and lowercase letters, a number and a special character.<br />
                        Allowed special characters: !, @, #, $, %
                    </p>
                </div>

                <button className='form__button' disabled={validEmail && validPwd ? false : true}>Sign Up</button>
            </form>
            <p className='pt-5 text-lg'>
                Don't Have Account?<br />
                <span className="line">
                    <Link to='/signUp'>Sign Up</Link>
                </span>
            </p>
        </section>
    )
}

export default Login