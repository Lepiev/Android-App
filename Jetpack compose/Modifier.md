# Модификаторы для компонентов
Коротко о содержании: Прокрутка, граница, форма, фон, тень, обработка нажатия



### МОДИФИКАТОРЫ:


### 1. Модификатор прокрутки содержимого компонента
  
ТЕГИ: прокуртка, ползунок, крутить, листать
Для добавления прокрутки содержимого по горизонтали применяется модификатор `Modifier.horizontalScroll`
```
fun Modifier.horizontalScroll(
    state: ScrollState,
    enabled: Boolean = true,
    flingBehavior: FlingBehavior? = null,
    reverseScrolling: Boolean = false
): Modifier
```
Прокрутка по вертикали `Modifier.verticalScroll`:
```
fun Modifier.verticalScroll(
    state: ScrollState,
    enabled: Boolean = true,
    flingBehavior: FlingBehavior? = null,
    reverseScrolling: Boolean = false
): Modifier
```
Параметры модификаторов:

- state: представляет объект ScrollState и описывает состояние полосы прокрутки

- enabled: значение типа Boolean, которое указывает, будет ли прокрутка доступна. По умолчанию имеет значение true

- flingBehavior: представляет объект FlingBehavior и описывает поведение при завершении прокрутки. По умолчанию имеет значение ScrollableDefaults.flingBehavior

- reverseScrolling: устанавливает направление. При значении true прокрутка идет в обратном направлении. По умолчанию имеет значение false




### 2. Модификатор создания границы для компонента  
ТЕГИ: граница, выделить, бордер, border  
Модификатор border `Modifier.border()` позволяет определить границу вокруг компонента. Этот модификатор имеет следующие определения:

- Modifier.border(border: BorderStroke, shape: Shape = RectangleShape)  
- Modifier.border(width: Dp, brush: Brush, shape: Shape = RectangleShape)  
- Modifier.border(width: Dp, color: Color, shape: Shape = RectangleShape)

### 3. Модификатор изменения цвета фона компонента
   ТЕГИ: фон  
   Модификатор `.background()`  

### 4. Модификатор для изменения формы компонента  
   ТЕГИ: закругление, форма, shape  
   Модикатор `Modifier.clip()`  
   fun Modifier.clip(shape: Shape): Modifier




### 5. Модификатор добавляющий тень  
ТЕГИ: тень  
внутренний код  
```
fun Modifier.shadow(
    elevation: Dp,
    shape: Shape = RectangleShape,
    clip: Boolean = elevation > 0.dp,
    ambientColor: Color = DefaultShadowColor,
    spotColor: Color = DefaultShadowColor
): Modifier
```

`shadow()`  
Функция модификатора принимает следующие параметры:  

- elevation: высота тени в dp

- shape: определяет форму тени с помощью объекта Shape

- clip: если равно true, то создается фрагмент с использованием формы из параметра shape

- ambientColor: цвет затенения на компоненте

- spotColor: цвет тени вокруг компонента


### 6. Модификатор обработки нажатия
ТЕГИ: нажатие, действие при нажатии, кнопка  
Модифмкатор `clickable()`  
```
fun Modifier.clickable(
    enabled: Boolean = true,
    onClickLabel: String? = null,
    role: Role? = null,
    onClick: () -> Unit
): Modifier
```
Параметры функции:

- enabled: значение типа Boolean, которое указывает, будет ли доступен компонент для нажатия. По умолчанию имеет значение true, то есть компонент будет доступен для нажатия. При значении false обработка нажатий оключена.

- onClickLabel: предоставляет значение типа String? и задает метку, с помощью которой можно быстро обратиться к компоненту. По умолчанию равен null

- role: объект типа Role?, который устанавливает тип визуального элемента. По умолчанию равен null

- onClick: функция типа () -> Unit, которая собственно и обрабатывает нажатие.
