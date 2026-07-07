import "../styles/TaskCard.css";

function TaskCard({

    task,

    onDelete,

    onEdit

}) {

    const formattedDate = new Date(task.dueDate).toLocaleDateString(
        "en-GB",
        {
            day: "2-digit",
            month: "short",
            year: "numeric"
        }
    );

    return (

        <div className="task-card">

            <h3 className="task-title">
                {task.title}
            </h3>

            <p className="task-description">
                {task.description}
            </p>

            <div className="task-info">

                <span
                    className={`status ${task.status.toLowerCase()}`}
                >
                    {task.status.replace("_", " ")}
                </span>

                <span
                    className={`priority ${task.priority.toLowerCase()}`}
                >
                    {task.priority}
                </span>

            </div>

            <p className="due-date">
                📅 Due: {formattedDate}
            </p>

            {(onEdit || onDelete) && (
    <div className="task-buttons">

        {onEdit && (
            <button
                className="edit-btn"
                onClick={() => onEdit(task)}>
                Edit
            </button>
        )}

        {onDelete && (
            <button
                className="delete-btn"
                onClick={() => onDelete(task.id)}>
                Delete
            </button>
        )}
    </div>

)}

        </div>

    );

}

export default TaskCard;