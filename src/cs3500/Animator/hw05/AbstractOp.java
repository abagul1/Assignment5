package cs3500.Animator.hw05;

/**
 * Abstract implementation of the {@code IOperation} interface.
 *
 * Defines the tick at which this operation is supposed to execute, and provides a constructor for
 * setting this tick number. All child classes should call this super constructor.
 *
 * Also, defines the getter method for the tick inherited from {@code IOperation}.
 */
public abstract class AbstractOp implements IOperation {

  protected int TickToFireAt;

  /**
   * Super constructor that sets the tick at which point the fire() method is executed in all child
   * classes.
   * @param tick  the tick to fire at.
   */
  public AbstractOp(int tick) {
    this.TickToFireAt = tick;
  }

  @Override
  public int getTickToFireAt() {
    return TickToFireAt;
  }
}
