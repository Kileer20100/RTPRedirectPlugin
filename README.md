
# RTPRedirectPlugin

## Описание

RTPRedirectPlugin — плагин для Minecraft (Bukkit/Spigot), который перехватывает команду `/rtp` и перенаправляет её в команду `/betterrtp` из плагина BetterRTP с логикой телепортации между мирами.

---

## Как это работает

* При вводе команды `/rtp` плагин проверяет мир игрока.
* Если игрок в мире `from-world` (например, `world`):

  * Телепортирует игрока в мир `to-world` (например, `Survival`).
  * Запускает `/betterrtp` с небольшой задержкой.
* Если игрок в другом мире — команда `/rtp` просто запускает `/betterrtp` без телепортации.
* Конфигурация из `config.yml`, есть команда `/rtpredirectreload` для перезагрузки конфига без рестарта сервера.

---

## Установка и настройка

1. Скопируйте `RTPRedirectPlugin.jar` в папку `plugins` вашего Bukkit/Spigot сервера.
2. Запустите сервер — плагин создаст файл `config.yml`.
3. Отредактируйте `config.yml`, указав:

   ```yaml
   from-world: world
   to-world: Survival
   ```
4. Перезапустите сервер или выполните `/rtpredirectreload` для применения настроек.

---

## Команды

* `/rtp` — запускает логику плагина (перенаправляет `/betterrtp` и телепортирует при необходимости).
* `/rtpredirectreload` — перезагружает конфигурацию плагина.

---

## Требования

* Minecraft сервер с поддержкой Bukkit/Spigot API.
* Плагин BetterRTP должен быть установлен.

---

## Сборка плагина из исходников

### Что нужно установить

* **Java Development Kit (JDK) 17 или выше**
  Скачать: [https://adoptium.net/](https://adoptium.net/) или [https://jdk.java.net/](https://jdk.java.net/)

* **Apache Maven** (рекомендуется для сборки)
  Скачать: [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
  Проверить установку:

  ```bash
  mvn -v
  ```

* **IDE** (не обязательно, но удобно) — например, IntelliJ IDEA или Eclipse.

### Как собрать

1. Склонируйте репозиторий или создайте проект с этим кодом.

2. Создайте `pom.xml` для Maven с зависимостью на Spigot API. Пример:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" ...>
  <modelVersion>4.0.0</modelVersion>
  <groupId>com.example</groupId>
  <artifactId>RTPRedirectPlugin</artifactId>
  <version>1.0.0</version>
  <repositories>
    <repository>
      <id>spigot-repo</id>
      <url>https://hub.spigotmc.org/nexus/content/repositories/snapshots/</url>
    </repository>
  </repositories>
  <dependencies>
    <dependency>
      <groupId>org.spigotmc</groupId>
      <artifactId>spigot-api</artifactId>
      <version>1.20.1-R0.1-SNAPSHOT</version>
      <scope>provided</scope>
    </dependency>
  </dependencies>
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.8.1</version>
        <configuration>
          <source>17</source>
          <target>17</target>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-jar-plugin</artifactId>
        <version>3.2.0</version>
        <configuration>
          <archive>
            <manifest>
              <addClasspath>true</addClasspath>
              <mainClass>com.example.rtpredirect.RTPRedirectPlugin</mainClass>
            </manifest>
          </archive>
        </configuration>
      </plugin>
    </plugins>
  </build>
</project>
```

3. Выполните сборку:

```bash
mvn clean package
```

4. В папке `target/` появится `RTPRedirectPlugin-1.0.0.jar` — этот файл копируйте в `plugins` вашего сервера.

Если нужна помощь с настройкой Maven, pom.xml или сборкой — скажи, помогу.
