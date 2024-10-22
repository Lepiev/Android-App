### Модификаторы для компонентов
Коротко о содержании: Прокрутка, граница



МОДИФИКАТЫ:

ТЕГИ: прокуртка, ползунок, крутить, листать
1. Модификатор прокрутки содержимого компонента
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



ТЕГИ: граница, выделить, бордер, border  
2. Модификатор создания границы для компонента
Модификатор border `Modifier.border()` позволяет определить границу вокруг компонента. Этот модификатор имеет следующие определения:

- Modifier.border(border: BorderStroke, shape: Shape = RectangleShape)  
  принимает объект BorderStroke, в который через конструктор передается ширина линии в единицах dp и ее цвет в виде объекта Brush: BorderStroke(width: Dp, brush: Brush)  
- Modifier.border(width: Dp, brush: Brush, shape: Shape = RectangleShape)  
- Modifier.border(width: Dp, color: Color, shape: Shape = RectangleShape)  








