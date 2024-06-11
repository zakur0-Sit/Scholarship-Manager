document.addEventListener('DOMContentLoaded', function() {
    loadDocs();

});

function loadDocs() {
    var jwtToken = localStorage.getItem('jwtToken');
    if (!jwtToken) {
        alert("Trebuie să te loghezi pentru a accesa pagina docs");
        window.location.href = 'login';  // Redirecționează către pagina de login
        return;
    }
    $.ajax({
        url: '/api/docs',
        type: 'GET',
        beforeSend: function(xhr) {
            xhr.setRequestHeader('Authorization', 'Bearer ' + jwtToken);
        },
        success: function(data) {
            console.log("Acces permis.");
            // Afișează conținutul paginii
            document.querySelectorAll('body > *').forEach(function(element) {
                element.style.display = 'flex';
            });
        },
        error: function() {
            alert("Eroare de autentificare. Redirecționare către login.");
            localStorage.removeItem('jwtToken');
            window.location.href = 'login';
        }
    });
}