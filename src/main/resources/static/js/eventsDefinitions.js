document.getElementById('fileInput').addEventListener('change', function(event) {
    const file = event.target.files[0];
    if (file && file.type === 'text/plain') {
        const reader = new FileReader();
        reader.onload = function(e) {
            document.getElementById('fileContent').value = e.target.result;
        };
        reader.readAsText(file);
    } else {
        alert('Please select a valid .txt file.');
    }
});

document.getElementById('submitButton').addEventListener('click', function() {
    const content = document.getElementById('fileContent').value;
    const loadingSpinner = document.getElementById('loading');
    function showSpinner() {
        console.log('Showing spinner');
        loadingSpinner.style.display = 'block';
    }

    function hideSpinner() {
        console.log('Hiding spinner');
        loadingSpinner.style.display = 'none';
    }

    showSpinner();

    fetch('/api/parsing/parse', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ content: content })
    })
        .then(response => response.json())
        .then(data => {
            console.log("hl")
            document.getElementById('responseContent').value = data.result;
            hideSpinner();
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('responseContent').value = 'An error occurred';
            hideSpinner();
        });
});