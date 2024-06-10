document.addEventListener("DOMContentLoaded", function() {
    fetch('/api/greeting')
        .then(response => response.text())
        .then(data => {
            document.getElementById('greeting').textContent = data;
        })
        .catch(error => console.error('Error:', error));
});
