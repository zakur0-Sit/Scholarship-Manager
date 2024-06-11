document.addEventListener('DOMContentLoaded', function() {
    logout();

});
function logout() {
    var jwtToken = localStorage.getItem('jwtToken');
    if (!jwtToken) {
        alert("Trebuie să fii logat pentru a te deconecta");
        return;
    }
    // Șterge tokenul JWT din localStorage
    localStorage.removeItem('jwtToken');
    // Afișează mesajul "Logout successful"
    alert("Logout successful");
    // Redirecționează către /login după o secundă
    setTimeout(function() {
        window.location.href = 'login';
    }, 1000);
}