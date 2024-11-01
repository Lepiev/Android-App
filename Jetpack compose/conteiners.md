# Стандартные контейнеры в Jetpack Compose  
Основные контейнеры Box, Row, Column  
### BOX  
Представляет некоторую область экрана.  
```
@Composable
inline fun Box(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    propagateMinConstraints: Boolean = false,
    content: @Composable BoxScope.() -> Unit
): @Composable Unit
```
Установка размеров Box:  
По умолчанию Box занимает те размеры на экране, которые необходимы, чтобы вместить содержимое.  
Размер настраивается с помощью Modifier  
  
Позиционирование внутри Box:  
Для позиционирования внутри Box данный компонент определяет параметр contentAlignment, которому передаются свойства объекта Alignment:  
- Alignment.BottomCenter: внизу по центру

- Alignment.BottomEnd: внизу в конце

- Alignment.BottomStart: внизу в начале

- Alignment.Center: по центру по вертикали и горизонтали

- Alignment.CenterEnd: по центру про вертикали и в конце по горизонтали

- Alignment.CenterStart: по центру про вертикали и в начале по горизонтали

- Alignment.TopCenter: вверху по центру

- Alignment.TopEnd: вверху в конце

- Alignment.TopStart: вверху в начале
Пример:
```
Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            )
```




