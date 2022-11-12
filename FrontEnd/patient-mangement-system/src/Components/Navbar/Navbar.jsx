import React, { useEffect, useState } from 'react'
import s from './Navbar.module.css'
import { useDispatch, useSelector } from 'react-redux';
import { logoutReducer, selectUser } from 'Features/userSlice.js';
import { useNavigate } from 'react-router-dom';

function Navbar() {

    // const [user, setUser] = useState(useSelector(selectUser));
    const user = useSelector(state => state.user);
    const [signedIn, setSignedIn] = useState(false);
    const navigate = useNavigate();
    useEffect(() => {
        if (user.user !== null) {
            setSignedIn(true);
        }
    }, [user])

    const dispatch = useDispatch();

    const logoutFun = (e) => {
        e.preventDefault();
        dispatch(logoutReducer());
        setSignedIn(false);
        navigate("/login");
    }

    const signUpHandler = (e) => {
        e.preventDefault();
        navigate("/signUP");
    }

    const signUpLoginButton = <button type="button" className={s.nav__button} onClick={signUpHandler}>Login / SignUp</button>;

    const signOutButton = <button onClick={logoutFun} className={s.nav__button}>LogOut</button>

    return (
        <nav className={s.nav__container}>
            <div className={s.nav__div__container}>
                <div className={s.nav__left}>
                    <img src={require('Assets/Images/logo_img.png')} className={s.nav__img} alt="DOCCARE Logo" />
                    <span className={s.nav__img__txt}>DOCCURE</span>
                </div>

                <div className={s.nav__right} id="navbar-solid-bg">
                    <ul className={s.nav__ul}>
                        <li>
                            <a href="dfd" className={s.nav__li} aria-current="page">Home</a>
                        </li>
                        <li>
                            <a href="dfdf" className={s.nav__li}>Services</a>
                        </li>
                        <li>
                            <a href="fdf" className={s.nav__li}>Pricing</a>
                        </li>
                        <li>
                            {
                                signedIn ? signOutButton : signUpLoginButton
                            }
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    )
}

export default Navbar