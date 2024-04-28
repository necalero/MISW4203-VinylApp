# MISW4203-VinylApp

### Instalación
1. Abrir Android Studio
2. Elegir la opción File > New > Project from Version Control...
![Captura de pantalla 2024-04-28 a la(s) 11 59 41](https://github.com/necalero/MISW4203-VinylApp/assets/142345624/a33b3f8b-e3de-4259-bbca-e280d9268719)
4. Poner la URL de este repositorio y dar clic en Clone
![Captura de pantalla 2024-04-28 a la(s) 12 01 23](https://github.com/necalero/MISW4203-VinylApp/assets/142345624/adb500d9-3de1-4021-b113-ea5a911292dd)
5. Al cargar nos pedirá construir el Gradle. Podemos darle clic en el mensaje que sale o ir a Build y hacerlo manualmente.
![Captura de pantalla 2024-04-28 a la(s) 12 06 06](https://github.com/necalero/MISW4203-VinylApp/assets/142345624/ccfe421d-812e-462b-97af-a0b56a282a4a)
6. Tener un dispositivo Android Vinculado
![Captura de pantalla 2024-04-28 a la(s) 12 08 40](https://github.com/necalero/MISW4203-VinylApp/assets/142345624/d1464e52-196d-45bf-8095-d542ac233a97)
7. Dar clic en Run
![Captura de pantalla 2024-04-28 a la(s) 12 09 24](https://github.com/necalero/MISW4203-VinylApp/assets/142345624/cd803b9a-796e-453c-ba8c-69f79570f24a)
8. Ver la app ejecutándose (ejemplo a continuación)

![Captura de pantalla 2024-04-28 a la(s) 12 10 19](https://github.com/necalero/MISW4203-VinylApp/assets/142345624/0b366f72-3e74-4a72-a7ca-cc3afd50e605)

9. Dirigirse al botón Artistas y ver el listado de Artistas. Al elegir un artista y darle clic sobre su nombre se mostrará el detalle de el artista seleccionado.

### Pruebas
1. Ir al archivo TestArtists
![Captura de pantalla 2024-04-28 a la(s) 12 41 24](https://github.com/necalero/MISW4203-VinylApp/assets/142345624/e4f3e29a-8061-4d3e-8ffe-43ffe8143cd6)
2. Es posible que salga en rojo el paquete contrib. Tocar darle clic a contrib y hacer la sugerencia que arroja Android Studio de implementación de dependencias
![Captura de pantalla 2024-04-28 a la(s) 12 40 09](https://github.com/necalero/MISW4203-VinylApp/assets/142345624/b980934c-efdd-4b16-958a-bea81d0741c3)
3. Correr las pruebas (Run 'TestArtists')

![Captura de pantalla 2024-04-28 a la(s) 12 43 52](https://github.com/necalero/MISW4203-VinylApp/assets/142345624/c0a271a9-1d24-48bc-9aa4-332abb7c60b5)

4. Ver resultado de las pruebas (todas deben salir exitosas)
![Captura de pantalla 2024-04-28 a la(s) 12 45 42](https://github.com/necalero/MISW4203-VinylApp/assets/142345624/809a3800-419e-42f8-b38a-8cd369b95a84)

5. (Opcional) En la línea 30 del archivo TestArtists.java se le puede cambiar el nombre del artista que se quiere probar.

![Captura de pantalla 2024-04-28 a la(s) 12 47 08](https://github.com/necalero/MISW4203-VinylApp/assets/142345624/c88dea9e-b4a6-446b-ba3e-bd865c7be4c9)

> Nota: Se recomienda desactivar las animaciones del celular donde se realizan las pruebas para que estas sean más efectivas. Siga los pasos del siguiente enlace [How to disable interface animations in Android 10](https://mcmw.abilitynet.org.uk/how-disable-interface-animations-android-10) para realizar esta desactivación.
