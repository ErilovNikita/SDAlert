/**
* Метод для получения данных алерта *
* @return Мапа со всеми данными *
*/
Map getAlert() {
  def alertObject = utils.get('catalogs$50353202') 
  return [
    'active' : alertObject?.isActive,
    'deadline' : alertObject?.datatime,
    'content' : [
        'header' : 'Внимание',
        'text' : alertObject?.description
    ]
  ]
}