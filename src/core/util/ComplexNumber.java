package core.util;

public class ComplexNumber {

    public float a, b;

    public ComplexNumber(float a, float b) {
        this.a = a;
        this.b = b;

    }

    public static ComplexNumber zero = new ComplexNumber(0, 0);

    public static ComplexNumber conjugate(ComplexNumber z) {
        return new ComplexNumber(z.a, -z.b);
    }

    public static float magnitude(ComplexNumber z) {
        return (float) Math.sqrt(z.a * z.a + z.b + z.b);
    }

    public static float Re(ComplexNumber z) {
        return z.a;
    }

    public static float Im(ComplexNumber z) {
        return z.b;
    }

    public static ComplexNumber add(ComplexNumber num1, ComplexNumber num2) {
        return new ComplexNumber(num1.a + num2.a, num1.b + num2.b);
    }

    public static ComplexNumber sub(ComplexNumber num1, ComplexNumber num2) {
        return new ComplexNumber(num1.a - num2.a, num1.b - num2.b);
    }

    public static ComplexNumber mul(ComplexNumber num1, ComplexNumber num2) {
        float a = num1.a * num2.a - num1.b * num2.b;
        float b = num1.a * num2.b + num1.b * num2.a;
        return new ComplexNumber(a, b);
    }

    public static ComplexNumber div(ComplexNumber num1, ComplexNumber num2) {
        ComplexNumber z = mul(num1, conjugate(num2));
        float magnitude = Re(mul(num2, conjugate(num2))); // Take the real part of the number because z x z* results in a real number
        return new ComplexNumber(z.a / magnitude, z.b / magnitude);
    }

    public static ComplexNumber pow(float n, ComplexNumber num2) {
        float na = (float) Math.pow(n, Re(num2));
        double ln_a = Math.log(n) / Math.log(Math.E);
        float a = na * (float) Math.cos(Im(num2) * ln_a);
        float b = na * (float) Math.sin(Im(num2) * ln_a);
        return new ComplexNumber(a, b);
    }

    @Override
    public String toString() {
        return a + " + " + b + "i";
    }

}
