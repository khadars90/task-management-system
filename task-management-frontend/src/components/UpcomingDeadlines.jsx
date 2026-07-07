import "../styles/UpcomingDeadlines.css";

function UpcomingDeadlines({ tasks }) {

    const upcomingTasks = [...tasks]
        .filter(task => task.status !== "DONE")
        .sort(
            (a, b) =>
                new Date(a.dueDate) - new Date(b.dueDate)
        )
        .slice(0, 4);

    const getDueText = (dueDate) => {

        const today = new Date();

        today.setHours(0, 0, 0, 0);

        const due = new Date(dueDate);

        due.setHours(0, 0, 0, 0);

        const difference =
            Math.ceil((due - today) / (1000 * 60 * 60 * 24));

        if (difference === 0) {
            return {
                text: "Today",
                className: "today"
            };
        }

        if (difference === 1) {
            return {
                text: "Tomorrow",
                className: "tomorrow"
            };
        }

        if (difference > 1) {
            return {
                text: `In ${difference} Days`,
                className: "upcoming"
            };
        }

        return {
            text: "Overdue",
            className: "overdue"
        };
    };

    return (

        <div className="deadlines-card">

            <h3>Upcoming Deadlines</h3>

            {

                upcomingTasks.length === 0 ?

                    <p>No Upcoming Tasks 🎉</p>

                    :

                    upcomingTasks.map(task => {

                        const due = getDueText(task.dueDate);

                        return (

                            <div
                                key={task.id}
                                className="deadline-item"
                            >

                                <div>

                                    <strong>

                                        {task.title}

                                    </strong>

                                    <br />

                                    <small>

                                        {task.priority}

                                    </small>

                                </div>

                                <span
                                    className={due.className}
                                >

                                    {due.text}

                                </span>

                            </div>

                        );

                    })

            }

        </div>

    );

}

export default UpcomingDeadlines;