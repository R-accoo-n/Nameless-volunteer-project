const showFields = document.getElementById("show-fields");
const showForVolunteer = document.getElementById("show-for-volunteer");
const showForMilitary = document.getElementById("show-for-military");
const hidden = document.getElementById("hidden");
const hiddenForVolunteer = document.getElementById("hiddenForVolunteer");
const hiddenForMilitary = document.getElementById("hiddenForMilitary");


showFields.addEventListener("click", function() {
if(hiddenForVolunteer.style.display === "block" && hiddenForMilitary.style.display === "block"){
    hiddenForVolunteer.style.display = "none";
    hiddenForMilitary.style.display = "none";
}
else if (hiddenForMilitary.style.display === "block") {
    hiddenForMilitary.style.display = "none";
}
else if (hiddenForVolunteer.style.display === "block") {
    hiddenForVolunteer.style.display = "none";
}
hidden.style.display = "block";
});


showForVolunteer.addEventListener("click", function() {
if (hiddenForMilitary === "block") {
    hiddenForMilitary.style.display = "none";
}
hidden.style.display = "block";
hiddenForMilitary.style.display = "block";
hiddenForVolunteer.style.display = "block";
});


showForMilitary.addEventListener("click", function() {
if (hiddenForVolunteer.style.display === "block") {
    hiddenForVolunteer.style.display = "none";
}
hidden.style.display = "block";
hiddenForMilitary.style.display = "block";
});