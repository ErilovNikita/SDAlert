let manifest = {
    name : "SDAlert",
    version : "1.0.0",
    author: "Erilov Nikita"
}

console.log(`%c${manifest.name} App     \n%cby ${manifest.author}`, 'background: #ffb700; color: #df4953', 'background: #fff; color: #333');

window.parent.injectJsApi(window.parent, window);

jsApi.restCallAsJson("exec/?func=modules.alert.getAlert&params").then((data) => {
    if (data.active) {
        document.getElementById('header').innerHTML = data.content.header
        document.getElementById('text').innerHTML = data.content.text
    }
})
