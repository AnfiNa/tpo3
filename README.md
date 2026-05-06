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

## Актуальная структура наборов

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

Адаптирован под текущий лендинг «БОЛЬШИЕ ЯЙЦА — РАЗМЕР ИМЕЕТ ЗНАЧЕНИЕ»:
- `nestScrollsToTopAfterDoubleReload`
- `sizesScrollsToTopAfterDoubleReload`
- `takeItScrollsToTopAfterDoubleReload`
- `tiktokAfterDoubleReload` (проверка alert)
- `telegramAfterDoubleReload` (проверка alert)

### Suite4
- `Suite4FirefoxTest` — Firefox
- `Suite4ChromeTest` — Chrome

Проверяет поведение навигации/CTA после multiple reload сценария и возврат к верхней части страницы.

### Suite5
- `Suite5FirefoxTest` — Firefox
- `Suite5ChromeTest` — Chrome

Проверяет якорную навигацию и видимость целевых секций на «большой» версии страницы.

### Дополнительные классы
- `DoubleReloadMenuAndAlertsTest`
- `SingleReloadAnchorScrollTest`
- `LandingPageBehaviorTest`
- `EggEmpireNavigationTest`
- `KonamiThemeChromeTest`
- `KonamiThemeFirefoxTest`

## Важные замечания

1. `Suite5ChromeTest` запускается в видимом Chrome (headless отключен).
2. Для новых тестов используется выбор элементов через XPath (как требовалось).
3. Если видите ошибку `illegal character: '\ufeff'`, это BOM в `.java` файлах. Нужно сохранить файлы как `UTF-8 without BOM`.
4. Если Chrome не стартует с ошибкой `DevToolsActivePort file doesn't exist`, проверьте:
- совместимость Chrome/ChromeDriver
- что Chrome не занят другим процессом
- локальные политики/антивирус

## Стек

- Java
- JUnit 4
- Selenium 4
- Maven Surefire
