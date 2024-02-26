package practice.unsorted.interview.dataType;

public enum Color {

    WHITE("FFF"), BLACK("000");
    private final String RGB_VALUE;
    Color(String rgbValue) {
        RGB_VALUE = rgbValue;
    }

    public String getRGB_VALUE() {
        return RGB_VALUE;
    }
}
