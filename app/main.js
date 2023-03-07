let manifest = {
    name : "ServiceDesk SDAlert",
    version : "1.0.0"
}

window.parent.injectJsApi(window.parent, window);

document.addEventListener("DOMContentLoaded", () => {
    document.getElementsByClassName('app-name')[0].innerHTML = manifest.name
    document.getElementsByClassName('app-version')[0].innerHTML = "v" + manifest.version
});

function renderAlert(
    type,
    text
) {
    
}