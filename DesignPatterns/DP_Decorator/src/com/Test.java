package com;

/**
 * 
 * java模式—装饰者模式
 * 
 * 
 *（1）抽象组件:定义一个抽象接口，来规范准备附加功能的类

（2）具体组件：将要被附加功能的类，实现抽象构件角色接口

（3）抽象装饰者：持有对具体构件角色的引用并定义与抽象构件角色一致的接口

（4）具体装饰：实现抽象装饰者角色，负责对具体构件添加额外功能。


    1.Component（被装饰对象的基类）  --> Person

      定义一个对象接口，可以给这些对象动态地添加职责。

    2.ConcreteComponent（具体被装饰对象）  --> Man

      定义一个对象，可以给这个对象添加一些职责。

    3.Decorator（装饰者抽象类） --> Decorator

      维持一个指向Component实例的引用，并定义一个与Component接口一致的接口。

    4.ConcreteDecorator（具体装饰者） --> ManDecoratorA  ManDecoratorB

      具体的装饰对象，给内部持有的具体被装饰对象，增加具体的职责。
 * 
 * @author 阳庆文
 * @version v1.0
 * @date 2017年7月10日
 */
public class Test {

    public static void main(String[] args) {
        Man man = new Man();
        ManDecoratorA md1 = new ManDecoratorA();
        ManDecoratorB md2 = new ManDecoratorB();
        
        md1.setPerson(man);
        md2.setPerson(md1);
        md2.eat();
    }
}