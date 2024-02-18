# IncBossesAbility_Regeneration
**_IncBossesAbility_Regeneration_** - это способность для плагина **IncBosses**, добавляющая уведомления о начале или конце фазы!

## Использование
Чтобы использовать способность, вам достаточно всего лишь загрузить [**_.jar_**](https://github.com/IncrementStudio/IncBossesAbility_Regeneration/releases/download/1.0/IncBossesAbility_Regeneration-1.0.jar) файл способности и переместить его в папку **_plugins/IncBosses/abilities_**.

Чтобы использовать способность в фазе босса, вам нужно создать секцию для новой способности и указать её название:
```yaml
abilities:
  notifications:
    ability: IncBossesAbility_Regeneration
```
Также в этой секции вам нужно указать параметры для этой способности.

## Параметры
### delay
Тип данных: **TimeMetric**
```
TimeMetric - это строка с форматом <число>[tick|t|sec|s|min|m|hour|h],
которая позволяет удобно задавать временные значения.

Пример:
- 10tick или 10t - 10 тиков
- 10sec или 10s - 10 секунд
- 10min или 10m - 10 минут
- 10hour или 10h - 10 часов
```
Значение по умолчанию: **0t**

Задержка перед началом регенерации.
___
### period
Тип данных: **TimeMetric**\
Значение по умолчанию: **1s**

Период, с которым происходит регенерация.
___
### health
Тип данных: **Double**\
Значение по умолчанию: **1**

Количество восполняемого здоровья.