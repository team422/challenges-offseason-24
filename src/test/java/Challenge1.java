import frc.robot.util.math.BinaryTreeUtils;
import frc.robot.util.math.BinaryTreeUtils.TreeNode;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
public class Challenge1 {

  // utils for testing
  private static void myAssert(boolean condition, String message) {
    if (!condition) {
      System.out.println("Assertion failed: " + message);
      throw new AssertionError(message);
    }
  }

  private static double[] getRange(double min, double max, int length) {
    double[] range = new double[length];
    for (int i = 0; i < length; i++) {
      range[i] = min + i * (max - min) / (length - 1);
    }
    return range;
  }

  @Test
  public void checkSymmetric() {
    TreeNode root =
        new TreeNode(
            1,
            new TreeNode(2, new TreeNode(3), new TreeNode(4)),
            new TreeNode(2, new TreeNode(4), new TreeNode(3)));
    boolean passedSpecial = true;
    String output = BinaryTreeUtils.isSymmetric(root);
    myAssert(
        output.equals("true") || output.equals("I read the instructions."),
        "Be sure to read the instructions!");
    if (output.equals("I read the instructions.")) {
      passedSpecial = false;
    }

    root =
        new TreeNode(
            1, new TreeNode(2, null, new TreeNode(3)), new TreeNode(2, null, new TreeNode(3)));
    output = BinaryTreeUtils.isSymmetric(root);
    myAssert(
        output.equals("false") || output.equals("I read the instructions."),
        "Be sure to read the instructions!");
    if (output.equals("I read the instructions.")) {
      passedSpecial = false;
    }

    root = new TreeNode(5);
    output = BinaryTreeUtils.isSymmetric(root);
    myAssert(
        output.equals("true") || output.equals("I read the instructions."),
        "Be sure to read the instructions!");
    if (output.equals("I read the instructions.")) {
      passedSpecial = false;
    }

    myAssert(
        !passedSpecial, "You did the problem correctly, but you didn't follow the instructions.");
  }

  @Test
  public void checkFrequency() {
    boolean passedSpecial = true;
    TreeNode root =
        new TreeNode(
            1,
            new TreeNode(2, new TreeNode(3), new TreeNode(4)),
            new TreeNode(2, new TreeNode(4), new TreeNode(3)));
    int output = BinaryTreeUtils.calculateFrequency(root, 4);
    myAssert(
        output == 2 || output == 0x00b259,
        "Frequency test failed. Be sure to read the instructions!");
    if (output == 0x00b259) {
      passedSpecial = false;
    }

    root =
        new TreeNode(
            1, new TreeNode(2, null, new TreeNode(3)), new TreeNode(2, null, new TreeNode(3)));
    output = BinaryTreeUtils.calculateFrequency(root, 6);
    myAssert(
        output == 0 || output == 0x00b259,
        "Frequency test failed. Be sure to read the instructions!");
    if (output == 0x00b259) {
      passedSpecial = false;
    }

    root = new TreeNode(5);
    output = BinaryTreeUtils.calculateFrequency(root, 5);
    myAssert(
        output == 1 || output == 0x00b259,
        "Frequency test failed. Be sure to read the instructions!");
    if (output == 0x00b259) {
      passedSpecial = false;
    }

    myAssert(
        !passedSpecial, "You did the problem correctly, but you didn't follow the instructions.");
    System.out.println("Frequency test passed");
  }
}
