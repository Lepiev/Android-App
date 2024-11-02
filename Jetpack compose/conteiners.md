# Стандартные контейнеры в Jetpack Compose  
Основные контейнеры Box, Row, Column  
Вместо Column и Row есть более эффективные LazyColumn и LazyRow, они отображают только то что видит пользователь  
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

### COLUMN  
```
@Composable
inline fun Column(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
): @Composable Unit
```
- verticalArrangement: объект Arrangement.Vertical, который устанавливает выравнивание компонента по вертикали. По умолчанию имеет значение Arrangement.Top (расположение в верху)

- horizontalAlignment: объект Alignment.Horizontal, который устанавливает выравнивание компонента по горизонтали. По умолчанию имеет значение Alignment.Start (расположение в начале - слева для языков с левосторонним письмом и справа для языков с правосторонним письмом)

- content: объект интерфейса BoxScope, который представляет вложенное содержимое
    
Позиционирование по вертикали и verticalArrangement  
Если высота контейера Column больше суммы высот его вложенных компонентов, то для позиционирования этих компонентов может применяться параметр verticalArrangement, который может принимать следующие значения:  

- Arrangement.Center: расположение по центру

- Arrangement.Bottom: расположение внизу

- Arrangement.Top: расположение вверху

- Arrangement.SpaceAround: компоненты равномерно распределяются по всей высоте с равномерными отступами между элементами, при этом отступы между первым и последним элементами и границами контейнера равен половине отступов между элементами
 - Arrangement.SpaceBetween: компоненты равномерно распределяются по всей высоте с равномерными отступами между элементами, при этом первый и последний элементы прижимаются к границам контейнера

- Arrangement.SpaceEvenly: компоненты равномерно распределяются по всей высоте с равномерными отступами между элементами, при этом отступы между первым и последним элементами и границами контейнера равны отступам между элементами

Выравнивание элементов по горизонтали  
Параметр horizontalAlignment позволяет выровнить содержимое вложенных компонентов. Этому параметру можно передать одно из следующих значений:

- Alignment.Start: выравнивание в начале (по умолчанию)

- Alignment.End: выравнивание в конце

- Alignment.CenterHorizontally: выравнивание по центру


### ROW  
```
@Composable
inline fun Row(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    content: @Composable RowScope.() -> Unit
): @Composable Unit
```
- modifier: объект Modifier, который позволяет настроить внешний вид и поведение компонента

- horizontalArrangement: объект Arrangement.Horizontal, который устанавливает выравнивание компонента по горизонтали. По умолчанию имеет значение Arrangement.Start (расположение в вначале: слева для левосторонних языков и справа для правосторонних языков)

- verticalAlignment: объект Alignment.Vertical, который устанавливает выравнивание компонента по вертикали. По умолчанию имеет значение Alignment.Top (расположение вверху)

- content: объект интерфейса RowScope, который представляет вложенное содержимое



### LazyColumn  
LazyColumn создает список с вертикальной прокруткой, а LazyRow создает список с горизонтальной прокруткой и имеет следующие параметры:
```
@Composable
fun LazyColumn(
    modifier: Modifier = Modifier,
    state: LazyListState = rememberLazyListState(),
    contentPadding: PaddingValues = PaddingValues(0.dp),
    reverseLayout: Boolean = false,
    verticalArrangement: Arrangement.Vertical = if (!reverseLayout) Arrangement.Top else Arrangement.Bottom,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    flingBehavior: FlingBehavior = ScrollableDefaults.flingBehavior(),
    userScrollEnabled: Boolean = true,
    content: LazyListScope.() -> Unit
): Unit
```
- modifier: применяемые к контейнеру модификаторы

- state: объект состояния LazyListState, применяемый для управления состоянием контейнера

- contentPadding: отступы вокруг содержимого

- reverseLayout: при значении true располагает элементы в обратном порядке

- verticalArrangement: настройки расположения элементов по вертикали

- horizontalAlignment: выравнивание элементов по горизонтали

- flingBehavior: описывает поведение при таком типе прокрутки, когда пользователь быстро перетаскивает что-то и поднимает палец. Представляет объект FlingBehavior

- userScrollEnabled: указывает, доступна ли прокрутка жестами либо через специальные инструменты управления доступом

- content: устанавливает содержимое контейнера с помощью функции типа LazyListScope.() -> Unit.
