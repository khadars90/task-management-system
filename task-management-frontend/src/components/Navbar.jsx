import { NavLink, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import api from "../api/axios";

import {
    FaTasks,
    FaHome,
    FaClipboardList,
    FaUserCircle,
    FaSignOutAlt
} from "react-icons/fa";

import "../styles/Navbar.css";

function Navbar() {

    const navigate = useNavigate();
    const [name, setName] = useState("");
    useEffect(() => {
    loadProfile();
}, []);

const loadProfile = async () => {

    try {

        const response = await api.get("/users/profile");

        setName(response.data.name);

    } catch (error) {

        console.log(error);

    }

};

    const logout = () => {

        localStorage.removeItem("token");

        navigate("/");

    };

    return (

        <nav className="navbar">

            <div className="logo">

                <FaTasks />

                <span>Task Manager</span>

            </div>

            <div className="nav-links">

                <NavLink to="/dashboard">

                    <FaHome />

                    Dashboard

                </NavLink>

                <NavLink to="/tasks">

                    <FaClipboardList />

                    My Tasks

                </NavLink>

                <NavLink to="/profile">

                    <FaUserCircle />

                    Profile

                </NavLink>

            </div>

            <div className="user-info">

                <div className="avatar">

                    {name ? name.charAt(0).toUpperCase() : "?"}

                </div>

                <button
                    onClick={logout}
                >

                    <FaSignOutAlt />

                    Logout

                </button>

            </div>

        </nav>

    );

}

export default Navbar;