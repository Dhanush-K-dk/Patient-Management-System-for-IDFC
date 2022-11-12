import React from 'react'
import { useRef, useState, useEffect } from "react";
import { faCheck, faTimes, faInfoCircle } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import s from "./SignUp.module.css";
import { checkUserNamePresent, checkEmail, addUser } from 'Api/userApi.js';
import { Link } from 'react-router-dom';

const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
const EMAIL_REGEX = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;

function SignUp() {

    const userRef = useRef();
    const errRef = useRef();

    const [userName, setUserName] = useState('');
    const [validUserName, setValidUserName] = useState(false);
    const [userNameFocus, setUserNameFocus] = useState(false);

    const [email, setEmail] = useState('');
    const [validEmail, setValidEmail] = useState(false);
    const [emailFocus, setEmailFocus] = useState(false);

    const [pwd, setPwd] = useState('');
    const [validPwd, setValidPwd] = useState(false);
    const [pwdFocus, setPwdFocus] = useState(false);

    const [matchPwd, setMatchPwd] = useState('');
    const [validMatch, setValidMatch] = useState(false);
    const [matchFocus, setMatchFocus] = useState(false);

    const [userNameExist, setUserNameExist] = useState(false);
    const [emailExist, setEmailExist] = useState(false);

    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState(false);

    useEffect(() => {
        userRef.current.focus();
    }, [])

    useEffect(() => {
        setValidUserName(USER_REGEX.test(userName));
    }, [userName])

    useEffect(() => {
        setValidEmail(EMAIL_REGEX.test(email));
    }, [email])

    useEffect(() => {
        setValidPwd(PWD_REGEX.test(pwd));
        setValidMatch(pwd === matchPwd);
    }, [pwd, matchPwd])

    useEffect(() => {
        setErrMsg('');
        setUserNameExist(false);
        setEmailExist(false);
    }, [userName, pwd, matchPwd, email])

    const handleSubmit = async (e) => {
        try {
            e.preventDefault();
            const userNameRes = await checkUserNamePresent(userName);
            setUserNameExist(userNameRes);
            const emailRes = await checkEmail(email);
            setEmailExist(emailRes);
            if (userNameExist || emailExist) {
                return;
            }

            const res = await addUser({
                userName: userName,
                email: email,
                password: pwd
            });
            setSuccess(true);
            setUserName('');
            setEmail('');
            setPwd('');
            setMatchPwd('');
        }
        catch (err) {
            setErrMsg('Registration Failed');
            errRef.current.focus();
            throw new Error(err.message);
        }
    }

    return (
        <>
            {success
                ?
                (
                    <section className={s.form__container}>
                        <h1 className='text-3xl mb-4'>Success!</h1>
                        <p>
                            <Link to='/login'>Sign In</Link>
                        </p>
                    </section>
                )
                :
                (
                    <section className={s.form__container}>
                        <p ref={errRef} className={errMsg ? s.error : "hidden"}
                        >{errMsg}</p>
                        <h1 className='text-center text-4xl font-semibold'>Register</h1>
                        <form onSubmit={handleSubmit}>
                            <div className={s.form__div}>
                                <label htmlFor="username">
                                    Username:
                                    <FontAwesomeIcon icon={faCheck} className={validUserName ? s.valid : "hidden"} />
                                    <FontAwesomeIcon icon={faTimes} className={validUserName || !userName ? "hidden" : s.invalid} />
                                </label>
                                <input
                                    className={s.form__input}
                                    type="text"
                                    id="username"
                                    ref={userRef}
                                    autoComplete="off"
                                    onChange={(e) => setUserName(e.target.value)}
                                    value={userName}
                                    required
                                    onFocus={() => setUserNameFocus(true)}
                                    onBlur={() => setUserNameFocus(false)}
                                />
                                <p id="userNameNote" className={userNameFocus && userName && !validUserName ? s.instructions : "hidden"}>
                                    <FontAwesomeIcon icon={faInfoCircle} />
                                    &nbsp; 4 to 24 characters.<br />
                                    Must begin with a letter.<br />
                                    Letters, numbers, underscores, hyphens allowed.
                                </p>
                                <p id="userExist" className={userNameExist ? s.instructions : "hidden"}>
                                    <FontAwesomeIcon icon={faInfoCircle} />
                                    &nbsp; UserName Taken!!!
                                </p>
                            </div>

                            <div className={s.form__div}>
                                <label htmlFor="userEmail">
                                    Email-Id:
                                    <FontAwesomeIcon icon={faCheck} className={validEmail ? s.valid : "hidden"} />
                                    <FontAwesomeIcon icon={faTimes} className={validEmail || !email ? "hidden" : s.invalid} />
                                </label>
                                <input
                                    className={s.form__input}
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
                                <p id="emailNote" className={emailFocus && email && !validEmail ? s.instructions : "hidden"}>
                                    <FontAwesomeIcon icon={faInfoCircle} />
                                    &nbsp; Invalid EmailID !!!
                                </p>
                                <p id="emailExist" className={emailExist ? s.instructions : "hidden"}>
                                    <FontAwesomeIcon icon={faInfoCircle} />
                                    &nbsp; Email Account Exists !!!
                                </p>
                            </div>


                            <div className={s.form__div}>
                                <label htmlFor="password">
                                    Password:
                                    <FontAwesomeIcon icon={faCheck} className={validPwd ? s.valid : "hidden"} />
                                    <FontAwesomeIcon icon={faTimes} className={validPwd || !pwd ? "hidden" : s.invalid} />
                                </label>
                                <input
                                    className={s.form__input}
                                    type="password"
                                    id="password"
                                    onChange={(e) => setPwd(e.target.value)}
                                    value={pwd}
                                    required
                                    onFocus={() => setPwdFocus(true)}
                                    onBlur={() => setPwdFocus(false)}
                                />
                                <p id="pwdnote" className={pwdFocus && !validPwd ? s.instructions : "hidden"}>
                                    <FontAwesomeIcon icon={faInfoCircle} />
                                    &nbsp;8 to 24 characters.<br />
                                    Must include uppercase and lowercase letters, a number and a special character.<br />
                                    Allowed special characters: !, @, #, $, %
                                </p>
                            </div>


                            <div className={s.form__div}>
                                <label htmlFor="confirm_pwd">
                                    Confirm Password:
                                    <FontAwesomeIcon icon={faCheck} className={validMatch && matchPwd ? s.valid : "hidden"} />
                                    <FontAwesomeIcon icon={faTimes} className={validMatch || !matchPwd ? "hidden" : s.invalid} />
                                </label>
                                <input
                                    className={s.form__input}
                                    type="password"
                                    id="confirm_pwd"
                                    onChange={(e) => setMatchPwd(e.target.value)}
                                    value={matchPwd}
                                    required
                                    onFocus={() => setMatchFocus(true)}
                                    onBlur={() => setMatchFocus(false)}
                                />
                                <p id="confirmnote" className={matchFocus && !validMatch ? s.instructions : "hidden"}>
                                    <FontAwesomeIcon icon={faInfoCircle} />
                                    Must match the first password input field.
                                </p>
                            </div>

                            <button className={s.form__button} disabled={validUserName && validEmail && validPwd && validMatch ? false : true}>Sign Up</button>
                        </form>
                        <p className='pt-5 text-lg'>
                            Already registered?<br />
                            <span className="line">
                                <Link to='/login'>Sign In</Link>
                            </span>
                        </p>
                    </section>
                )}
        </>
    )
}

export default SignUp