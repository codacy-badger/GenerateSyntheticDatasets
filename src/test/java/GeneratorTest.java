import org.junit.Assert;
import org.junit.Test;

/**
 * @author Jadson Oliveira <jadsonjjmo@gmail.com>
 */
public class GeneratorTest {

    @Test
    public void linearOperationsTest01() {
        final String expression = "{10}*{1}+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(30.0, result, 0);
    }

    @Test
    public void linearOperationsTest02() {
        final String expression = "{10}*{2.0}+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(40.0, result, 0);
    }

    @Test
    public void linearOperationsTest03() {
        final String expression = "{10}*({1.0}+{2.0})+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(50.0, result, 0);
    }

    @Test
    public void linearOperationsTest04() {
        final String expression = "{10}*({1.0}-{2.0})+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(10.0, result, 0);
    }

    @Test
    public void linearOperationsTest05() {
        final String expression = "{10}*({1.0}*{2.0})+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(40.0, result, 0);
    }

    @Test
    public void linearOperationsTest06() {
        final String expression = "{10}*({1.0}/{2.0})+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(25.0, result, 0);
    }

    @Test
    public void linearOperationsTest07() {
        final String expression = "{10}*(({1.0}/{2.0})+{3.5})+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(60.0, result, 0);
    }

    @Test
    public void quadraticOperationsTest01() {
        final String expression = "{10}*{1.0}^{2}+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(30.0, result, 0);
    }

    @Test
    public void quadraticOperationsTest02() {
        final String expression = "{10}*{2.0}^{2}+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(60.0, result, 0);
    }

    @Test
    public void quadraticOperationsTest03() {
        final String expression = "{10}*({1.0}+{2.0})^{2}+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(110.0, result, 0);
    }

    @Test
    public void logOperationsTest01() {
        final String expression = "{10}*l{1.0}+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(20.0, result, 0);
    }

    @Test
    public void logOperationsTest02() {
        final String expression = "{10}*l{2.0}+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(10 * Math.log(2) + 20, result, 0.0001);
    }

    @Test
    public void logOperationsTest03() {
        final String expression = "{10}*l({1.0}+{2.0})+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(10 * Math.log(3) + 20, result, 0.0001);
    }

    @Test
    public void sinOperationsTest01() {
        final String expression = "{10}*s({1.0}+{2.0})+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(10 * Math.sin(Math.toRadians(3)) + 20, result, 0.0001);
    }

    @Test
    public void sinOperationsTest02() {
        final String expression = "{10}*s{2.0}+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(10 * Math.sin(Math.toRadians(2)) + 20, result, 0.0001);
    }

    @Test
    public void exponentialOperationsTest01() {
        final String expression = "{10}^{1.0}+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(30.0, result, 0);
    }

    @Test
    public void exponentialOperationsTest02() {
        final String expression = "{10}^{2.0}+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(120.0, result, 0);
    }

    @Test
    public void exponentialOperationsTest03() {
        final String expression = "{10}^({1.0}+{2.0})+{20}";
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(1020.0, result, 0);
    }

    @Test
    public void approximatedOperationsTest01() {
        String expression = "{10}*{1.0}+{rand{10}}";
        expression = Generator.normalizeExpression(expression, new String[]{"1.0", "2.0"});
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(10.0, result, 10);
    }

    @Test
    public void approximatedOperationsTest02() {
        String expression = "{10}*{2.0}+{rand{5}}";
        expression = Generator.normalizeExpression(expression, new String[]{"1.0", "2.0"});
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(20.0, result, 5);
    }

    @Test
    public void approximatedOperationsTest03() {
        String expression = "{10}*([0]+[1])+({rand{10}}/{100})";
        expression = Generator.normalizeExpression(expression, new String[]{"1.0", "2.0"});
        final double result = Double.parseDouble(Generator.solve(expression));

        Assert.assertEquals(30.0, result, 0.1);
    }


}
