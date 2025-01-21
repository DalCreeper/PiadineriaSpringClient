document.addEventListener("DOMContentLoaded", function () {
	const homeForm = document.querySelector("form");
	const viewButton = document.getElementById("view-btn");
	
	homeForm.addEventListener("submit", function() {
	  overlay.style.display = "flex";
	  viewButton.disabled = true;
	});
});