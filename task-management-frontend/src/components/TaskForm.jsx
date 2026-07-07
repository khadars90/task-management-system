import { useState, useEffect } from "react";
import "../styles/TaskForm.css";

function TaskForm({

    initialData,
    onSubmit,
    buttonText

}) {

    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [status, setStatus] = useState("TODO");
    const [priority, setPriority] = useState("MEDIUM");
    const [dueDate, setDueDate] = useState("");

    useEffect(() => {

        if(initialData){

            setTitle(initialData.title || "");
            setDescription(initialData.description || "");
            setStatus(initialData.status || "TODO");
            setPriority(initialData.priority || "MEDIUM");
            setDueDate(initialData.dueDate || "");

        }

    }, [initialData]);

    const handleSubmit = (e) => {

        e.preventDefault();

        onSubmit({

            title,
            description,
            status,
            priority,
            dueDate

        });

    };

    return (

        <form
            className="task-form"
            onSubmit={handleSubmit}
        >

            <input
                type="text"
                placeholder="Title"
                value={title}
                onChange={(e)=>setTitle(e.target.value)}
                required
            />

            <textarea
                placeholder="Description"
                value={description}
                onChange={(e)=>setDescription(e.target.value)}
            />

            <div className="row">

                <select
                    value={status}
                    onChange={(e)=>setStatus(e.target.value)}
                >

                    <option value="TODO">TODO</option>

                    <option value="IN_PROGRESS">
                        IN PROGRESS
                    </option>

                    <option value="DONE">
                        DONE
                    </option>

                </select>

                <select
                    value={priority}
                    onChange={(e)=>setPriority(e.target.value)}
                >
                    <option value="LOW">LOW</option>

                    <option value="MEDIUM">
                        MEDIUM
                    </option>

                    <option value="HIGH">
                        HIGH
                    </option>
                </select>
            </div>
            <input
                type="date"
                value={dueDate}
                onChange={(e)=>setDueDate(e.target.value)}
            />
            <button type="submit">
                {buttonText}
            </button>
        </form>
    );
}

export default TaskForm;