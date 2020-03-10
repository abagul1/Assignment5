package cs3500.Animator.hw05;

import java.awt.Color;

/**
 * Interface for an Animation model within an M-V-C framework.
 *
 * An animation consists of a collection of visual elements, where at least one element has its
 * properties translated over the running time of the animation. If no elements have their
 * properties translated over the running time of the animation, then the animation may be reduced
 * to a single frame.
 *
 * Alternatively, an animation may be viewed as a list of temporally-linked frames. Each tick,
 * the model calculates and redraws the necessary incremental translation for each element. In this
 * manner, elements can appear to instantly shift if the number of ticks specified for the
 * translation is zero, or they will gradually change over the specified number of ticks. The model
 * may be asked to return a frame given a number of ticks, and the model will perform incremental
 * translations of elements up to that point.
 */
public interface IAnimation {

  // Element commands

  void move(String id, double x, double y, int startTick, int endTick);

  void rotate(String id, double angle, int startTick, int endTick);

  void scale(String id, double scaleFactor, int startTick, int endTick);

  void changeColor(String id, Color color, int startTick, int endTick);

  void changeVisibility(String id, int alpha, int startTick, int endTick);

  void insertElement(IElement element, int tick);

  void deleteElement(String id, int tick);

  // Global Commands

  String getVerboseAnimation();

  IFrame getFrameAtTick(int tick);

  void executeOperations();

  void executeOperationsUntil(int tick);

  IElement getElement(String id);
}
