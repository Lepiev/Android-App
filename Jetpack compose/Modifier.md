### Модификаторы для компонентов
Коротко о содержании:



МОДИФИКАТЫ:

1. Модификатор прокрутки содержимого компонента
Для добавления прокрутки содержимого по горизонтали применяется модификатор Modifier.horizontalScroll
```
fun Modifier.horizontalScroll(
    state: ScrollState,
    enabled: Boolean = true,
    flingBehavior: FlingBehavior? = null,
    reverseScrolling: Boolean = false
): Modifier
```
