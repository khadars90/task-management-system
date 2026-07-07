import { useState } from "react";
import api from "../api/axios";
import { useNavigate } from "react-router-dom";
import "../styles/Login.css";

function Login() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const navigate = useNavigate();

    const handleLogin = async () => {

        try {

            const response = await api.post(
                "/auth/login",
                {
                    email,
                    password
                }
            );

            localStorage.setItem("token", response.data);

            navigate("/dashboard");

        } catch (error) {

            console.log(error);

            alert("Invalid Email or Password");

        }

    };

    return (

        <div className="login-page">

            <div className="login-container">

                {/* Left Side */}

                <div className="login-left">

                    <h2>Welcome Back 👋</h2>

                    <p className="login-subtitle">
                        Sign in to continue managing your tasks.
                    </p>

                    <input
                        className="login-input"
                        type="email"
                        placeholder="Enter Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />

                    <input
                        className="login-input"
                        type="password"
                        placeholder="Enter Password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />

                    <button
                        className="login-btn"
                        onClick={handleLogin}
                    >
                        Login
                    </button>
                    
                    <div className="login-footer">

                    <p>
                    Don't have an account?
                    <span
                        onClick={() => navigate("/register")}>
                        Register
                    </span>
                    </p>

</div>

                </div>
                
                

                {/* Right Side */}

                <div className="login-right">

                    <h1>Task Manager</h1>

                    <p>
                        Plan your Days. </p>
                        <br/>
                        <p>Track your Progress.</p>
                        <br/>
                        <p>Finish your goals.</p>
                        
                </div>
                

            </div>

        </div>

    );

}

export default Login;