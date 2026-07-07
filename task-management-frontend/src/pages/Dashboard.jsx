import { useEffect, useState } from "react";
import api from "../api/axios";
import "../styles/Dashboard.css";
import {useNavigate} from "react-router-dom";

import Layout from "../components/Layout";
import WelcomeBanner from "../components/WelcomeBanner";
import StatsCard from "../components/StatsCard";
import TaskCard from "../components/TaskCard";
import UpcomingDeadlines from "../components/UpcomingDeadlines";

function Dashboard() {

    const [tasks, setTasks] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        loadTasks();
    }, []);

    const loadTasks = async () => {
        try {

            const response = await api.get("/tasks");

            setTasks(response.data);

        } catch (error) {

            console.log(error);

        }
    };

    const totalTasks = tasks.length;

    const pendingTasks =
        tasks.filter(task => task.status === "TODO").length;

    const inProgressTasks =
        tasks.filter(task => task.status === "IN_PROGRESS").length;

    const completedTasks =
        tasks.filter(task => task.status === "DONE").length;

    return (

        <Layout>

            <div className="dashboard-container">

                <WelcomeBanner />

                <div className="stats-container">

                    <StatsCard
                        title="Total Tasks"
                        value={totalTasks}
                        color="#2563eb"
                    />

                    <StatsCard
                        title="Pending"
                        value={pendingTasks}
                        color="#f59e0b"
                    />

                    <StatsCard
                        title="In Progress"
                        value={inProgressTasks}
                        color="#7c3aed"
                    />

                    <StatsCard
                        title="Completed"
                        value={completedTasks}
                        color="#16a34a"
                    />

                </div>

                <div className="dashboard-content">
                <div className="recent-header">
                <h2 className="recent-title">

                    Recent Tasks

                </h2>
                <button className="view-all-btn"
                    onClick={() => navigate("/tasks")}>
                    View All →
                </button>
                </div>
                <UpcomingDeadlines tasks = {tasks}/>
                </div>

                {

                    tasks.length === 0 ?

                        <p>No Tasks Found</p>

                        :

                        tasks
                            .slice(0, 5)
                            .map(task => (

                                <TaskCard

                                    key={task.id}

                                    task={task}
                                />

                            ))

                }

            </div>

        </Layout>

    );

}

export default Dashboard;