package seasonlogic;

public enum WinLoss {
  WIN("W"),
  LOSS("L");

  private final String shortHand;

  WinLoss(String shortHand) {
    this.shortHand = shortHand;
  }

  @Override
  public String toString() {
    return shortHand;
  }
}
