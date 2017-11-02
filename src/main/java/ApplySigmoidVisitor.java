import org.apache.commons.math3.linear.RealMatrixChangingVisitor;

/**
 * @author Yevhen
 */
public class ApplySigmoidVisitor implements RealMatrixChangingVisitor {

    @Override
    public void start(int rows, int columns, int startRow, int endRow, int startColumn, int endColumn) {

    }

    @Override
    public double visit(int row, int column, double value) {
        return 1 / (1 + Math.exp(-value));
    }

    @Override
    public double end() {
        return 0;
    }
}
