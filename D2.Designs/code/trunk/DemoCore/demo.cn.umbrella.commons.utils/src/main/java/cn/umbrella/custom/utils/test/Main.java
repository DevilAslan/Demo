package cn.umbrella.custom.utils.test;

public class Main {

	public static void main(String[] args) {
		// 外部普通类
		System.out.println("方法名             类名");
		System.out.println("getName            " + Main.class.getName());
		System.out.println("getCanonicalName   "
				+ Main.class.getCanonicalName());
		System.out.println("getSimpleName      " + Main.class.getSimpleName());
		System.out.println();

		// 内部类
		System.out.println("getName            "
				+ TestInnerClass.class.getName());
		System.out.println("getCanonicalName   "
				+ TestInnerClass.class.getCanonicalName());
		System.out.println("getSimpleName      "
				+ TestInnerClass.class.getSimpleName());
		System.out.println();

		// 数组类
		TestInnerClass[] testInnerClasses = new TestInnerClass[] {
				new TestInnerClass(), new TestInnerClass(),
				new TestInnerClass() };
		System.out.println("getName            "
				+ testInnerClasses.getClass().getName());
		System.out.println("getCanonicalName   "
				+ testInnerClasses.getClass().getCanonicalName());
		System.out.println("getSimpleName      "
				+ testInnerClasses.getClass().getSimpleName());
		System.out.println();

	}

	static class TestInnerClass {

	}

}
