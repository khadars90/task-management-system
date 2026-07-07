import { useEffect, useState } from "react";
import {FaPlus} from "react-icons/fa";
import api from "../api/axios";
import Layout from "../components/Layout";
import TaskCard from "../components/TaskCard";
import Modal from "../components/Modal";
import TaskForm from "../components/TaskForm";
import "../styles/Tasks.css";

function Tasks() {

    const [tasks, setTasks] = useState([]);
    const [open, setOpen] = useState(false);
    const [selectedTask, setSelectedTask] = useState(null);
    const [isEditing, setIsEditing] = useState(false);
    const [search, setSearch] = useState("");
    const [statusFilter, setStatusFilter] = useState("ALL");
    const [priorityFilter, setPriorityFilter] = useState("ALL");

    useEffect(() => {

        loadTasks();

    }, []);

    const loadTasks = async () => {

        try{

            const response = await api.get("/tasks");

            setTasks(response.data);

        }

        catch(error){

            console.log(error);

        }

    };

    const addTask = async(task)=>{

        try{

            await api.post("/tasks",task);

            setOpen(false);
            loadTasks();

        }

        catch(error){

            console.log(error);

        }

    };

    const deleteTask = async(id)=>{

        try{

            await api.delete(`/tasks/${id}`);
            loadTasks();

        }

        catch(error){

            console.log(error);

        }

    };
    const updateTask = async(updatedTask)=>{

    try{

        await api.put(`/tasks/${selectedTask.id}`,

            updatedTask

        );

        setOpen(false);

        setIsEditing(false);

        setSelectedTask(null);

        loadTasks();

    }

    catch(error){

        console.log(error);

    }

};

    const editTask=(task)=>{
       
        setSelectedTask(task);
        setIsEditing(true);
        setOpen(true);


    };

    const filteredTasks = tasks.filter(task =>
    task.title.toLowerCase().includes(search.toLowerCase()) &&
    (statusFilter === "ALL" || task.status === statusFilter) &&
    (priorityFilter === "ALL" || task.priority === priorityFilter)
);

    return(

        <Layout>

            <div className="dashboard-container">

                <div
                    style={{
                        display:"flex",
                        justifyContent:"space-between",
                        alignItems:"center"
                    }}
                >

                    <h2>

                        My Tasks

                    </h2>
                    <p className="statuses">Search by Status</p>
                    <p className="priorities">Search by Priority</p>

                    <button
                    className="add-task-btn"
                        onClick={()=>setOpen(true)}
                    >
                        <FaPlus/>
                        Add New Task

                    </button>

                </div>
                <div className="search-container">
    <input
        type="text"
        className="search-input"
        placeholder="🔍 Search tasks by title..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
    />

    
    <label className="filter-label"></label>
    <select
    className="filter-select"
    value={statusFilter}
    onChange={(e) => setStatusFilter(e.target.value)}
>
    <option value="ALL">All Status</option>
    <option value="TODO">TODO</option>
    <option value="IN_PROGRESS">IN PROGRESS</option>
    <option value="DONE">DONE</option>

</select>

 
 
 <select
    className="filter-select"
    value={priorityFilter}
    onChange={(e) => setPriorityFilter(e.target.value)}
>

    <option value="ALL">All </option>
    <option value="HIGH">HIGH</option>
    <option value="MEDIUM">MEDIUM</option>
    <option value="LOW">LOW</option>

</select>
</div>


                <br/>
                <div className="tasks-grid">

                    {filteredTasks.map(task=>(

                        <TaskCard

                            key={task.id}

                            task={task}

                            onDelete={deleteTask}

                            onEdit={editTask}

                        />

                    ))} 
                </div>

                <Modal

                    isOpen={open}

                    onClose={()=>setOpen(false)}

                >

                    <TaskForm
                    initialData={selectedTask}

                        buttonText={
                            isEditing
                            ? "Update Task" : "Create Task"
                        }

                        onSubmit={
                            isEditing
                            ?
                            updateTask
                            :
                            addTask
                        }

                    />

                </Modal>

            </div>

        </Layout>

    );

}

export default Tasks;