const script=document.createElement("script");

script.src="https://www.google.com/recaptcha/api.js?render=6LfWBu8dAAAAAB3xb5sgr6hCWJrMvig01P7y7UZj";
script.async=!0;

document.head.appendChild(script);

function onClickValidate(data) {
    grecaptcha.ready(function() {
        grecaptcha.execute('6LfWBu8dAAAAAB3xb5sgr6hCWJrMvig01P7y7UZj', {action: 'submit'}).then(function(token) {
            document.getElementById('form:token').value = token;
            validarFormulario(data)
        });
    });
}