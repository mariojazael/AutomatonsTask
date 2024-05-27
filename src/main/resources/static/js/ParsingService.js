document.getElementById('submitButton').addEventListener('click', function(event) {
    event.preventDefault(); // Evita el envío del formulario por defecto

    const dataValue = document.getElementById('data').value;
    const url = `http://localhost:8080/api/parsing/parse?data=${encodeURIComponent(dataValue)}`;

    fetch(url, {
        method: 'GET', // o 'POST', 'PUT', etc.
        headers: {
            'Content-Type': 'application/json'
            // Agrega cualquier otro encabezado necesario
        }
    })
        .then(response => response.json())
        .then(data => {
            // Convertimos los datos a una cadena JSON y la establecemos como el valor de resultBox
            document.getElementById('resultBox').value += JSON.stringify(data, null, 2) + '\n';
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('resultBox').value = 'Error: ' + error;
        });
});


