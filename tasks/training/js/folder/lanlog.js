const ids = [
    "sign-in-text", 
    "email-label",
    "email-inputError",
    "pwd-label",
    "pwd-inputError",
    "remember-me-label",
    "help",
    "fb-btn-text",
    "sign-up",
    "sign-up-link",
    "reCAPInfo",
    "learn-more",
    "reCAP1",
    "reCAP2",
    "reCAP3",
    "reCAP4",
    "reCAP5",
    "footer-header",
    "gift",
    "terms",
    "privacy"
]

const labels = Object.freeze (
    {
        'en': [
            "Sign In",
            "Email or phone number",
            "Please enter a valid email or phone number.",
            "Password",
            "Your password must contain between 4 and 60 characters.",
            "Remember me",
            "Need help?",
            "Login with Facebook",
            "New to Netflix? ",
            "Sign up now.",
            "This page is protected by Google reCAPTCHA to ensure you're not a bot. ",
            "Learn more",
            "The information collected by Google reCAPTCHA is subject to the Google ",
            "Privacy Policy",
            " and ",
            "Terms of Service",
            ", and is used for providing, maintaining, and improving the reCAPTCHA service and for general security purposes (it is not used for personalized advertising by Google).",
            "Questions? Call ",
            "Gift Card Terms",
            "Terms of Use",
            "Privacy Statement",
        ],
        'pt': [
            "Entrar",
            "Email ou número de telefone",
            "Informe um email ou número de telefone válido.",
            "Senha",
            "A senha deve ter entre 4 e 60 caracteres.",
            "Lembrar-se de mim",
            "Precisa de ajuda?",
            "Conectar com o Facebook",
            "Novo por aqui? ",
            "Assine agora.",
            "Esta página é protegida pelo Google reCAPTCHA para garantir que você não é um robô. ",
            "Saiba mais",
            "As informações recolhidas pelo Google reCAPTCHA estão sujeitas à ",
            "Política de Privacidade",
            " e ",
            "Termos de Uso",
            ", e são usadas para oferecer, manter e melhorar o serviço reCAPTCHA e por questões de segurança (não são usadas para exibir anúncios personalizados pelo Google).",
            "Dúvidas? Ligue ",
            "Termos dos Cartões pré-pagos",
            "Termos de uso",
            "Declaração de privacidade",
        ],
    }
);

const loadTexts = function (lang) {
    let text = lang === 'pt' ? labels.pt : labels.en;
    for (let i = 0; i < ids.length; i++) {
        document.getElementById(ids[i]).innerHTML = text[i];
    }
    document.getElementById('login-button').value = text[0];
    document.lang = lang;
}

const getSelectedLanguage = function () {
    let selector = document.getElementById("language-selector");
    let value = selector.options[selector.selectedIndex].value;
    return value;
}

const getBrowserLanguage = function () {
    let lang = navigator.language || navigator.userLanguage;
    lang = lang.slice(0, 2);
    return lang;
}

const setSelectedLanguage = function (lang) {
    lang = lang === 'pt' ? lang : 'en';
    document.getElementById('language-selector').value = lang;
}

const setUpLanguage = function () {
    let lang = getBrowserLanguage();
    lang = lang.slice(0, 2);
    loadTexts(lang);
    setSelectedLanguage(lang);
}