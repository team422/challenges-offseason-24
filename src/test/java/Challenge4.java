import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.wrist.Wrist;
import frc.robot.subsystems.wrist.WristIO;
import frc.robot.subsystems.wrist.WristIOSim;
import frc.robot.subsystems.wrist.WristInputsAutoLogged;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
public class Challenge4 {

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

  static final double DELTA =
      3; // 3 degrees tolerance for the wrist to be considered at the desired angle

  Wrist wrist;
  WristInputsAutoLogged inputs;

  private void sleepCycles(int cycles) {
    for (int i = 0; i < cycles; i++) {
      wrist.periodic();
    }
  }

  private void sleepSeconds(double seconds) {
    sleepCycles((int) (seconds / 0.02));
  }

  @BeforeEach
  public void setup() {
    WristIO io = new WristIOSim();
    PIDController controller = new PIDController(0.8, 0.0, 0.05);
    controller.setTolerance(DELTA);
    wrist = new Wrist(io, controller);
    inputs = wrist.getInputs();

    myAssert(inputs != null, "Wrist.getInputs() should not return null");
  }

  @Test
  public void testPID() {
    double[] angles = getRange(-120, 120, 24);
    for (double angle : angles) {
      wrist.setDesiredAngle(Rotation2d.fromDegrees(0));
      sleepSeconds(3.0);
      myAssert(
          wrist.withinTolerance(),
          String.format(
              "Wrist is not within tolerance after 3 seconds. Expected: %f, Actual: %f",
              0.0, Units.radiansToDegrees(inputs.angleRad)));

      Rotation2d desiredAngle = Rotation2d.fromDegrees(angle);
      wrist.setDesiredAngle(desiredAngle);
      sleepSeconds(3.0);
      myAssert(
          wrist.withinTolerance(),
          String.format(
              "Wrist is not within tolerance after 3 seconds. Expected: %f, Actual: %f",
              angle, Units.radiansToDegrees(inputs.angleRad)));
      myAssert(
          inputs.angleRad - desiredAngle.getRadians() < DELTA,
          String.format(
              "Don't be cheeky with me. You can't just make withinTolerance return true and expect to pass the test. Expected: %f, Actual: %f",
              angle, Units.radiansToDegrees(inputs.angleRad)));
    }
  }

  @Test
  public void testCommand() {
    // i couldnt get commandscheduler to work so i manually execute the command
    Command cmd = wrist.setDesiredAngleCommand(Rotation2d.fromDegrees(90));
    myAssert(cmd != null, "setDesiredAngleCommand should return a command");
    cmd.initialize();

    sleepSeconds(3.0);

    myAssert(
        wrist.withinTolerance(),
        String.format(
            "Wrist is not within tolerance after 3 seconds (if failing this test the Command is likely wrong). Expected: %f, Actual: %f",
            90.0, Units.radiansToDegrees(inputs.angleRad)));
    myAssert(
        inputs.angleRad - Rotation2d.fromDegrees(90).getRadians() < DELTA,
        String.format(
            "Don't be cheeky with me. You can't just make withinTolerance return true and expect to pass the test (if failing this test the Command is likely wrong). Expected: %f, Actual: %f",
            90.0, Units.radiansToDegrees(inputs.angleRad)));
  }
}
