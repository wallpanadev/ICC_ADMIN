window.addEventListener('DOMContentLoaded', event => {
    // Toggle the side navigation
    const sidebarToggle = document.body.querySelector('#sidebarToggle');
    if (sidebarToggle) {
        sidebarToggle.addEventListener('click', event => {
            event.preventDefault();
            document.body.classList.toggle('sb-sidenav-toggled');
            localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
        });
    }
});

/**============== DECLARACIONES ==============*/
//--General
var toastLive = document.getElementById('toast-msg');
var toast;
if (toastLive) {
    toast = new bootstrap.Toast(toastLive);
}

var modal = document.getElementById('modalTabla');
var myModal;
if (modal) {
    myModal = new bootstrap.Modal(modal);
}

var timer;
var segundos = 0;
var tiempoEspera = 15;

/**============== GENERAL ==============*/
function validarFormulario(data) {
    loading(data);
    if (data.status === 'success') {
        let validateFields = document.querySelectorAll('.validate');
        validateFields.forEach(vF => {
            validarCampo(vF);
        });
    }
}

function validarCampo(element) {
    if (element.value == null || element.value.trim().length === 0) {
        element.classList.remove('is-valid');
        element.classList.add('is-invalid');
    } else {
        element.classList.remove('is-invalid');
        element.classList.add('is-valid');
    }
}

function mostrarToastAjax(data) {
    loading(data);
    if (data.status === 'success') {
        toast.show();
    }
}

function deshabilitarRecarga(data) {
    loading(data);
    if (data.status === 'success') {
        let button = data.source;
        if (segundos === 0) {
            button.classList.add("disabled");
            timer = setInterval(function () {
                segundos++;
                if (segundos === tiempoEspera) {
                    button.classList.remove("disabled");
                    segundos = 0;
                    clearInterval(timer);
                }
            }, 1000);
        }
        toast.show();
    }
}

function validarModal(data) {
    if (data.status === 'success') {
        myModal.hide();
        toast.show();
    }
}

function loading(data) {
    switch (data.status) {
        case 'begin':
            document.getElementById('loading').style.display = 'block';
            break;
        case 'success':
            document.getElementById('loading').style.display = 'none';
            break;
    }
}

function showLoader() {
    document.getElementById('loading').style.display = 'block';
    const v = setInterval(function () {
        document.getElementById('loading').style.display = 'none';
    }, 4000);
}

function onSubmit(token) {
    document.getElementById("demo-form").submit();
}