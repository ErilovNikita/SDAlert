package ru.ocs.alert

/*! UTF8 */

/**
 * # alert - Naumen Service Desk Package
 * Пакет для обработки обращений одноименного встроенного приложения NSD, а так же REST запросов обособленных платформ . *
 * Содержит методы, которые формируют уникальные структурированные данные *
 * @author Erilov.NA*
 * @since 2023-03-14 *
 * @version 2.0.1 *
*/

/* Зависимости */
import ru.naumen.core.server.script.api.injection.InjectApi
import groovy.transform.Field
import groovy.transform.builder.*
import groovy.json.JsonBuilder

/* Параметры */
@Field String ALERT_OBJECT = 'catalogs$50353202'

@InjectApi
@Builder
class Alert {
    String uuid
    Boolean active
    Date deadline
    AlertType type
    String header
    String text
  
  	/** enum для храненения типа alert'a */
  	enum AlertType {
      info,
      danger,
      warn
    }

  	/** enum для храненения типа возвращаемого формата */
    enum returnFormat {
      JSON,
      map
    }
  
  	/**
    * Метод для сборки *
    * @param type Строковое значение возращаемого формата (emun returnFormat) *
    * @return мапа или же JSON обьекта Alert *
    */
   	private String make(
      String type
    ) {
      	Map data = [
          'uuid' 	  : this.uuid,
          'active'    : this.active,
          'deadline'  : this.deadline,
          'content'   : [
              'type'    : this.type as String,
              'header'  : this.header,
              'text'    : this.text
          ]
        ]
      	if (returnFormat[type] as String == 'JSON') {
        	return new JsonBuilder(data).toPrettyString()
        }
      
      	if (returnFormat[type] as String == 'map') {
        	return data
        }
    }
  
  	/**
    * Метод для сборки обьекта класса *
    * @param storageObject Обьект системы NSD *
    * @return Обьект класса Event *
    */
    private Alert fromData( 
      Map storageObject
    ) {
        // Вернем собранный обьекта класса
        return Alert.builder()
            .uuid(		storageObject?.UUID as String )
            .active(	storageObject?.isActive?.code == 'yes' )
            .deadline(	storageObject?.datatime as Date )
            .type(		AlertType[ storageObject?.type?.code as String ] )
            .header(	'Внимание' as String )
            .text(		storageObject?.description as String )
            .build()
    }
}

/**
* Публичный метод для REST запросов на получение информации *
* @param alertUUID Обьект системы NSD *
* @return Обьект класса Event *
*/
def getAlert( 
  String type = 'JSON',
  Map alertUUID = utils.get(ALERT_OBJECT)
) {
  Alert alert = new Alert()
  alert = alert.fromData( alertUUID )
  return alert.make( type )
}