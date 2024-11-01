// 새 Todo 항목을 추가하는 함수
function addTodo() {
    const task = document.getElementById("taskInput").value;
    const dueDate = document.getElementById("dueDateInput").value;
    const repeatType = document.getElementById("repeatTypeInput").value;
    const completed = document.getElementById("completedInput").checked;

    const newTodo = {
        task: task,
        dueDate: dueDate,
        repeatType: repeatType,
        completed: completed
    };

    fetch("/api/todos", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(newTodo)
    })
        .then(response => response.json())
        .then(data => {
            console.log("New Todo Added:", data);
            addTodoToTable(data); // 화면에 새 Todo 항목을 추가
        })
        .catch(error => console.error("Error adding todo:", error));
}

// 새로 추가된 Todo를 테이블에 추가하는 함수
function addTodoToTable(todo) {
    const table = document.getElementById("todoTable").querySelector("tbody");
    const row = table.insertRow();
    row.innerHTML = `
        <td>${todo.id}</td>
        <td>${todo.task}</td>
        <td>${todo.dueDate}</td>
        <td>${todo.repeatType}</td>
        <td>
            <input type="checkbox" ${todo.completed ? 'checked' : ''} 
                   onchange="toggleTodoStatus(this, ${todo.id})">
        </td>
    `;
}

// Todo 항목 완료 상태를 업데이트하는 함수
function toggleTodoStatus(checkbox, todoId) {
    fetch(`/api/todos/${todoId}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ completed: checkbox.checked })
    })
        .then(response => response.json())
        .then(data => console.log("Todo status updated:", data))
        .catch(error => console.error("Error updating task status:", error));
}
