function showSemester(semester) {
    const rows = document.querySelectorAll('tr[data-semester]');
    rows.forEach(row => {
        row.style.display = 'none';
    });

    const selectedRows = document.querySelectorAll(`tr[data-semester='${semester}']`);
    selectedRows.forEach(row => {
        row.style.display = '';
    });
}

document.addEventListener('DOMContentLoaded', function() {
    showSemester(1);
    loadProfile();
});

function loadProfile() {
    console.log("in profile..")
    var jwtToken = localStorage.getItem('jwtToken');
    console.log("dupa token..")
    if (!jwtToken) {
        alert("Trebuie să te loghezi pentru a accesa pagina profile");
        window.location.href = 'login';  // Redirecționează către pagina de login
        return;
    }
    console.log("dupa if")
    $.ajax({
        url: '/aapi/api/profile',
        type: 'GET',
        beforeSend: function(xhr) {
            console.log("inainte de authoriz")
            xhr.setRequestHeader('Authorization', 'Bearer ' + jwtToken);
            console.log("dupa auth")
        },
        success: function(data) {
            console.log("Acces permis.");
            // Afișează conținutul paginii
            document.querySelectorAll('body > *').forEach(function(element) {
                element.style.display = 'flex';
            });
            // Populează datele profilului
            $('#fullName').text(data.fullName);
            $('#id').text(data.id);
            $('#email').text(data.email);
            $('#phoneNumber').text(data.phoneNumber);
            $('#dateOfBirth').text(data.dateOfBirth);
            $('#country').text(data.country);
            $('#address').text(data.address);
            $('#year').text(data.year);
            $('#group').text(data.group);

            // Populează notele
            var gradesTable = $('#gradesTable');
            gradesTable.empty();

            if (data && data.grades && Array.isArray(data.grades)) {
                // Parcurgem fiecare notă și construim rândurile tabelului
                data.grades.forEach(function(grade) {
                    // Construim un rând pentru fiecare notă
                    var row = '<tr data-semester="' + grade.semester + '">' +
                        '<td>' + grade.semester + '</td>' +
                        '<td>' + grade.subjectName + '</td>' +
                        '<td>' + grade.grade + '</td>' +
                        '<td>' + grade.credits + '</td>' +
                        '</tr>';
                    // Adăugăm rândul la tabel
                    gradesTable.append(row);
                });
            } else {
                // Dacă nu există date de afișat, putem afișa un mesaj corespunzător
                var errorMessage = '<tr><td colspan="4">Nu există note disponibile.</td></tr>';
                gradesTable.append(errorMessage);
            }

        },
        error: function() {
            alert("Eroare de autentificare. Redirecționare către login.");
            localStorage.removeItem('jwtToken');
            window.location.href = 'login';
        }
    });
}