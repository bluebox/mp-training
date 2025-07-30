let dataList = [];
let allStates = {};
let allCities = {};

$(document).ready(function(){

    // Load States
    $.ajax({
        url: "http://192.168.0.73:32114/partner/get-states?countryCode=IN",
        method: "GET",
        success: function(res) {
            if(res.responseStatus === "SUCCESS") {
                allStates = JSON.parse(res.response);
                $("#state").empty().append('<option value="">Select State</option>');
                Object.keys(allStates).forEach(stateName => {
                    let code = allStates[stateName];
                    $("#state").append(`<option value="${code}">${stateName}</option>`);
                });
            }
        },
        error: function() { alert("Failed to load states."); }
    });

    // Load Cities when a state is selected
    $("#state").change(function(){
        let stateCode = $(this).val();
        if(!stateCode) {
            $("#city").empty().append('<option value="">Select City</option>');
            return;
        }
        $.ajax({
            url: `http://192.168.0.73:32114/partner/get-cities-for-state?stateCode=${stateCode}`,
            method: "GET",
            success: function(res) {
                if(res.responseStatus === "SUCCESS") {
                    allCities = JSON.parse(res.response);
                    $("#city").empty().append('<option value="">Select City</option>');
                    Object.keys(allCities).forEach(cityName => {
                        $("#city").append(`<option value="${cityName}">${cityName}</option>`);
                    });
                }
            },
            error: function() { alert("Failed to load cities."); }
        });
    });

    // Add or Update form submit
    $("#dataForm").submit(function(e){
        e.preventDefault();
        let index = $("#editIndex").val();
        let name = $("#name").val();
        let age = $("#age").val();
        let email = $("#email").val();
        let phone = $("#phone").val();
        let branch = $("input[name='branch']:checked").val();
        let languages = $("input[name='languages']:checked").map(function(){return this.value;}).get().join(", ");
        let state = $("#state option:selected").text();
        let city = $("#city option:selected").text();

        if(!branch || !languages || !state || !city || state==="Select State" || city==="Select City"){
            alert("Please fill all required fields.");
            return;
        }

        let entry = {name, age, email, phone, branch, languages, state, city};

        if(index === ""){
            dataList.push(entry);
        } else {
            dataList[index] = entry;
            $("#submitBtn").text("Add");
        }

        $("#editIndex").val("");
        this.reset();
        renderTable();
    });

    // Search filter with counter update
    $("#search").on("keyup", function(){
        let value = $(this).val().toLowerCase();
        let visibleCount = 0;
        $("#dataTable tbody tr").each(function(){
            let match = $(this).text().toLowerCase().indexOf(value) > -1;
            $(this).toggle(match);
            if(match) visibleCount++;
        });
        updateCounter(visibleCount);
    });
});

// Render table function
function renderTable(){
    let tbody = $("#dataTable tbody");
    tbody.empty();
    if(dataList.length === 0){
        tbody.append("<tr><td colspan='9'>No results found</td></tr>");
        updateCounter(0);
        return;
    }
    dataList.forEach((item,index)=>{
        tbody.append(`
            <tr>
                <td>${item.name}</td><td>${item.age}</td><td>${item.email}</td><td>${item.phone}</td>
                <td>${item.branch}</td><td>${item.languages}</td><td>${item.state}</td><td>${item.city}</td>
                <td class="actions">
                    <button onclick="editRow(${index})">Update</button>
                    <button onclick="deleteRow(${index})">Delete</button>
                </td>
            </tr>
        `);
    });
    updateCounter(dataList.length);
}

// Delete row
function deleteRow(index){
    if(confirm("Are you sure you want to delete this entry?")){
        dataList.splice(index,1);
        renderTable();
    }
}

// Edit row
function editRow(index){
    let item = dataList[index];
    $("#name").val(item.name);
    $("#age").val(item.age);
    $("#email").val(item.email);
    $("#phone").val(item.phone);
    $(`input[name='branch'][value='${item.branch}']`).prop("checked",true);

    $("input[name='languages']").prop("checked",false);
    item.languages.split(", ").forEach(lang=>{
        $(`input[name='languages'][value='${lang}']`).prop("checked",true);
    });

    $("#state option").filter(function(){
        return $(this).text() === item.state;
    }).prop("selected", true).trigger("change");

    setTimeout(()=>{
        $("#city option").filter(function(){
            return $(this).text() === item.city;
        }).prop("selected", true);
    },500);

    $("#editIndex").val(index);
    $("#submitBtn").text("Save");
}

// Update counter
function updateCounter(showing){
    $("#counter").text(`Total Records: ${dataList.length} | Showing: ${showing}`);
}
