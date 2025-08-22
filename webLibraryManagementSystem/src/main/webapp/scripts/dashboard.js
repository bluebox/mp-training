function toggleSubMenu(id, element) {
	const submenu = document.getElementById(id);
	const arrow = element.querySelector(".arrow");

	const allSubmenus = document.querySelectorAll(".submenu");
	const allArrows = document.querySelectorAll(".arrow");

	allSubmenus.forEach(menu => {
		if (menu.id !== id) menu.style.display = "none";
	});

	allArrows.forEach(a => a.textContent = "▶");

	if (submenu.style.display === "block") {
		submenu.style.display = "none";
		if (arrow) arrow.textContent = "▶";
	} else {
		submenu.style.display = "block";
		if (arrow) arrow.textContent = "▼";
	}
}