const textareas = document.querySelectorAll(".textarea"); //class textarea for everyone textareas
textareas.forEach(textarea => {
    textarea.addEventListener("input", () => {
        textarea.style.height = "auto";
        textarea.style.height = `${textarea.scrollHeight}px`;
    });
});