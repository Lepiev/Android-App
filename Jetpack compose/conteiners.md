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

Если Box содержит несколько вложенных компонентов, то умолчанию они будут накладываться друг на друга  
Выравнивание вложенных компонентов:  
В Box мы можем задать расположение не только для всех вложенных компонентов в целом, но и для каждого компонента по отдельности. При определении компонентов внутри Box на компонентах можно определить модификатор align, который принимает вышерассмотренный объект Alignment и позволяет настроить положение каждого отдельного компонентов внутри Box:  
```
Box( modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center )
            {
                Text("TopStart", fontSize = 28.sp, modifier =Modifier.align(Alignment.TopStart))
                Text("TopEnd", fontSize = 28.sp, modifier =Modifier.align(Alignment.TopEnd))
                Text("Center", fontSize = 28.sp, modifier =Modifier.align(Alignment.Center))
                Text("BottomStart", fontSize = 28.sp, modifier =Modifier.align(Alignment.BottomStart))
                Text("BottomEnd", fontSize = 28.sp, modifier =Modifier.align(Alignment.BottomEnd))
            }
```



