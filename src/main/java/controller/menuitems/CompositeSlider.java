package controller.menuitems;


import java.awt.Graphics;
import java.util.Arrays;
import java.util.List;

public class CompositeSlider implements Slider {

  private final List<Slider> sliders;

  public CompositeSlider(Slider... sliders) {
    this.sliders = Arrays.asList(sliders);
  }

  @Override
  public void drawSelf(Graphics g) {
    sliders.forEach(s -> s.drawSelf(g));
  }

  @Override
  public void onDrag(int x, int y) {
    sliders.forEach(s -> s.onDrag(x, y));
  }

  @Override
  public void onMousePressed(int x, int y) {
    sliders.forEach(s -> s.onMousePressed(x, y));
  }

  @Override
  public void onMouseReleased(int x, int y) {
    sliders.forEach(s -> s.onMouseReleased(x, y));
  }

  @Override
  public boolean isActivated() {
    return sliders.stream().anyMatch(Slider::isActivated);
  }

  @Override
  public void reset() {
    sliders.forEach(Slider::reset);
  }

  @Override
  public void resetDefaults() {
    sliders.forEach(Slider::resetDefaults);
  }

  @Override
  public void submit() {
    sliders.forEach(Slider::submit);
  }
}
