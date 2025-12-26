const API_BASE = "http://localhost:8080";

const taskInput = document.getElementById("taskInput");
const addBtn = document.getElementById("addBtn");
const taskList = document.getElementById("taskList");

// Load tasks from backend when page opens
window.addEventListener("load", getTasks);

addBtn.addEventListener("click", async () => {
    const title = taskInput.value.trim();
    if (!title) return;

    await fetch(`${API_BASE}/addTask?title=${encodeURIComponent(title)}`, {
        method: "POST"
    });

    taskInput.value = "";
    getTasks();
});

async function getTasks() {
    const response = await fetch(`${API_BASE}/getTasks`);
    const tasks = await response.json();
    renderTasks(tasks);
}

function renderTasks(tasks) {
    taskList.innerHTML = "";

    tasks.forEach(task => {
        const li = document.createElement("li");
        li.className = "task-item";
        if (task.done) li.classList.add("done");

        const titleSpan = document.createElement("span");
        titleSpan.textContent = task.title;

        const btnContainer = document.createElement("div");

        const toggleBtn = document.createElement("button");
        toggleBtn.textContent = task.done ? "Undo" : "Done";
        toggleBtn.className = "action-btn";
        toggleBtn.addEventListener("click", async () => {
            await fetch(`${API_BASE}/toggle/${task.id}`, { method: "PUT" });
            getTasks();
        });

        const deleteBtn = document.createElement("button");
        deleteBtn.textContent = "Delete";
        deleteBtn.className = "action-btn";
        deleteBtn.addEventListener("click", async () => {
            await fetch(`${API_BASE}/delete/${task.id}`, { method: "DELETE" });
            getTasks();
        });

        btnContainer.appendChild(toggleBtn);
        btnContainer.appendChild(deleteBtn);

        li.appendChild(titleSpan);
        li.appendChild(btnContainer);

        taskList.appendChild(li);
    });
}
