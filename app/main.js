let manifest = {
    name : "SDAlert",
    version : "1.5.1",
    author: "Erilov Nikita"
}

console.log(`%c${manifest.name} App     \n%cby ${manifest.author}`, 'background: #ffb700; color: #df4953', 'background: #fff; color: #333');

window.parent.injectJsApi(window.parent, window);

jsApi.restCallAsJson("exec/?func=modules.alert.getAlert&params").then((data) => {
    console.log(data)
    if (data.active) {

        if ( data.content.type && ['danger', 'warn', 'info'].includes( data.content.type ) ) {
            document.getElementsByClassName('alert-card')[0].classList.remove('info')
            document.getElementsByClassName('alert-card')[0].classList.add( data.content.type )
        }

        document.getElementById('header').innerHTML = data.content.header
        document.getElementById('text').innerHTML = data.content.text
    }
})