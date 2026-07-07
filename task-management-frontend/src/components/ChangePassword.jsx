import { useState } from "react";
import api from "../api/axios";
import "../styles/ChangePassword.css";

function ChangePassword({ close }) {

    const [currentPassword, setCurrentPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");
    const [confirmPassword, setConfirmPassword] = useState("");

    const changePassword = async () => {

        if (newPassword !== confirmPassword) {

            alert("Passwords do not match");

            return;
        }

        try {

            await api.put("/users/change-password", {
                currentPassword,
                newPassword

            });

            alert("Password Changed Successfully");

            close();

        } catch (error) {

            console.log(error);

        }

    };
        
            { return (

    <div className="change-password">

        <h2>Change Password</h2>

        <input
            type="password"
            placeholder="Current Password"
            value={currentPassword}
            onChange={(e) =>
                setCurrentPassword(e.target.value)
            }
        />

        <br /><br />

        <input
            type="password"
            placeholder="New Password"
            value={newPassword}
            onChange={(e) =>
                setNewPassword(e.target.value)
            }
        />

        <br /><br />

        <input
            type="password"
            placeholder="Confirm Password"
            value={confirmPassword}
            onChange={(e) =>
                setConfirmPassword(e.target.value)
            }
        />

        <br /><br />
        <div className="password-buttons">
            <button className="cancel-btn"
            onClick={close}
            >
                Cancel
            </button>

        <button 
        className="update-btn"
        onClick={changePassword}
        >
            Update Password
        </button>

        </div>

    </div>
        

    );
    
}

}

export default ChangePassword;