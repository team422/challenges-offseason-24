import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeIOSim;
import frc.robot.subsystems.intake.IntakeInputsAutoLogged;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
public class Challenge3 {

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

  static final double DELTA = 1e-3;
  Intake intake;
  IntakeInputsAutoLogged inputs;

  private void waitCycles(int cycles) {
    for (int i = 0; i < cycles; i++) intake.periodic();
  }

  private void waitSeconds(double seconds) {
    waitCycles((int) (seconds / 0.02));
  }

  @BeforeEach
  public void setup() {
    intake = new Intake(new IntakeIOSim());
    inputs = intake.getInputs();

    myAssert(inputs != null, "Intake.getInputs() should not return null");
  }

  @Test
  public void testIntake() {
    double[] voltages = getRange(-12.0, 12.0, 48);
    for (double voltage : voltages) {
      intake.setRollerVoltage(0.0);
      waitSeconds(1.0);
      myAssert(
          Math.abs(inputs.rollerVelocity) < DELTA,
          String.format("Intake velocity should be zero when voltage is zero"));
      myAssert(
          Math.abs(inputs.rollerVoltage) < DELTA,
          String.format(
              "Intake voltage incorrect. Expected: 0.0, Actual: %f", inputs.rollerVoltage));

      intake.setRollerVoltage(voltage);
      waitSeconds(1.0);
      myAssert(
          Math.abs(inputs.rollerVoltage - voltage) < DELTA,
          String.format(
              "Intake voltage incorrect. Expected: %f, Actual: %f", voltage, inputs.rollerVoltage));
      if (Math.abs(voltage) > DELTA) {
        myAssert(
            Math.abs(inputs.rollerVelocity) > DELTA,
            String.format("Intake velocity should be non-zero when voltage is non-zero"));
      }
    }
  }

  @Test
  public void testIntakeCommand() {
    // i couldnt get commandscheduler to work so i manually execute the command
    Command cmd = intake.setVoltageCommand(7.0);
    myAssert(cmd != null, "setVoltageCommand should return a command");
    cmd.initialize();

    waitSeconds(1.0);

    myAssert(
        Math.abs(inputs.rollerVoltage - 7.0) < DELTA,
        String.format(
            "Intake voltage incorrect (if failing this test the Command is likely wrong). Expected: 7.0, Actual: %f",
            inputs.rollerVoltage));
    myAssert(
        Math.abs(inputs.rollerVelocity) > DELTA,
        String.format(
            "Intake velocity should be non-zero when voltage is non-zero (if failing this test the Command is likely wrong)"));
  }
}
