package practice.unsorted;

public class InterfaceDemo {

    public static void main(String[] args) {


        Interface1 interface1 = new Interface1() {
            @Override
            public String toString() {
                System.out.println("L0G - toString()");
                return null;
            }
        };

        System.out.println(interface1);
//        System.out.println(interface1.toString1());

    }


}
