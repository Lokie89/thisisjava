package examples;

@FunctionalInterface
public interface MyFunctionalInterface2 {
    public void method();
}

class UsingThis {
    public int outterField = 10;

    class Inner {
        int innerField = 20;

        void method() {
            MyFunctionalInterface2 fi = () -> {
                System.out.println("outField: " + outterField);
                System.out.println("outField: " + UsingThis.this.outterField + "\n");

                System.out.println("innerField: " + innerField);
                System.out.println("innerField: " + this.innerField + "\n");
            };
            fi.method();
        }
    }
}

class UsingThisExample{
    public static void main(String[] args) {
        UsingThis usingThis = new UsingThis();
        UsingThis.Inner inner = usingThis.new Inner();
        inner.method();
    }
}