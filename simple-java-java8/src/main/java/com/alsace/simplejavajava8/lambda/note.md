1. lambda是一个匿名函数，可以理解为是一段可以传递的代码

2. java8引入了一个新的操作符“->”， 左侧：参数列表； 右侧：lambda表达式中所需执行的功能，即lambda体

3. lambda语法格式： 1). 无参数，无返回值：() -> System.out.println("hello world"); 2). 一个参数，无返回值，小括号可以省略不写：(x) -> System.out.println(
   x); 3). 两个参数，有返回值，如果方法体中只有一条语句，{}和return可以省略不写：(x, y) -> {...; return ...;}; 4).
   lambda中表达式的参数类型可以省略不写，jvm可以通过上下文进行推断参数类型

4. 函数式接口： 接口中只有一个抽象方法，可以使用@FunctionalInterface修饰，可以检查是不是函数式接口

5. java内置四大核心函数式接口:
   Consumer<T> 消费型，无返回值 Supplier<T> 供给型，返回值T Function<T, R> 函数型，返回R Predicate<T> 断言型，返回boolean

6. 方法引用：如果lambda体中的内容有方法已经实现了，可以使用“方法引用” 主要有三种语法格式： 对象::实例方法名，如 System.out::println; 类::静态方法名，如 Integer::compare; 类::
   实例方法名，如果第一个参数是方法的调用者，第二个参数是方法的参数时，可以使用。如：
   (x, y) -> x.equals(y); ===>  String::equals;

7. 构造器引用，构造器参数列表要与函数式接口参数列表一致 格式： ClassName::new