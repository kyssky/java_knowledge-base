
package a_java基础知识点;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class d_泛型 {//适用的时候类型无关的时候
	
}
//定义泛型类  T 代表一个引用类型   ---可以使用界表示法---
//extends扩展表示泛型只能是继承类或者子类的元素---下界  注意不能使用impletes（接口）
// super表示只有他的父类是可以接受的----上界
//	当不同的泛型拥有不同的T或者V   的时候其实是不同的类型
//  当要确认一个变量是个泛型的类或者子类的时候只能使用instanceof ThisisT<?>（这种表示形式）--引申相同的类名不同的T代表不同的类  ？代表通配符
//泛型中的<T>不同或者T不能进行相互转化 就不能及进行枪支转化

//jvm虚拟机 的实现原理是使用了先擦除属性 然后将属性 加进泛型 并且同时出现了一个object类型的方法 --为了能匹配输入  随后 进行桥接方法---
//也就是说---- 在编译成字节码的时候会存在一个相同名称的object 类但是 jvm虚拟机会自动处理
// 比如说  --object sfsf（） String sfsf（）----正常写会出错但是属性擦除不会出错
//泛型可以导致模糊性错误  当使用 T he v 的类型相同的时候  此函数两个拥有相同值的构造方法将会发生命名错误 比如说 T V 类型都是 string
//----泛型的限制  不能实例化参数  ----其实本质山就是 new 的对象不能是泛型 就是 不能 new T（）；
//----对数组的限制  不能实例化泛型数组   其次不能声明 泛型组成的数组
class ThisisT<T extends Father,V>{
	//注意泛型不能是基本类型只能是引用类型  但是java 的自定装箱机制会将基本类型变成引用类型
	//因为引用类型 不能是基本类型 意味着 声明 泛型的时候不能使用引用类型
	public ThisisT(T heh) {}
	public ThisisT(V v){}
	public ThisisT(T t,V v){
		//
		t.thisisFatherMehthod();
	}
	//? 通配符  其实和T所带表的意义是是相同的
	// 但是 ? 通配符是为了解决函数 内部的方法调用 泛型类的时候所采用的因为泛型类中不能使用除了已经声明的泛型之外的字母
	//其余的 T等是一样的方法
	public void method(ArrayList<?> arrayList){//这样这个方法就能接受除了T V 类型之外的其他类型
		
	}


}
class Father{
	public void thisisFatherMehthod(){}
}
interface Mother<T extends ArrayList<T>>{
	public void ThisisMatherMethod();
}
//--继承或者 实现的接口 终不能写extends等语句  具体的实现需要在新的类名称中写 官方属于叫做声明值的传递
//继承泛型类  ---
class  Ceshi<T extends ArrayList<T>> implements Mother<T>{

	@Override
	public void ThisisMatherMethod() {
		// TODO Auto-generated method stub
		
	}
	
}
//继承泛型接口
class Ceshi2<T extends Father, V> extends ThisisT<T,V>{

	public Ceshi2(T heh) {
		super(heh);
		// TODO Auto-generated constructor stub
	}
	
}
//继承泛型类外加泛型接口
class Ceshi3<M extends ArrayList<M>, V, T extends Father> extends ThisisT<T,V> implements Mother<M>{

	public Ceshi3(T heh) {
		super(heh);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ThisisMatherMethod() {
		// TODO Auto-generated method stub
		
	}
}
class BBB<T>{
	public T say() {
		return null;
	}
}
class AAA extends BBB<Integer>{//范型继承可以是已知的类型使用使用已知了型可以在前面不加上属性，但是如果前面加上属性将导致后面的类型变成未知
	@Override
	public Integer say() {
		// TODO Auto-generated method stub
		return 123;
	}
}
class Other{
	//?通配符表示一般的匹配相关的有嗯来约束一类的类型
	class Rong<T>{}
	class One{}
	class Two extends One{}
	class Three extends Two{}
	public void methodone() {
		Rong<? extends Two> rong = new Rong<Three>();
		//Rong<? extends Two> rong2 = new Rong<One>();//报错了因为不满足上界
		Rong<? super Two> rong3 = new Rong<One>();
		//---注意--- 集合类型的通配符模式下 	extends只能取不能存取得是extends后面的类型  
		//								surper 只能存变量强制转化成Object类型不能取
		List<? extends Two> list=new ArrayList<>();
		One one= list.get(0);
		Two two = list.get(0);//之所以只能取是因为强制转换的问题
		//list.set(0, new Three());//报错
		List<? super Two> list2 = new ArrayList<>();
		list2.add(new Three());//适因为内部一定知道相关 术语上转型
		One two1= (Two) list2.get(0);
	}
}

//为了实现与非泛型代码的兼容，Java语言的泛型采用擦除(Erasure)来实现，也就是泛型基本上由编译器来实现，由编译器执行类型检查和类型推断，然后在生成字节码之前将其清除掉，虚拟机是不知道泛型存在的。这样的话，泛型和非泛型的代码就可以混合运行，当然了，也显得相当混乱。
//在使用泛型时，会有一个对应的类型叫做原生类型(raw type)，泛型类型会被擦除到原生类型，如Generic<T>会被查处到Generic，List<String>会被查处到List，由于擦除，在虚拟机中无法获得任何类型信息，虚拟机只知道原生类型。下面的代码将展示Java泛型的真相-擦除：
class Erasure<T> {
	private T t;
	
