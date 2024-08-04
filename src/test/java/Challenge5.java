import edu.wpi.first.math.util.Units;
import frc.robot.subsystems.intake.pivot.PivotIO;
import frc.robot.subsystems.intake.pivot.PivotIOSim;
import frc.robot.subsystems.intake.pivot.PivotInputsAutoLogged;
import frc.robot.subsystems.intake.rollers.RollerIO;
import frc.robot.subsystems.intake.rollers.RollerIOSim;
import frc.robot.subsystems.intake.rollers.RollerInputsAutoLogged;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
public class Challenge5 {

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

  static final double DELTA = 1e-1;
  PivotIO pivotIO;
  RollerIO rollerIO;

  // these don't really matter so only initialize them once
  PivotInputsAutoLogged pivotInputs = new PivotInputsAutoLogged();
  RollerInputsAutoLogged rollerInputs = new RollerInputsAutoLogged();

  private void sleepCycles(int cycles) {
    for (int i = 0; i < cycles; i++) {
      pivotIO.updateInputs(pivotInputs);
      rollerIO.updateInputs(rollerInputs);
    }
  }

  private void sleepSeconds(double seconds) {
    sleepCycles((int) (seconds / 0.02));
  }

  @BeforeEach
  public void setup() {
    pivotIO = new PivotIOSim();
    rollerIO = new RollerIOSim();
  }

  @Test
  public void pivotTest() {
    // im too lazy to make each method its own test so theyre all in one

    // set and get voltage
    double[] voltages = getRange(-12, 12, 26);
    for (double voltage : voltages) {
      pivotIO.setVoltage(voltage);
      myAssert(
          Math.abs(pivotIO.getVoltage() - voltage) < DELTA,
          String.format(
              "Incorrect Pivot voltage. Expected: %f, Actual: %f", voltage, pivotIO.getVoltage()));
    }

    // io needs to update for angle to be correct
    sleepCycles(1);

    // get angle (just test if the angle is the same as the expected initial angle)
    myAssert(
        Math.abs(pivotIO.getAngle().getRadians() - Units.degreesToRadians(120)) < DELTA,
        String.format(
            "Incorrect Pivot angle. Expected: %f, Actual: %f",
            Units.degreesToRadians(120), pivotIO.getAngle().getRadians()));

    // get velocity
    // this one tests if they have set up all the constants correctly
    // its kinda complicated so lemme explain
    // basically the single jointed arm sim should have a start angle of 120 degrees
    // and if the current angle is ever outside of the range of the min and max angles (5 and 125
    // for this challenge), the sim will no longer be moving
    // i have set it up so that the sim will be 1 cycle away from hitting the min angle
    // so the velocity should be -9.81 rad/s (assuming all the physics constants are correct)
    double voltage = -7.0;
    int cycles = 21;
    double expectedVelocity = -9.81;
    pivotIO.setVoltage(voltage);
    sleepCycles(cycles);
    myAssert(
        Math.abs(pivotIO.getVelocityRadPerSec() - expectedVelocity) < DELTA,
        String.format(
            "Incorrect Pivot velocity. Expected: %f, Actual: %f. (If you are getting this one wrong, your physics constants are probably wrong. Be sure to carefully read the instructions)",
            expectedVelocity, pivotIO.getVelocityRadPerSec()));

    // now, one cycle later, the sim should have hit the min angle and the velocity should be 0
    // if they have made it here but fail this test, they have either hardcoded the velocity or
    // messed up the min and max angles
    sleepCycles(1);
    myAssert(
        Math.abs(pivotIO.getVelocityRadPerSec()) < DELTA,
        String.format(
            "Incorrect Pivot velocity. Expected: %f, Actual: %f. (If you are getting this one wrong, you either hardcoded the velocity or your min and max angles are wrong)",
            0.0, pivotIO.getVelocityRadPerSec()));
  }

  @Test
  public void rollerTest() {
    // set and get voltage
    double[] voltages = getRange(-12, 12, 26);
    for (double voltage : voltages) {
      rollerIO.setVoltage(voltage);
      myAssert(
          Math.abs(rollerIO.getVoltage() - voltage) < DELTA,
          String.format(
              "Incorrect Roller voltage. Expected: %f, Actual: %f",
              voltage, rollerIO.getVoltage()));
    }

    // io needs to update for velocity to be correct
    sleepCycles(1);

    // get velocity (normal)
    // now this one i can actually do the normal way since there's no angles to worry about
    // just gonna use voltages array again
    double prev = Integer.MIN_VALUE;
    for (double voltage : voltages) {
      rollerIO.setVoltage(voltage);
      sleepSeconds(1);
      double velocity = rollerIO.getVelocityRadPerSec();
      myAssert(
          velocity > prev,
          String.format(
              "Roller velocity is not increasing. Expected: >%f, Actual: %f", prev, velocity));
      prev = velocity;
    }

    rollerIO.setVoltage(0.0);
    // wait to slow down
    sleepSeconds(3);
    myAssert(
        rollerIO.getVelocityRadPerSec() < DELTA,
        String.format(
            "Motor not slowing down after no voltage for 3 seconds. Expected Velocity: %f, Actual: %f.",
            0.0, rollerIO.getVelocityRadPerSec()));

    // get velocity (test physics constants)
    // the constants are much simpler this time, but i still wanna make sure they get it right
    // the roller sim should have a start velocity of 0
    // and then after running for 1 second with a voltage of 12.0, the velocity should be 631.8
    double voltage = 12.0;
    double expectedVelocity = 631.8;
    rollerIO.setVoltage(voltage);
    sleepSeconds(1);
    myAssert(
        Math.abs(rollerIO.getVelocityRadPerSec() - expectedVelocity) < DELTA,
        String.format(
            "Incorrect Roller velocity. Expected: %f, Actual: %f. (If you are getting this one wrong, your physics constants are probably wrong. Be sure to carefully read the instructions)",
            expectedVelocity, rollerIO.getVelocityRadPerSec()));
  }
}
