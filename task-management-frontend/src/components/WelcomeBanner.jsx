import { useEffect, useState } from "react";
import api from "../api/axios";
import "../styles/WelcomeBanner.css";

function WelcomeBanner() {

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

    return (

        <div className="welcome-banner">
            <h2>
                👋 Welcome Back, {name}!
            </h2>
        </div>

    );

}

export default WelcomeBanner;