	public void set(T t) {
		this.t = t;
	}
	
	public T get() {
		return t;
	}
	
	public static void main(String[] args) {	
		Erasure<String> eras = new Erasure<String>();
		eras.set("not real class type");
		String value = eras.get();
		
	}
}
//使用javap反编译class文件，得到如下代码：
//class com.think.generics.Erasure<T> {
//  com.think.generics.Erasure();
//    Code:
//       0: aload_0       
//       1: invokespecial #12                 // Method java/lang/Object."<init>":()V
//       4: return        
//
//  public void set(T);
//    Code:
//       0: aload_0       
//       1: aload_1       
//       2: putfield      #23                 // Field t:Ljava/lang/Object;
//       5: return        
//
//  public T get();
//    Code:
//       0: aload_0       
//       1: getfield      #23                 // Field t:Ljava/lang/Object;
//       4: areturn       
//
//  public static void main(java.lang.String[]);
//    Code:
//       0: new           #1                  // class com/think/generics/Erasure
//       3: dup           
//       4: invokespecial #30                 // Method "<init>":()V
//       7: astore_1      
//       8: aload_1       
//       9: ldc           #31                 // String not real class type
//      11: invokevirtual #33                 // Method set:(Ljava/lang/Object;)V
//      14: aload_1       
//      15: invokevirtual #35                 // Method get:()Ljava/lang/Object;
//      18: checkcast     #37                 // class java/lang/String
//      21: astore_2      
//      22: return        
//}
//从反编译出来的字节码可以看到，泛型Erasure<T>被擦除到了Erasure，其内部的字段T被擦除到了Object，可以看到get和set方法中都是把t作为Object来使用的。最值得关注的是，反编译代码的倒数第三行，对应到Java代码就是String value = eras.get();编译器执行了类型转换。这就是Java泛型的本质：对传递进来的值进行额外的编译期检查，并插入对传递出去的值的转型。这样的泛型真的是泛型吗？
//即便我们可以说，Java中的泛型确实不是真正的泛型，但是它带来的好处还是显而易见的，它使得Java的类型安全前进了一大步，原本需要程序员显式控制的类型转换，现在改由编译器来实现，只要你按照泛型的规范去编写代码，总会得到安全的保障。在这里，我们不得不思考一个问题，理解Java泛型，那么其核心目的是什么？我个人认为，Java泛型的核心目的在于安全性，尤其是在理解泛型通配符时，一切奇怪的规则，归根结底都是处于安全的目的。
//类型信息的丢失
//由于擦除的原因，在泛型代码内部，无法获得任何有关泛型参数类型的信息。在运行时，虚拟机无法获得确切的类型信息，一切以来确切类型信息的工作都无法完成，比如instanceof操作，和new表达式，
//class Erasure1<T>  {
//    public void f() {
//        if(arg instanceof T) //Error
//        T ins = new T();//Error
//        T[] array = new T[10];//error
//    }
//}
//那么在需要具体的类型信息时，我们就要记住Class对象来实现了，凡是在运行时需要类型信息的地方，都使用Class对象来进行操作，比如：
//class Erasure2<T> {
//    private Class<T> clazz;
//    Erasure(Class<T> kind) {
//        clazz = kind;
//    }
//    public void f() {
//        if(clazz.isInstance(arg)) {}
//        T t = clazz.newInstance();//必须要有无参构造方法
//    }
//}

