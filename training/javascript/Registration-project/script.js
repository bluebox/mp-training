const API_KEY = "RzRLZGVseDhBMDd5bm9jOE8wNXk5elFjN1NGcVUzSnZNalRqRWNyTA==";
const countryCode = "IN";

let stateCodeNameMap = {};
let studentsList = [];

class Student {
    constructor(name, age, phone, email, branch, languages, state, city) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.branch = branch;
        this.languages = languages;
        this.state = state;
        this.city = city;
    }
}

$(document).ready(function () {
    getStates();
    initializeTables();
    addDefaultRow();

    $('#states').on('change', function () {
        const stateCode = $(this).val();
        if (stateCode) {
            getCities(stateCode);
        } else {
            $('#cities').html('<option value="">Select a city</option>');
        }
    });

    $('#regForm').on('submit', function (e) {
        e.preventDefault();
        if (validateForm()) {
            addToTable();
            this.reset();
            $('#cities').html('<option value="">Select a city</option>');
            searchTable();
        }
    });

    $('#searchTableArea').on('click', '.delete-btn', function () {
        if (confirm('Are you sure to delete?')) {
            const index = $(this).closest('tr').data('index');
            studentsList.splice(index, 1);
            searchTable();
        }
    });

    $('#searchTableArea').on('click', '.update-btn', function () {
        const index = $(this).closest('tr').data('index');
        const student = studentsList[index];
        $('[name="name"]').val(student.name);
        $('[name="age"]').val(student.age);
        $('[name="phoneNumber"]').val(student.phone);
        $('[name="email"]').val(student.email);
        $('[name="branch"]').prop('checked', false);
        $(`[name="branch"][value="${student.branch}"]`).prop('checked', true);
        $('[name="languages"]').prop('checked', false);
        student.languages.split(',').map(lang => lang.trim()).forEach(lang => {
            $(`[name="languages"][value="${lang}"]`).prop('checked', true);
        });

        const stateOption = $(`#states option`).filter(function () { return $(this).text() === student.state; });
        if (stateOption.length > 0) {
            $('#states').val(stateOption.val()).trigger('change');
            setTimeout(function () {
                $('#cities').val(student.city);
            }, 500);
        } else {
            $('#states').val('');
            $('#cities').val('');
        }
        studentsList.splice(index, 1);
        searchTable();
        alert('Details moved to form. You can update now.');
    });

    $('#searchBtn').on('click', function () {
        searchTable();
    });

    $('#clearSearchBtn').on('click', function () {
        $('#searchBox').val('');
        searchTable();
    });

    $('#searchBox').on('input', function () {
        searchTable();
    });
});

function initializeTables() {
    $('#searchTable').append('<tr id="searchNoItemsRow"><td colspan="9" style="text-align:center;color:#666">No students registered yet</td></tr>');
}

function addDefaultRow() {
    const student = new Student("Kaushik", 22, 9441372718, "suryakaushik2003@gmail.com", "CSE", "English, Hindi, Telugu", "Andhra Pradesh", "Hyderabad");
    studentsList.push(student);
    searchTable();
}

function validateForm() {
    const name = $('[name="name"]').val().trim();
    if (!name) return alert("Enter name") || false;

    const age = parseInt($('[name="age"]').val(), 10);
    if (!age || age <= 0) return alert("Enter correct age") || false;

    const phone = $('[name="phoneNumber"]').val().trim();
    if (!/^\d{10}$/.test(phone)) return alert("Enter valid 10-digit phone number") || false;

    const email = $('[name="email"]').val().trim();
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!email || !emailPattern.test(email)) return alert("Enter valid email") || false;

    if ($('[name="branch"]:checked').length === 0) return alert("Select a branch") || false;
    if ($('[name="languages"]:checked').length === 0) return alert("Select a language") || false;
    if ($('#states').val() === "") return alert("Select state") || false;
    if ($('#cities').val() === "") return alert("Select city") || false;

    return true;
}

function addToTable() {
    const student = new Student(
        $('[name="name"]').val(),
        $('[name="age"]').val(),
        $('[name="phoneNumber"]').val(),
        $('[name="email"]').val(),
        $('[name="branch"]:checked').val(),
        $('[name="languages"]:checked').map(function () { return this.value; }).get().join(", "),
        stateCodeNameMap[$('#states').val()] || $('#states').val(),
        $('#cities').val()
    );
    studentsList.push(student);
}

function searchTable() {
    const criteria = $('#searchCriteria').val();
    const searchValue = $('#searchBox').val().toLowerCase().trim();

    $('#searchTable').find("tr:gt(0)").remove();
    $('#searchNoItemsRow').remove();

    if (studentsList.length === 0) {
        $('#searchTable').append('<tr id="searchNoItemsRow"><td colspan="9" style="text-align:center;color:#dc3545">No students registered</td></tr>');
        return;
    }

    const results = searchValue === '' ?
        studentsList.map((s, i) => ({ student: s, index: i })) :
        studentsList.map((s, i) => ({ student: s, index: i })).filter(({ student }) => {
            let val = criteria === 'phone' ? student.phone : student[criteria];
            return val.toString().toLowerCase().includes(searchValue);
        });

    if (results.length === 0) {
        $('#searchTable').append('<tr id="searchNoItemsRow"><td colspan="9" style="text-align:center;color:#dc3545">No matching students</td></tr>');
    } else {
        results.forEach(({ student, index }) => {
            const row = `
                <tr data-index="${index}">
                    <td>${student.name}</td>
                    <td>${student.age}</td>
                    <td>${student.phone}</td>
                    <td>${student.email}</td>
                    <td>${student.branch}</td>
                    <td>${student.languages}</td>
                    <td>${student.state}</td>
                    <td>${student.city}</td>
                    <td>
                        <div class="action-area">
                            <button class="update-btn">Update</button>
                            <button class="delete-btn">Delete</button>
                        </div>
                    </td>
                </tr>`;
            $('#searchTable').append(row);
        });
    }
}

function getStates() {
    $.ajax({
        url: `https://api.countrystatecity.in/v1/countries/${countryCode}/states`,
        method: 'GET',
        headers: { "X-CSCAPI-KEY": API_KEY },
        success: function (data) {
            $('#states').html('<option value="">Select a state</option>').prop('disabled', false);
            data.sort((a, b) => a.name.localeCompare(b.name));
            stateCodeNameMap = {};
            data.forEach(state => {
                stateCodeNameMap[state.iso2] = state.name;
                $('#states').append(`<option value="${state.iso2}">${state.name}</option>`);
            });
        },
        error: function () {
            $('#states').html('<option value="">Error loading states</option>');
            alert("Failed to load states.");
        }
    });
}

function getCities(stateCode) {
    $.ajax({
        url: `https://api.countrystatecity.in/v1/countries/${countryCode}/states/${stateCode}/cities`,
        method: 'GET',
        headers: { "X-CSCAPI-KEY": API_KEY },
        success: function (data) {
            $('#cities').html('<option value="">Select a city</option>').prop('disabled', false);
            data.sort((a, b) => a.name.localeCompare(b.name));
            data.forEach(city => $('#cities').append(`<option value="${city.name}">${city.name}</option>`));
        },
        error: function () {
            $('#cities').html('<option value="">Error loading cities</option>');
            alert("Failed to load cities.");
        }
    });
}
