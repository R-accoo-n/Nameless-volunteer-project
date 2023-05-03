const editButton = document.getElementById("edit-button");
const endEditButton = document.getElementById("end-edit-button");
const dataField = document.getElementById("data-about-user");
const editDataField = document.getElementById("edit-data-about-user");

editDataField.style.display = "none";
dataField.style.display = "block";


endEditButton.addEventListener("click", function() {
    if (editDataField.style.display === "block") {
        editDataField.style.display = "none";
    }
    dataField.style.display = "block";
});
     
editButton.addEventListener("click", function() {
    if (dataField.style.display  === "block") {
        dataField.style.display = "none";
    }
    editDataField.style.display = "block";
});

const containerForSocials = document.getElementById('container-for-socials')
// Додавання обробників подій на елементи
containerForSocials.addEventListener('click', function(event) {
    // Якщо була натиснута кнопка "+"
    if (event.target.classList.contains('add-another-social-network') && event.target.textContent === '+') {
        // Створення нового елементу div з кнопкою "+" та елементом p
        const newItem = document.createElement('div');
        newItem.classList.add('item-add-socials');
        newItem.innerHTML = `
            <select class="item-socials" id="Social-Networks" name="Social-Networks">
                <option value="Twitter">Twitter</option>
                <option value="Facebook">Facebook</option>
                <option value="Telegram">Telegram</option>
                <option value="Instagram">Instagram</option>
            </select>
            <input class="one-Of-The-Social-Networks" type="text" placeholder="Посилання на соціальну мережу">
            <button class="add-another-social-network" >+</button>
        `;

        // Додавання нового елементу до контейнера

        containerForSocials.insertBefore(newItem, event.target.parentNode.nextSibling);

        // Зміна кнопки "+" на кнопку "-"
        event.target.textContent = '-';
    }
  // Якщо була натиснута кнопка "-"
    else if (event.target.classList.contains('add-another-social-network') && event.target.textContent === '-') {
        // Видалення відповідного елементу div
        event.target.parentNode.remove();
  }
});

