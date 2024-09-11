# ENERGY APP
![image](https://github.com/user-attachments/assets/78de51e3-44d8-48bd-a8dd-117bff42959a)

## Diseño de interfaces

https://www.figma.com/design/ZWvonuB7NAfSfwqp45QQhY/Proyecto-energyAPP?node-id=54795-26389&node-type=canvas&t=wLXjzsa5r94p3JdC-0

## Manual

[Manual.docx.pdf](https://github.com/user-attachments/files/16946847/Manual.docx.pdf)
## Diagramas

[DIAGRAMAS ENERGYAPP.pdf](https://github.com/user-attachments/files/16946500/DIAGRAMAS.ENERGYAPP.pdf)

## Conectividad Bluetooth con ESP32

La funcionalidad, empieza desde el codigo del esp32 mismo, en nuestro caso usamos el protocolo de activación de Bluetooth, 
incluyendo al código BluetoothSerial.h, desde el setup del codigo inicializamos el metodo begin de la libreria con el 
nombre del dispositivo, este viene definido por el modelo, tiende a ser "ESP32-BT-Slave", aqui esta un ejemplo practico 
del codigo usado en el IDE de arduino:
```
#include "BluetoothSerial.h"

// Configuración del sensor de corriente
float SENSIBILITY = 0.100; // Sensibilidad para el modelo 20A
int SAMPLESNUMBER = 100; // Número de muestras para la media
const int ADC_PIN = 33; // Pin ADC para la lectura del sensor

// Configuración del Bluetooth
String device_name = "ESP32-BT-Slave";
BluetoothSerial SerialBT;

#if !defined(CONFIG_BT_ENABLED) || !defined(CONFIG_BLUEDROID_ENABLED)
#error el Bluetooth está desactivado, inicialice el setup
#endif

#if !defined(CONFIG_BT_SPP_ENABLED)
#error el puerto del dispositivo está inactivo en este momento
#endif

void setup() 
{
  Serial.begin(9600);
  SerialBT.begin(device_name);
  Serial.printf("El dispositivo con nombre \"%s\" se ha iniciado.\n¡Ahora puedes emparejarlo con Bluetooth!\n", device_name.c_str());
}

void printMeasure(String prefix, float value, String postfix)
{
  Serial.print(prefix);
  Serial.print(value, 3);
  Serial.println(postfix);

  // Enviar los datos también por Bluetooth
  SerialBT.print(prefix);
  SerialBT.print(value, 3);
  SerialBT.println(postfix);
}

void loop()
{
  float current = getCorriente(SAMPLESNUMBER);
  printMeasure("Intensidad: ", current, "mA");
  delay(1000);

  // Transmisión bidireccional de datos (opcional)
  if (Serial.available()) {
    SerialBT.write(Serial.read());
  }
  if (SerialBT.available()) {
    Serial.write(SerialBT.read());
  }
  delay(20);
}

float getCorriente(int samplesNumber)
{
  float voltage;
  float corrienteSum = 0;
  for (int i = 0; i < samplesNumber; i++)
  {
    voltage = analogRead(ADC_PIN) * 3.3 / 4095.0;
    corrienteSum += (voltage - 2.23) / SENSIBILITY; // Ajusta el offset si es necesario
  }
  return (corrienteSum / samplesNumber);
}
```

Para poder usarlo correctamente desde android studio hace falta dotar de los permisos Bluetooth a nuestra aplicación en el manifiesto, de esta manera


![image](https://github.com/user-attachments/assets/b6ef82a4-e432-4eee-9299-98720cc75b50)


Para mas detalles sobre la inicialización y el uso del Bluetooth en la aplicación diríjase a BluetoothService.java en el paquete de **ModelController**

## Detalles a tener en cuenta sobre las plantillas XML

En las ultimas versiones de android studio es complicado encontrar videos o documentación que no esté obsoleta, por tanto es recomendable buscar o usar directamente 
ejemplos de kotlin para el Front-end de la aplicación ya que la documentación y posibilidades del mismo son mas amplias, ojo me refiero solo al Front-end, en caso de
que se implemente el uso de las plantillas XML este proyecto será de su interés. Puede ver ejemplos de como renderizar componentes de android studio muy personalizados 
en el paquete **RES** de esta misma. Otro punto a recalcar es que cuando se usan plantillas XML en conjunto con vistas de Java es algo mas complejo el uso de componentes
que consuman información de una consulta a la base de datos, puede ver ejemplos de como renderizar tablas o textview con datos de consultas de manera correcta en el paquete **Views**
de esta aplicación allí se ejemplifica como se usa correctamente el inflate para renderizar tablas, dialog tanto personalizados como de manera simple, como podrá ver en la siguiente sección.


## Algunas imagenes de la aplicación:

![8a274c42-4fa6-4530-98b8-f942c4aab668](https://github.com/user-attachments/assets/cca0291a-8484-4b97-a6d6-aade8f8f60aa)
![0f4e3be7-fbac-487f-80dd-c5698d7d8ce8](https://github.com/user-attachments/assets/a466f107-5ad0-4853-bbc0-a1819909678c)
![d6bd93d9-33fa-46dc-b041-0a4046157cf7](https://github.com/user-attachments/assets/573c6671-3e7e-4828-9bd6-880fe921fbde)
![2f32afe3-72bb-48ef-9bef-3f3220a65869](https://github.com/user-attachments/assets/677daada-c75b-4bb7-b412-e5ece8bcb37a)
![10a61cba-dc0e-44fb-a5ee-5e6828e62631](https://github.com/user-attachments/assets/6c110455-5bf9-49b0-b48e-e0750785048a)
![d177f8c9-d1ba-4cc5-9a94-1791a7bb2de6](https://github.com/user-attachments/assets/80c3a921-56a0-4379-96f6-f3e876cf7ea9)
![b1ba439c-3dc8-42bf-af19-2f1caa4524c5](https://github.com/user-attachments/assets/a8de067b-cbc1-495c-9775-96af0072083f)
![53d5f772-da04-4555-8c0c-114e2d0a54e3](https://github.com/user-attachments/assets/1def81eb-4cf2-48df-bdc4-9d60be7d8c3d)
![024c8465-ff76-4346-99ad-dd5548db0c08](https://github.com/user-attachments/assets/0dc46110-b750-46b2-bacc-dd2243997b35)




