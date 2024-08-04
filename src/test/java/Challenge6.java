import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.FlywheelConstants;
import frc.robot.RobotContainer;
import frc.robot.oi.DriverControls;
import frc.robot.oi.DriverControlsXbox;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.FlywheelIONeo;
import frc.robot.util.TrackingTrigger;
import java.lang.reflect.Field;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
public class Challenge6 {
  // this is by far the most technically complex challenge as far as unit tests go, so buckle up
  // once you read this there's no going back

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
  static RobotContainer robotContainer;

  @BeforeAll
  public static void setup() {
    robotContainer = new RobotContainer(); // can only make one so needs to be BeforeAll
  }

  @Test
  public void testField() {

    Class<RobotContainer> robotContainerClass = RobotContainer.class;
    Class<Flywheel> flywheelClass = Flywheel.class;
    // this pains me
    // this violates every principle of java i hold dear
    // i will never look at the private keyword the same way again
    Field[] robotContainerFields = robotContainerClass.getDeclaredFields();
    boolean flywheelFound = false;
    for (Field field : robotContainerFields) {
      if (field.getType().equals(flywheelClass)) {
        flywheelFound = true;
        break;
      }
    }
    // ok so this is pretty complicated and hacky so ill do my best to explain
    // basically its using a thing called reflection to access private fields of a class
    // which i didnt even know was possible until now
    // but it just gets a list of all fields and finds one that is a Flywheel object
    // and if it finds one, it ends the search
    // if you think this is bad, just wait until we MAKE THE PRIVATE FIELDS PUBLIC

    myAssert(flywheelFound, "Flywheel member not found in RobotContainer");
  }

  @Test
  public void testInstantiation() {
    Class<RobotContainer> robotContainerClass = RobotContainer.class;
    Class<Flywheel> flywheelClass = Flywheel.class;
    Field[] robotContainerFields = robotContainerClass.getDeclaredFields();
    Flywheel flywheel = null;
    // find flywheel field and grab the object
    for (Field field : robotContainerFields) {
      if (field.getType().equals(flywheelClass)) {
        field.setAccessible(true); // i hate this, private doesn't mean anything anymore
        try {
          flywheel = (Flywheel) field.get(robotContainer);
        } catch (IllegalAccessException e) {
        }
      }
    }
    // i think i hate this language now

    myAssert(flywheel != null, "Flywheel member not found in RobotContainer");

    // check if FlywheelIONeo is instantiated
    FlywheelIONeo flywheelIONeo = null;
    try {
      // flywheelio is made by me so the name is guaranteed
      Field flywheelIOField = flywheelClass.getDeclaredField("m_io");
      flywheelIOField.setAccessible(true);
      flywheelIONeo = (FlywheelIONeo) flywheelIOField.get(flywheel);
    } catch (NoSuchFieldException | IllegalAccessException e) {
    }

    myAssert(
        flywheelIONeo != null,
        "FlywheelIONeo field not found in Flywheel. Either you have modified the Flywheel class or instantiated the Flywheel object incorrectly.");

    // check if PIDController is instantiated
    PIDController controller = null;
    try {
      // see above
      Field controllerField = flywheelClass.getDeclaredField("m_controller");
      controllerField.setAccessible(true);
      controller = (PIDController) controllerField.get(flywheel);
    } catch (NoSuchFieldException | IllegalAccessException e) {
    }

    myAssert(
        controller != null,
        "PIDController field not found in Flywheel. Either you have modified the Flywheel class or instantiated the Flywheel object incorrectly.");

    // check if correct PID values are set
    myAssert(
        Math.abs(controller.getP() - FlywheelConstants.kP) < DELTA,
        "P value not set correctly in PIDController");
    myAssert(
        Math.abs(controller.getI() - FlywheelConstants.kI) < DELTA,
        "I value not set correctly in PIDController");
    myAssert(
        Math.abs(controller.getD() - FlywheelConstants.kD) < DELTA,
        "D value not set correctly in PIDController");
  }

  @Test
  public void testButtonBinding() {
    Class<RobotContainer> robotContainerClass = RobotContainer.class;
    Class<DriverControls> driverControlsClass = DriverControls.class;
    Field[] robotContainerFields = robotContainerClass.getDeclaredFields();
    DriverControls driverControls = null;
    // another adventure in reflection
    for (Field field : robotContainerFields) {
      if (field.getType().equals(driverControlsClass)) {
        field.setAccessible(true);
        try {
          driverControls = (DriverControls) field.get(robotContainer);
        } catch (IllegalAccessException e) {
        }
      }
    }

    myAssert(
        driverControls != null,
        "DriverControls field not found in RobotContainer. Do not modify the m_driverControls field of RobotContainer.");

    // check if DriverControlsXbox is instantiated
    DriverControlsXbox driverControlsXbox = null;
    try {
      driverControlsXbox = (DriverControlsXbox) driverControls;
    } catch (ClassCastException e) {
    }

    myAssert(
        driverControlsXbox != null,
        "DriverControls field not set to DriverControlsXbox in RobotContainer. Do not modify the m_driverControls field of RobotContainer.");

    // get the TrackingTrigger and stored commands
    TrackingTrigger trackingTrigger = (TrackingTrigger) driverControlsXbox.runFlywheel();
    var commands = trackingTrigger.getCommands();

    // there should be two commands in the map, one is ON_TRUE and the other is ON_FALSE
    // imma do one big assert at the end instead of a bunch of small ones
    Command onTrueCommand = null;
    Command onFalseCommand = null;
    for (Command command : commands.keySet()) {
      if (commands.get(command).contains(TrackingTrigger.TriggerType.ON_TRUE)) {
        onTrueCommand = command;
      } else if (commands.get(command).contains(TrackingTrigger.TriggerType.ON_FALSE)) {
        onFalseCommand = command;
      }
    }
    myAssert(
        commands.size() == 2
            && onTrueCommand != null
            && onFalseCommand != null
            && onTrueCommand != onFalseCommand,
        "Commands not bound correctly");

    // i need the flywheel object to check if the commands are correct, so here we go again
    Flywheel flywheel = null;
    Class<Flywheel> flywheelClass = Flywheel.class;
    for (Field field : robotContainerFields) {
      if (field.getType().equals(flywheelClass)) {
        field.setAccessible(true);
        try {
          flywheel = (Flywheel) field.get(robotContainer);
        } catch (IllegalAccessException e) {
        }
      }
    }
    myAssert(flywheel != null, "Flywheel field not found in RobotContainer");

    // check if it's the correct command
    onTrueCommand.initialize();
    double setpoint = flywheel.getSetpoint();
    myAssert(
        Math.abs(setpoint - FlywheelConstants.kVelocitySetpoint) < DELTA,
        String.format(
            "Velocity setpoint not correctly set for Command. Expected: %f, Actual: %f",
            FlywheelConstants.kVelocitySetpoint, setpoint));

    onFalseCommand.initialize();
    setpoint = flywheel.getSetpoint();
    myAssert(
        setpoint < DELTA,
        String.format(
            "Velocity setpoint not correctly set for Command. Expected: 0.0, Actual: %f",
            setpoint));
  }

  // made it to the end! thank you for going on this journey with me.
}
