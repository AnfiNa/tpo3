# Selenium UI Tests

Автотесты на `JUnit 4 + Selenium` для сайта:
`https://xn----8sbbqoikx1fp7b1b.xn--p1ai/`

## Требования

- Java 11+
- Maven 3.9+
- Google Chrome + ChromeDriver (совместимые версии)
- Mozilla Firefox + GeckoDriver

## Запуск

- Все тесты:
```bash
mvn clean test
```

- Один класс:
```bash
mvn "-Dtest=Suite3ChromeTest" test
```

- Несколько классов:
```bash
mvn "-Dtest=Suite3ChromeTest,Suite3FirefoxTest" test
```

### Suite1
- `Suite1Test` — Firefox
- `Suite1ChromeExactTest` — Chrome

Проверяет базовые сценарии открытия страницы, переходов по основным элементам и работу якорной навигации.

### Suite2
- `Suite2FirefoxTest` — Firefox
- `Suite2ChromeTest` — Chrome

Проверяет single-reload сценарии (1 перезагрузка) и навигацию по пунктам меню целевой страницы.

### Suite3
- `Suite3FirefoxTest` — Firefox
- `Suite3ChromeTest` — Chrome

Проверяет double-reload сценарии (2 перезагрузки) и навигацию по пунктам меню целевой страницы, а также уведомления.

### Suite4
- `Suite4FirefoxTest` — Firefox
- `Suite4ChromeTest` — Chrome

Проверяет поведение навигации/CTA после multiple reload сценария и возврат к верхней части страницы.

### Suite5
- `Suite5FirefoxTest` — Firefox
- `Suite5ChromeTest` — Chrome

Проверяет якорную навигацию и видимость целевых секций на «большой» версии страницы.

## Стек

- Java
- JUnit 4
- Selenium 4
- Maven Surefire
