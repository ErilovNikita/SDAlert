let manifest = {
    name : "ServiceDesk SDAlert",
    version : "1.0.0"
}

window.parent.injectJsApi(window.parent, window);

jsApi.restCallAsJson("exec/?func=modules.alert.getAlert&params").then((data) => {
    if (data.active) {
        document.getElementById('header').innerHTML = data.content.header
        document.getElementById('text').innerHTML = data.content.text
    }
})
