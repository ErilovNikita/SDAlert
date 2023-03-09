import groovy.json.JsonBuilder

/**
* Метод для получения данных алерта *
* @return JSON со всеми данными *
*/
String getAlert() {
  def alertObject = utils.get('catalogs$50353202') 
  Map data = [
    'active'    : alertObject?.isActive as Boolean,
    'deadline'  : alertObject?.datatime as Date,
    'content'   : [
        'header'  : 'Внимание',
        'text'    : alertObject?.description as String
    ]
  ]
  return new JsonBuilder(data).toPrettyString()
}