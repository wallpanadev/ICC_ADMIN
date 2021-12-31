/*window.onload = function () {
    const v = setTimeout(removeLoad, 400);
    document.getElementById('panel-carga').style.opacity = '0';
};*/

/**============== DECLARACIONES ==============*/
//--General
var toastLive = document.getElementById('toast-msg');
var toast;
if (toastLive) {
    toast = new bootstrap.Toast(toastLive);
}

var timer;
var segundos = 0;
var tiempoEspera = 15;

//--Boleteria
var listTarifaInput = document.querySelectorAll('.tarifa');
var maxPasajero = document.getElementById('max-pasajeros');

//--Compra
var totalTarifa = document.getElementById('form:total-tarifa');

/**============== GENERAL ==============*/
function mostrarToast(data) {
    if (data.status === 'success') {
        const valToast = document.getElementById('form:show-toast');
        if (valToast && valToast.value === 'true') {
            toast.show();
            valToast.value = 'false';
        }
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
function validarFormulario(data) {
    loading(data);
    let validateFields = document.querySelectorAll('.validate');
    validateFields.forEach(vF => {
        validarCampo(vF);
    });
    if (data) {
        mostrarToast(data);
    }
}

//-- Validar si existe el dato en el datalist
function buscarValorDataList(input) {
    let id = input.id.includes("form") ? input.id.split(":")[1] : input.id;

    const autocomplete = document.getElementById("datalist-" + id);
    const dataListOption = autocomplete.children;
    if (dataListOption) {
        for (let i = 0; i < dataListOption.length; i++) {
            if (dataListOption[i].textContent === input.value) {
                document.getElementById("form:hidden-" + id).value = dataListOption[i].dataset.value;
                return;
            }
        }
    }
    document.getElementById("form:hidden-" + id).value = null;
    input.value = null;
}

//--Spinner
function loading(data) {
    switch (data.status) {
        case 'begin':
            document.getElementById('loading').style.display = 'block';
            break;
        case 'complete':
            document.getElementById('loading').style.display = 'none';
            break;
    }
}

//--Page load
function removeLoad() {
    document.getElementById('panel-carga').style.display = 'none';
}

function showLoader() {
    document.getElementById('loading').style.display = 'block';
    const v = setInterval(function () {
        document.getElementById('loading').style.display = 'none';
    }, 2000);
}

/**============== BOLETERIA ==============*/
var ingresoFecha;
function validarCampoFecha(data) {
    let status = data.status;
    let element = data.source;

    switch (status) {
        case "begin":
            ingresoFecha = element.value !== null && element.value !== '';
            break;
        case "success":
            //-- Controlar que primero ingrese la fecha de ida
            if (element.id.includes("Regreso")) {
                const txtFechaIda = document.getElementById("form:txtFechaIda");
                if (txtFechaIda.value === null || txtFechaIda.value === '') {
                    txtFechaIda.focus();
                    toast.show();
                }
            }
            //-- Mostrar error de fecha minima o maxima
            if (ingresoFecha && (element.value === null || element.value === '')) {
                toast.show();
            }

            validarCampo(element);
            break;
    }
}

function validarCiudadDestino(data) {
    if (data.status === "success") {
        const txtCiudadOrigen = document.getElementById("form:txtCiudadOrigen");
        if (txtCiudadOrigen.value === null || txtCiudadOrigen.value === '') {
            txtCiudadOrigen.focus();
            toast.show();
        }
    }
}

//-- Valida fecha regreso
function validarFormularioInicio(data) {
    if (data.status === 'success') {
        const txtFechaRegreso = document.getElementById("form:txtFechaRegreso");
        if (txtFechaRegreso) {
            if (txtFechaRegreso.disabled) {
                txtFechaRegreso.classList.remove("validate")
            } else {
                txtFechaRegreso.classList.add("validate")
            }
            validarFormulario(data);
        }
        mostrarToast(data);
    }
}

//-- Controla total de pasajeros
function controlarNumPasajero(button) {
    button.blur();
    let totalPasajero = 0;

    listTarifaInput.forEach(tarifa => {
        totalPasajero += parseInt(tarifa.value);
    })
    contarPasajero(button, totalPasajero);
}

//-- Controla suma/resta de pasajeros
function contarPasajero(button, totalPasajero) {
    const txtNumPasajero = button.nextElementSibling || button.previousElementSibling;

    let numPasajero = parseInt(txtNumPasajero.value);
    let minPasajero = button.name.includes("0") ? 1 : 0;

    if (button.name.includes("Plus") && totalPasajero < parseInt(maxPasajero.value)) {
        numPasajero++;
        if (minPasajero === 0 && numPasajero >= 1) {
            const alert = document.getElementById('alertMenor');
            alert.classList.remove('d-none');
            alert.classList.add('d-flex');
        }
    } else if (button.name.includes("Sub") && totalPasajero > 1 && numPasajero > minPasajero) {
        numPasajero--;
        if (minPasajero === 0 && numPasajero === 0) {
            const alert = document.getElementById('alertMenor');
            alert.classList.remove('d-flex');
            alert.classList.add('d-none');
        }
    } else {
        if (numPasajero === 1 && minPasajero === 1) {
            alert("Un adulto debe realizar la compra");
        } else {
            alert("Límite Pasajeros");
        }
    }
    txtNumPasajero.value = "" + numPasajero;
}

function activarBoleto(link) {
    document.querySelectorAll('.boleto-link').forEach(b => {
        b.classList.remove('active');
    })
    link.classList.add('active')
    controlarAcordeon();
}

function controlarAcordeon() {
    let  btnAc = document.getElementById('flush-headingOne');
    const  divAc = document.getElementById('flush-collapseOne');
    if (btnAc) {
        btnAc = btnAc.firstElementChild;
        btnAc.classList.add('collapsed');
        btnAc.setAttribute('aria-expanded', 'false');
        divAc.classList.remove('show');
    }
}

function loadingCiudadDestino(data) {
    loading(data);
    switch (data.status) {
        case 'begin':
            document.getElementById('form:txtCiudadDestino').disabled = true;
            break;
        case 'complete':
            document.getElementById('form:txtCiudadDestino').disabled = false;
            break;
    }
}

/**============== COMPRA ==============*/
//-- Cambio de los estado del asiento libre/seleccionado
function controlarAsiento(data) {
    loading(data);
    switch (data.status) {
        case 'begin':
            const checkInput = data.source;
            if (!checkInput.disabled) {
                let totT = parseInt(totalTarifa.value)
                let selectAsiento = 0;
                document.querySelectorAll('.asiento-check').forEach(asiento => {
                    if (asiento.checked && !asiento.disabled) {
                        selectAsiento++;
                    }
                });
                if (selectAsiento > totT) {
                    toast.show();
                }
            }
            break;
        case 'success':
            if (document.getElementById('form:asiento').checked) {
                toast.show();
            }
            break;
    }
}

function mostrarToastAjax(data) {
    if (data.status === 'success') {
        toast.show();
    }
}

function deshabilitarRecarga(data) {
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