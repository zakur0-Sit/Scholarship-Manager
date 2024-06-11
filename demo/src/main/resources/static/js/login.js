function login() {
    var username = $('#username').val();
    var password = $('#password').val();
    console.log("Trimitere cerere de autentificare...");
    $.ajax({
        url: '/login',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify({username: username, password: password}),
        success: function(response) {
            localStorage.setItem('jwtToken', response.token);
            console.log("Autentificare reușită. Token JWT salvat în localStorage:", response.token);
            $('#successMessage').show();
            window.location.href = 'index';
        },
        error: function() {
            console.log("Eroare la autentificare.");
            $('#errorMessage').show();
        }

    })

}
console.log("La final");