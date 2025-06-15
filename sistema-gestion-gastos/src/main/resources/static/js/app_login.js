API_LOGIN_URL = "http://localhost:8080/api/beta-1.0/usuarios/login";
document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.has('logout')) {
        document.getElementById('logout-message').style.display = 'block';
    }

    const loginForm = document.getElementById('login-form');
    
    loginForm.addEventListener('submit', function(e) {
        e.preventDefault();
        
        const email = document.getElementById('inp_email').value;
        const contraseña = document.getElementById('inp_contrasenna').value;
        
        // Validación simple (en un sistema real sería más complejo)
        if (!email || !contraseña) {
            showError('Por favor complete todos los campos');
            return;
        }
        
        // Simular autenticación
        authenticateUser(email, contraseña);
    });
    
    function authenticateUser(email, contraseña) {
        fetch(API_LOGIN_URL, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            email: email,
            contraseña: contraseña
        })
    })  .then(response => {
            if (!response.ok) throw new Error("Respuesta no OK");
            return response.json();
        })
        .then(data => {
            console.log("DATA RECIBIDA:", data);
            if(data && data.id && data.email){
                localStorage.setItem("usuario", JSON.stringify(data));
                window.location.href = "/index.html";
            }else if (data.error){
                showError("No se pudo iniciar sesion: " + data.error);
                document.getElementById("inp_email").value = "";
                document.getElementById("inp_contrasenna").value = "";
            }else{ 
                console.log("DATA RECIBIDA:", data);
                showError("Correo o contraseña incorrectos.");
                document.getElementById("inp_email").value = "";
                document.getElementById("inp_contrasenna").value = "";
            }
        }).catch(error => {
            showError("Error al conectar con el servidor. Intenta más tarde.");
            console.error(error);
        });
    }
    
    function showError(message) {
        const errorElement = document.getElementById('login-error');
        errorElement.textContent = message;
        errorElement.style.display = 'block';
    }
});