const tabItems = document.querySelectorAll('.tab-item');
const tabContentItems =document.querySelectorAll('.tab-content-item');

//select tab content item
function selectItem(e){
	removeBorder();
	removeShow();
	//add border to current tab
	this.classList.add('tab-border');
	//Grab item content from the DOM
	const tabContentItem = document.querySelector(`#${this.id}-content`);
	//Add the Show Class
	tabContentItem.classList.add('show');
}

function removeBorder(){ 
	tabItems.forEach(item => item.classList.remove('tab-border')) 
}

function removeShow(){ 
	tabContentItems.forEach(item => item.classList.remove('show')) 
}

//listen for tab click
tabItems.forEach(item => item.addEventListener('click',selectItem));