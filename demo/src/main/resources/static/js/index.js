document.addEventListener('DOMContentLoaded', function() {
    loadIndex();
});

function loadIndex() {
    var jwtToken = localStorage.getItem('jwtToken');
    if (!jwtToken) {
        alert("Trebuie să te loghezi pentru a accesa pagina index");
        window.location.href = 'login';  // Redirecționează către pagina de login
        return;
    }
    $.ajax({
        url: '/api/index',
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