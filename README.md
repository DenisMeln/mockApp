# mockApp
Заглушка на java, реализованная с помощью Spring Boot и Maven.
Заглушка реализует REST API посредством методов GET и POST.
- Метод GET возвращает статичный JSON ({"login":"Login1","status":"ok"})
- Метод POST принимает json с двумя параметрами (login, password); возвращает JSON с login, password и текущей датой в формате yyyy-MM-dd HH:mm:ss.
