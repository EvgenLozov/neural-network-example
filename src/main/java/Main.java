import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.SparseRealMatrix;

import java.util.Arrays;

/**
 * @author Yevhen
 */
public class Main {

    public static void main(String[] args) {

        double [][] input = { {1, 0, 1} };
        double [][] weightOfHidden = { {0.45, 0.78}, {-0.12, 0.13} , {0, 0}};
        double [][] weightOfOut = { {1.5}, {-2.3} };

//      better weights
//      double [][] input = { {1, 0} };
//      double [][] weightOfHidden = { {0.5, 0.73}, {-0.12, 0.13}};
//      double [][] weightOfOut = { {1.563}, {-2.2} };

        RealMatrix inputMatrix = MatrixUtils.createRealMatrix(input);
        RealMatrix weightOfHiddenMatrix = MatrixUtils.createRealMatrix(weightOfHidden);
        RealMatrix weightOfOutMatrix = MatrixUtils.createRealMatrix(weightOfOut);

        System.out.println("Input: " + Arrays.deepToString(inputMatrix.getData()));

        RealMatrix hiddenInput = inputMatrix.multiply(weightOfHiddenMatrix);
//        System.out.println(Arrays.deepToString(hiddenInput.getData()));

        RealMatrix hiddenOutput = hiddenInput.copy();
        hiddenOutput.walkInRowOrder(new ApplySigmoidVisitor());
//        System.out.println(Arrays.deepToString(hiddenOutput.getData()));

        RealMatrix outInput = hiddenOutput.multiply(weightOfOutMatrix);
        RealMatrix output = outInput.copy();
        output.walkInRowOrder(new ApplySigmoidVisitor());
//        System.out.println(Arrays.deepToString(outInput.getData()));

        double result = output.getEntry(0, 0);
        System.out.println("Result: " + result);

        double error = Math.pow(1 - result, 2);
        System.out.println("Error: " + Math.round(error*100) + "%");
    }
}
