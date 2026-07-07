import {useState} from "react";
import api from "../api/axios";
import {useNavigate} from "react-router-dom";
import "../styles/Login.css";


function Register(){
    const navigate = useNavigate();
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const handleRegister = async () => {
        try{
            await api.post("/auth/register", {
                name, email, password
            });

            navigate("/", {
                state:{
                    message:"Registration Successful! Please Login."
                }
            });
        }
        catch(error){
            console.log(error);
            alert("Registration Failed");
        }
    };
    return (
        <div className="login-page">
            <div className="login-container">

                {/* Left Side */}
                <div className="login-left">
                    <h2>Create Account</h2>
                    <p className="login-subtitle"> Create your account to start managing your tasks.</p>
                    <input 
                        className="login-input"
                        type = "text"
                        placeholder = "Enter Name"
                        value={name}
                        onChange={(e)=>setName(e.target.value)}
                        />

                        <input 
                        className="login-input"
                        type = "email"
                        placeholder = "Enter Email"
                        value={email}
                        onChange={(e)=>setEmail(e.target.value)}
                        />

                        <input 
                        className="login-input"
                        type = "password"
                        placeholder = "Enter Password"
                        value={password}
                        onChange={(e)=>setPassword(e.target.value)}
                        />

                        <button 
                            className="login-btn"
                            onClick={handleRegister}>
                            Register
                            </button>

                            <div className="login-footer">
                                <p>Already have an account?
                                <span onClick={()=>navigate("/")}> Login  </span>
                                </p>
                            </div>
                </div>

                            {/* Right Side */}
                <div className="login-right">
                    <h1>📝 Task Manager</h1>
                    <p>
                        Start organizing your work today.
                    </p>

                    <br/>

                    <p>✅ Secure Authentication</p>
                    <p>✅ Organize Tasks</p>
                    <p>✅ Meet Deadlines</p>

                </div>


            </div>

        </div>
    );
}

export default Register;