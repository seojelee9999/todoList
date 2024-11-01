document.addEventListener("DOMContentLoaded", function () {
    const calendar = document.getElementById("calendar");
    const today = new Date();
    const year = today.getFullYear();
    const month = today.getMonth() + 1; // 1월이 0부터 시작하므로 +1 필요
    const daysInMonth = new Date(year, month, 0).getDate();

    for (let day = 1; day <= daysInMonth; day++) {
        const dayElement = document.createElement("div");
        dayElement.classList.add("calendar-day");
        dayElement.textContent = day;

        // 두 자리로 날짜 형식을 맞추기
        const formattedMonth = month.toString().padStart(2, '0');
        const formattedDay = day.toString().padStart(2, '0');

        dayElement.addEventListener("click", function () {
            window.location.href = `/todos/${year}-${formattedMonth}-${formattedDay}`;
        });

        calendar.appendChild(dayElement);
    }
});
