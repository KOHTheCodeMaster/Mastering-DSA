package practice.unsorted.interview.dataType;


public class DataType1 {

    public static void main(String[] args) {

        DataType1 obj = new DataType1();
        obj.major();

    }

    private void major() {

        implicitTypeCasting1();
        implicitTypeCastFromIntToDoublePrimitive(5);

        enum1();

    }

    private void enum1() {

        Color color = Color.valueOf("WHITE");
        System.out.println(color.getRGB_VALUE());


    }

    private void identifier1() {
/*
        int a1;
        int 1a;
        int 1_a;
        int 1_a;
*/
    }

    private void implicitTypeCasting1() {

        double d1 = 5L;         //  implicit typecasting from small to bigger data type.
        double d2 = (int) 5L;   //  implicit typecasting from small to bigger data type.

//        int x1 = 5L;          //  Compilation Error - Required int, Provided long
//        int x2 = 5D;          //  Compilation Error - Required int, Provided double

        int x3 = 5;
        double d3 = x3;         //  implicit typecasting from small to bigger data type.
        x3 = (int) d3;          //  Explicit Typecast required when converting from bigger to smaller data type.

    }

    private double implicitTypeCastFromIntToDoublePrimitive(int x) {
        return x;
    }

}
