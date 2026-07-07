import { useEffect, useState } from "react";
import api from "../api/axios";
import Layout from "../components/Layout";
import Modal from "../components/Modal";
import ChangePassword from "../components/ChangePassword";
import "../styles/Profile.css";

function Profile() {

    const [profile, setProfile] = useState({
        name: "",
        email: ""
    });

    const [openPassword, setOpenPassword] = useState(false);

    useEffect(() => {
        loadProfile();
    }, []);

    const loadProfile = async () => {

        try {

            const response = await api.get("/users/profile");

            setProfile(response.data);

        } catch (error) {

            console.log(error);

        }

    };

    const saveProfile = async () => {

        try {

            await api.put("/users/profile", profile);

            alert("Profile Updated Successfully");

        } catch (error) {

            console.log(error);

        }

    };

    return (

        <Layout>

            <div className="profile-container">

                <div className="profile-card">

                    <h2>👤 My Profile</h2>

                    <div className="profile-field">

                        <label>Name</label>

                        <input
                            type="text"
                            value={profile.name}
                            onChange={(e) =>
                                setProfile({
                                    ...profile,
                                    name: e.target.value
                                })
                            }
                        />

                    </div>

                    <div className="profile-field">

                        <label>Email</label>

                        <input
                            type="email"
                            value={profile.email}
                            onChange={(e) =>
                                setProfile({
                                    ...profile,
                                    email: e.target.value
                                })
                            }
                        />

                    </div>

                    <div className="profile-buttons">

                        <button
                            className="password-btn"
                            onClick={() => setOpenPassword(true)}
                        >
                            Change Password
                        </button>

                    </div>

                </div>

            </div>

            <Modal
                isOpen={openPassword}
                onClose={() => setOpenPassword(false)}
            >

                <ChangePassword
                    close={() => setOpenPassword(false)}
                />

            </Modal>

        </Layout>

    );

}

export default Profile;