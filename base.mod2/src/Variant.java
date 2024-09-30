import java.util.Random;

public enum Variant {
    SCISSORS,
    ROCK,
    PAPER;

    public static Variant getRandom() {
        Random random = new Random();
        Variant[] variants = Variant.values();
        return variants[random.nextInt(variants.length)];
    }
}
